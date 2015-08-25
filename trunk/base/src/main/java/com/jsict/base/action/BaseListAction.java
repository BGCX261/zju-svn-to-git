package com.jsict.base.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.jsict.base.context.ProjectContext;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.FilterFormatNotValidException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.form.BaseListForm;
import com.jsict.base.util.Consts;
import com.jsict.base.util.HttpRequestPaser;
import com.jsict.base.util.MessageInfo;

/**
 * @author qipf
 */
public abstract class BaseListAction extends BaseAction
{

    private EntityFilter entityFilter;

    private Integer pageNo;

    private Integer pageSize;

    public ActionForward process(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws ApplicationException,
            SystemException

    {

        BaseListForm myForm = (BaseListForm) form;
        String actionType = myForm.getActionType();
        ActionForward moveTo = null;

        if (Consts.ACTION_TYPE_PRE.equals(actionType))
        {
            int currentPageNo = myForm.getCurrentPageNo();
            if (currentPageNo > 0)
            {
                currentPageNo--;
                myForm.setCurrentPageNo(currentPageNo);
                int recordsPerPage = myForm.getRecordsPerPage();
                List list = myForm.getResultList();
                int firstRecordNo = currentPageNo * recordsPerPage;
                int lastRecordNo = (currentPageNo + 1) * recordsPerPage;
                myForm.setFirstRecordNo(firstRecordNo);
                myForm.setLastRecordNo(lastRecordNo);
            }
            moveTo = mapping.getInputForward();
        }
        else if (Consts.ACTION_TYPE_NEXT.equals(actionType))
        {
            int currentPageNo = myForm.getCurrentPageNo();
            int maxPageNo = myForm.getMaxPageNo();
            if (currentPageNo < maxPageNo)
            {
                currentPageNo++;
                myForm.setCurrentPageNo(currentPageNo);
                int recordsPerPage = myForm.getRecordsPerPage();
                List list = myForm.getResultList();
                int firstRecordNo = currentPageNo * recordsPerPage;
                int lastRecordNo = (currentPageNo + 1) * recordsPerPage;
                lastRecordNo = ((lastRecordNo > list.size()) ? list.size() : lastRecordNo);
                myForm.setFirstRecordNo(firstRecordNo);
                myForm.setLastRecordNo(lastRecordNo);
            }
            moveTo = mapping.getInputForward();
        }
        else
        {
            try
            {
                try
                {
                    HttpRequestPaser parser = new HttpRequestPaser(request);
                    entityFilter = parser.getEntityFilter();
                    pageNo = parser.getPageNo();
                    pageSize = parser.getPageSize();
                }
                catch (FilterFormatNotValidException e)
                {
                    ProjectContext.getErrorList().add(e.getMessageInfo());
                    throw new ApplicationException();
                }
                moveTo = doListProcess(mapping, form, request, response);
            }
            catch (ApplicationException e)
            {
                List<MessageInfo> errorList = ProjectContext.getErrorList();
                MessageInfo messageInfo = e.getMessageInfo();
                if(messageInfo != null)
                {
                    errorList.add(messageInfo);
                }
                request.setAttribute("errorList", errorList);
                String forward = e.getForward();
                if(forward == null)
                {
                    moveTo = mapping.getInputForward();
                }
                else
                {
                    moveTo = mapping.findForward(forward);
                }

                return moveTo;

            }
            catch (SystemException e)
            {
                return moveTo;
            }
            finally
            {
                ProjectContext.freeContext();
            }
        }
        return moveTo;

    }

    public abstract ActionForward doListProcess(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws ApplicationException,
            SystemException;

    public EntityFilter getEntityFilter()
    {
        return entityFilter;
    }

    public void setEntityFilter(EntityFilter entityFilter)
    {
        this.entityFilter = entityFilter;
    }

    public Integer getPageNo()
    {
        return pageNo;
    }

    public void setPageNo(Integer pageNo)
    {
        this.pageNo = pageNo;
    }

    /*
     * If pageSize is null, return Consts.DEFAULT_PAGE_SIZE 
     */
    public Integer getPageSize()
    {
        return pageSize == null ? Consts.DEFAULT_PAGE_SIZE : pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }

    protected void setPageInfo(HttpServletRequest request, PagedList resultList)
    {
        request.setAttribute("currentPageNo", resultList.getPageNo());
        request.setAttribute("firstResult", resultList.getFirstResult());
        request.setAttribute("lastResult", resultList.getLastResult());
        request.setAttribute("totalPages", resultList.getTotalPages());
        request.setAttribute("totalCount", resultList.getTotalCount());
        request.setAttribute("pageSize", resultList.getPageSize());
        request.setAttribute("resultList", resultList);
    }

}
