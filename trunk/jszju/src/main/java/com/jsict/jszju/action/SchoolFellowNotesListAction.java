/**
 * SchoolFellowNotesListAction.java        2009-11-28 下午06:34:03
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.jszju.action;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.jsict.base.action.BaseListAction;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.Consts;
import com.jsict.jszju.domain.CommentDomain;
import com.jsict.jszju.domain.SchoolFellowForHelpDomain;
import com.jsict.jszju.domain.SchoolFellowNotesDomain;
import com.jsict.jszju.form.SchoolFellowForHelpListForm;
import com.jsict.jszju.form.SchoolFellowNotesListForm;
import com.jsict.jszju.service.ISchoolFellowNotesService;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class SchoolFellowNotesListAction extends BaseListAction {

	private static final String NOTES = "notes";
	
	private static final String ALLNOTES = "allnotes";
	
	private static final String VIEWALLNOTES = "viewallnotes";
	
	private static final String FRONT = "front";

	private ISchoolFellowNotesService schoolFellowNotesService;

	public void setSchoolFellowNotesService(
			ISchoolFellowNotesService schoolFellowNotesService) {
		this.schoolFellowNotesService = schoolFellowNotesService;
	}

	/**
	 * <p>
	 * Description: The doListProcess
	 * </p>
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws ApplicationException
	 * @throws SystemException
	 * @author: caie
	 * @update: [updatedate] [changer][change description]
	 */

	@Override
	public ActionForward doListProcess(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ApplicationException, SystemException {
		SchoolFellowNotesListForm beanForm = (SchoolFellowNotesListForm) form;
		String actionType = request.getParameter("actionType");
		if (NOTES.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			String adminrole = null;
			boolean flag=false;
			for (Cookie c : cookies) {
				if (c.getName().equals("role") && (c.getValue().equals("typernotes")||(c.getValue().equals("admin"))||(c.getValue().equals("superadmin")))) {
					flag=true;
					break;
				}
			}
			if(!flag)
			{
				return mapping.findForward("nopopedom");
			}
			init(request, beanForm);
			return mapping.getInputForward();
		}
		else if (VIEWALLNOTES.equalsIgnoreCase(actionType)) {
			PagedList<SchoolFellowNotesDomain> list = schoolFellowNotesService
			.getSchoolFellowNotesPagedList(getEntityFilter(), getPageNo(),getPageSize());
			request.setAttribute("allnotesList", list);
			Collections.sort(list, new SchoolFellowNotesComparator());
			beanForm.setResultList(list);
			setPageInfo(request, (PagedList) list); //设置分页信息
			return mapping.findForward("viewallnotes");
		}
		else if (ALLNOTES.equalsIgnoreCase(actionType)) {
			
			return mapping.findForward("allnotes");
		}
		else if (FRONT.equalsIgnoreCase(actionType)) {
			PagedList<SchoolFellowNotesDomain> list = schoolFellowNotesService
			.getSchoolFellowNotesPagedList(getEntityFilter(), getPageNo(), getPageSize());
			request.setAttribute("notelist", list);
			return mapping.findForward("front");
		}  
		else if (Consts.ACTION_TYPE_NEW.equalsIgnoreCase(actionType)) {
			beanForm.setActionType(Consts.ACTION_TYPE_INSERT);
			return mapping.findForward("edit");
		} else if (Consts.ACTION_TYPE_VIEW.equalsIgnoreCase(actionType)) {
			
			SchoolFellowNotesDomain domain = schoolFellowNotesService
			.getSchoolFellowNotes(request.getParameter("id"));
			request.setAttribute("domain", domain);
			return mapping.findForward("view");
		} else if (Consts.ACTION_TYPE_EDIT.equalsIgnoreCase(actionType)) {

			// 根据界面传入ID取得对应的domain,进入数据编辑页面
			SchoolFellowNotesDomain domain = schoolFellowNotesService
					.getSchoolFellowNotes(request.getParameter("id"));
			beanForm.setDomain(domain);
			beanForm.setActionType(Consts.ACTION_TYPE_UPDATE);
			return mapping.findForward("edit");
		}else if(Consts.ACTION_TYPE_UPDATE.equalsIgnoreCase(actionType))
        {
	          //点击保存按钮后,如果ACTION_TYPE是update, 则修改数据
			SchoolFellowNotesDomain domain = beanForm.getDomain();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String commitTime=df.format(new Date());
			domain.setTime(commitTime);
			schoolFellowNotesService.updateSchoolFellowNotes(domain);
	            return mapping.findForward(Consts.ACTION_TYPE_BACKTOLIST);
	        }
		else if (Consts.ACTION_TYPE_INSERT.equalsIgnoreCase(actionType)) {
			// 点击保存按钮后,如果ACTION_TYPE是insert, 则插入数据
			SchoolFellowNotesDomain domain = beanForm.getDomain();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String commitTime=df.format(new Date());
			domain.setTime(commitTime);
			schoolFellowNotesService.addSchoolFellowNotes(domain);
			return mapping.findForward(Consts.ACTION_TYPE_BACKTOLIST);
		}
		else if (Consts.ACTION_TYPE_DISABLE.equalsIgnoreCase(actionType)) {
			// 点击保存按钮后,如果ACTION_TYPE是insert, 则插入数据
			SchoolFellowNotesDomain domain = schoolFellowNotesService
			.getSchoolFellowNotes(request.getParameter("id"));
			schoolFellowNotesService.delSchoolFellowNotes(domain);
			return mapping.findForward(Consts.ACTION_TYPE_BACKTOLIST);
		}
		return mapping.getInputForward();

	}

	private void init(HttpServletRequest request, SchoolFellowNotesListForm form)
			throws SystemException, ApplicationException {
		PagedList<SchoolFellowNotesDomain> list = schoolFellowNotesService
				.getSchoolFellowNotesPagedList(getEntityFilter(), getPageNo(), getPageSize());
		form.setResultList(list);
		setPageInfo(request, (PagedList) list); // 设置分页信息
	}
	
	class  SchoolFellowNotesComparator implements Comparator {
		public int compare(Object o1, Object o2) {
			SchoolFellowNotesDomain s1 = (SchoolFellowNotesDomain) o1;
			SchoolFellowNotesDomain s2 = (SchoolFellowNotesDomain) o2;
			if (s1.getTime() == null) {
				s1.setTime("2009-10-10 00:00:00");
			}
			if (s2.getTime() == null) {
				s2.setTime("2009-10-10 00:00:00");
			}
			int result1 = s1.getTime().compareTo(s2.getTime());
			int result = (result1 < 0) ? 1 : -1;
			return result;
		}
	}

}
