/**
 * SchooleFellowInfoConsultListAction.java        2009-11-28 上午12:28:32
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.jszju.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.action.BaseListAction;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.Consts;
import com.jsict.jszju.domain.SchoolFellowForHelpDomain;
import com.jsict.jszju.domain.SchoolFellowPutInfoDoamin;
import com.jsict.jszju.form.SchoolFellowForHelpListForm;
import com.jsict.jszju.form.SchoolFellowPutInfoListForm;
import com.jsict.jszju.service.ISchooleFellowInfoConsultService;
import com.jsict.jszju.service.IUserInfoService;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class SchooleFellowInfoConsultListAction extends BaseListAction {

	private static final String CONSULTLIST = "consultlist";

	private static final String CONSULT = "consul";

	private IUserInfoService userInfoService;

	private ISchooleFellowInfoConsultService schooleFellowInfoConsultService;

	@Required
	public void setSchooleFellowInfoConsultService(
			ISchooleFellowInfoConsultService schooleFellowInfoConsultService) {
		this.schooleFellowInfoConsultService = schooleFellowInfoConsultService;
	}

	@Required
	public void setUserInfoService(IUserInfoService userInfoService) {
		this.userInfoService = userInfoService;
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
		String actionType = request.getParameter("actionType");
		SchoolFellowPutInfoListForm beanForm = (SchoolFellowPutInfoListForm) form;
		SchoolFellowPutInfoDoamin consultDomain = beanForm.getDomain();
		if (CONSULT.equalsIgnoreCase(actionType)) {

			SchoolFellowPutInfoDoamin domain = new SchoolFellowPutInfoDoamin();
			domain.setContent(consultDomain.getContent());
			domain.setTitle(consultDomain.getTitle());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String commitTime=df.format(new Date());
			domain.setTime(commitTime);
			domain.setVisittime("0");
			String userName = null;
			userName = userInfoService.getUserNameFormCookie(request);
			if (userName != null) {
				Integer userId = userInfoService.getUserInfoId(userName);
				domain.setUserid(Integer.toString(userId));
				schooleFellowInfoConsultService.addSchooleFellowInfoConsult(domain);
				/**
				 * 2 然后保存comment内容
				 */
				return mapping.getInputForward();
			} else {
				/**
				 * 用户没有登陆
				 */
				return mapping.findForward("errorlogin");
			}
		} else if (CONSULTLIST.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			String adminrole = null;
			boolean flag=false;
			for (Cookie c : cookies) {
				if (c.getName().equals("role") && (c.getValue().equals("typerbbs")||(c.getValue().equals("admin"))||(c.getValue().equals("superadmin")))) {
					flag=true;
					break;
				}
			}
			if(!flag)
			{
				return mapping.findForward("nopopedom");
			}
			
			PagedList<SchoolFellowPutInfoDoamin> list = schooleFellowInfoConsultService.getSchooleFellowInfoConsultPagedList(getEntityFilter(), null, null);
			beanForm.setResultList(list);
			setPageInfo(request, (PagedList) list); // 设置分页信息
			return mapping.findForward("consultlist");

		} else if (Consts.ACTION_TYPE_EDIT.equalsIgnoreCase(actionType)) {
			// 根据界面传入ID取得对应的domain,进入数据编辑页面
			SchoolFellowPutInfoDoamin domain = schooleFellowInfoConsultService
					.getSchooleFellowInfoConsult(request.getParameter("id"));
			beanForm.setDomain(domain);
			beanForm.setActionType(Consts.ACTION_TYPE_UPDATE);
			return mapping.findForward("edit");
		} else if (Consts.ACTION_TYPE_INSERT.equalsIgnoreCase(actionType)) {
			// 点击保存按钮后,如果ACTION_TYPE是insert, 则插入数据
			SchoolFellowPutInfoDoamin domain = beanForm.getDomain();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String commitTime=df.format(new Date());
			domain.setTime(commitTime);
			schooleFellowInfoConsultService.addSchooleFellowInfoConsult(domain);
			return mapping.findForward(Consts.ACTION_TYPE_BACKTOLIST);
		} 
		 else if(Consts.ACTION_TYPE_UPDATE.equalsIgnoreCase(actionType))
	        {
	          //点击保存按钮后,如果ACTION_TYPE是update, 则修改数据
			 SchoolFellowPutInfoDoamin domain = beanForm.getDomain();
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				String commitTime=df.format(new Date());
				domain.setTime(commitTime);
			 schooleFellowInfoConsultService.updateSchooleFellowInfoConsult(domain);
	            return mapping.findForward(Consts.ACTION_TYPE_BACKTOLIST);
	        }
		 else if (Consts.ACTION_TYPE_CANCEL.equalsIgnoreCase(actionType)) {
			return mapping.findForward(Consts.ACTION_TYPE_BACKTOLIST);
		}
		return mapping.getInputForward();
	}
}
