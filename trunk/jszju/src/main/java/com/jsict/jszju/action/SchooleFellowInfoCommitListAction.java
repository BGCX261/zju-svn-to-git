/**
 * SchooleFellowInfoCommitListAction.java        2009-11-27 下午10:14:27
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.jszju.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import com.jsict.jszju.domain.ArticleContentDomain;
import com.jsict.jszju.domain.PaychargeDomain;
import com.jsict.jszju.domain.SchoolFellowForHelpDomain;
import com.jsict.jszju.domain.SchoolFellowPutInfoDoamin;
import com.jsict.jszju.entity.ItcastTopic;
import com.jsict.jszju.form.PaychargeListFrom;
import com.jsict.jszju.form.SchoolFellowForHelpListForm;
import com.jsict.jszju.service.ISchoolFellowInfoCommitService;
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
public class SchooleFellowInfoCommitListAction extends BaseListAction {

	private static final String FORHELP = "forhelp";

	private static final String CONSULTVIEW = "consultview";

	private static final String VIEW = "view";

	private static final String FORHELPLIST = "forhelplist";

	private static final String VIEWALLINFO = "viewallinfo";

	private static final String VIEWALLCONSULT = "viewallconsult";

	private static final String VIEWALLINFO2 = "viewallinfo2";

	private static final String VIEWALLCONSULT2 = "viewallconsult2";

	private IUserInfoService userInfoService;

	private ISchoolFellowInfoCommitService schoolFellowInfoCommitService;

	private ISchooleFellowInfoConsultService schooleFellowInfoConsultService;

	@Required
	public void setSchooleFellowInfoConsultService(
			ISchooleFellowInfoConsultService schooleFellowInfoConsultService) {
		this.schooleFellowInfoConsultService = schooleFellowInfoConsultService;
	}

	@Required
	public void setSchoolFellowInfoCommitService(
			ISchoolFellowInfoCommitService schoolFellowInfoCommitService) {
		this.schoolFellowInfoCommitService = schoolFellowInfoCommitService;
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

		SchoolFellowForHelpListForm beanForm = (SchoolFellowForHelpListForm) form;
		String actionType = request.getParameter("actionType");

		if (FORHELPLIST.equalsIgnoreCase(actionType)) {
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

			initSchoolfellowhelp(request, beanForm);
			return mapping.findForward("forhelplist");
		} else if (VIEWALLINFO.equalsIgnoreCase(actionType)) {

			return mapping.findForward(VIEWALLINFO);

		} else if (VIEWALLCONSULT.equalsIgnoreCase(actionType)) {

			return mapping.findForward(VIEWALLCONSULT);

		} else if (VIEWALLINFO2.equalsIgnoreCase(actionType)) {

			PagedList<SchoolFellowForHelpDomain> list = schoolFellowInfoCommitService
					.getSchoolFelloHelpPagedList(getEntityFilter(), null, null);
			Collections.sort(list, new SchoolFellowForHelpListComparator());
			request.setAttribute("infolist", list);
			beanForm.setResultList(list);
			setPageInfo(request, (PagedList) list); // 设置分页信息

			return mapping.findForward(VIEWALLINFO2);

		} else if (VIEWALLCONSULT2.equalsIgnoreCase(actionType)) {

			PagedList<SchoolFellowPutInfoDoamin> list = schooleFellowInfoConsultService
					.getSchooleFellowInfoConsultPagedList(getEntityFilter(),
							null, null);
			Collections.sort(list, new SchoolFellowPutInfoListComparator());
			request.setAttribute("consultlist", list);
			beanForm.setResultList(list);
			setPageInfo(request, (PagedList) list); // 设置分页信息

			return mapping.findForward(VIEWALLCONSULT2);

		} else if (VIEW.equalsIgnoreCase(actionType)) {

			List<ItcastTopic> list1 = schoolFellowInfoCommitService
					.getItcastTopic("10");
			List<ItcastTopic> list2 = schoolFellowInfoCommitService
			.getItcastTopic("11");
			if(list2!=null){    
	            Iterator it= list2.iterator();    
	            while(it.hasNext()){    
	            	list1.add((ItcastTopic)it.next());    
	            }    
	         }    
			Collections.sort(list1, new ItcastTopicListComparator());
			request.setAttribute("forhelplist", list1);
			return mapping.findForward("view");

		} else if (CONSULTVIEW.equalsIgnoreCase(actionType)) {

			List<ItcastTopic> list = schoolFellowInfoCommitService
			.getItcastTopic("12");
			Collections.sort(list, new ItcastTopicListComparator());
			request.setAttribute("forconsultlist", list);
			return mapping.findForward("consultview");

		} else if (FORHELP.equalsIgnoreCase(actionType)) {

			SchoolFellowForHelpDomain helpDomain = beanForm
					.getSchoolFellowForHelpDomain();
			SchoolFellowForHelpDomain domain = new SchoolFellowForHelpDomain();
			domain.setContent(helpDomain.getContent());
			domain.setTitle(helpDomain.getTitle());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			String commitTime = df.format(new Date());
			domain.setTime(commitTime);
			domain.setVisittimes("0");
			String userName = null;
			userName = userInfoService.getUserNameFormCookie(request);
			if (userName != null) {
				Integer userId = userInfoService.getUserInfoId(userName);
				domain.setUserid(Integer.toString(userId));
				schoolFellowInfoCommitService.addSchoolFelloHelp(domain);
				/**
				 * 增加修改user的活跃度函数，在userInfoService里面写这个函数
				 */
				return mapping.getInputForward();
			} else {
				/**
				 * 用户没有登陆
				 */
				return mapping.findForward("errorlogin");
			}
		} else if (Consts.ACTION_TYPE_EDIT.equalsIgnoreCase(actionType)) {
			// 根据界面传入ID取得对应的domain,进入数据编辑页面
			SchoolFellowForHelpDomain domain = schoolFellowInfoCommitService
					.getSchoolFelloHelp(request.getParameter("id"));
			beanForm.setSchoolFellowForHelpDomain(domain);
			beanForm.setActionType(Consts.ACTION_TYPE_UPDATE);
			return mapping.findForward("edit");
		} else if (Consts.ACTION_TYPE_INSERT.equalsIgnoreCase(actionType)) {
			// 点击保存按钮后,如果ACTION_TYPE是insert, 则插入数据
			SchoolFellowForHelpDomain domain = beanForm
					.getSchoolFellowForHelpDomain();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			String commitTime = df.format(new Date());
			domain.setTime(commitTime);
			schoolFellowInfoCommitService.addSchoolFelloHelp(domain);
			return mapping.findForward(Consts.ACTION_TYPE_BACKTOLIST);
		} else if (Consts.ACTION_TYPE_CANCEL.equalsIgnoreCase(actionType)) {
			return mapping.findForward(Consts.ACTION_TYPE_BACKTOLIST);
		} else if (Consts.ACTION_TYPE_UPDATE.equalsIgnoreCase(actionType)) {
			// 点击保存按钮后,如果ACTION_TYPE是update, 则修改数据
			SchoolFellowForHelpDomain domain = beanForm
					.getSchoolFellowForHelpDomain();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			String commitTime = df.format(new Date());
			domain.setTime(commitTime);
			schoolFellowInfoCommitService.updateSchoolFelloHelp(domain);
			return mapping.findForward(Consts.ACTION_TYPE_BACKTOLIST);
		}
		return mapping.getInputForward();
	}

	private void initSchoolfellowhelp(HttpServletRequest request,
			SchoolFellowForHelpListForm form) throws SystemException,
			ApplicationException {
		PagedList<SchoolFellowForHelpDomain> list = schoolFellowInfoCommitService
				.getSchoolFelloHelpPagedList(getEntityFilter(), null, null);
		form.setResultList(list);
		setPageInfo(request, (PagedList) list); // 设置分页信息
	}

	class SchoolFellowForHelpListComparator implements Comparator {
		public int compare(Object o1, Object o2) {
			SchoolFellowForHelpDomain s1 = (SchoolFellowForHelpDomain) o1;
			SchoolFellowForHelpDomain s2 = (SchoolFellowForHelpDomain) o2;
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

	class SchoolFellowPutInfoListComparator implements Comparator {
		public int compare(Object o1, Object o2) {
			SchoolFellowPutInfoDoamin s1 = (SchoolFellowPutInfoDoamin) o1;
			SchoolFellowPutInfoDoamin s2 = (SchoolFellowPutInfoDoamin) o2;
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
	
	class ItcastTopicListComparator implements Comparator {
		public int compare(Object o1, Object o2) {
			ItcastTopic s1 = (ItcastTopic) o1;
			ItcastTopic s2 = (ItcastTopic) o2;
			int result1 = s1.getLastArticlePostTime().compareTo(s2.getLastArticlePostTime());
			int result = (result1 < 0) ? 1 : -1;
			return result;
		}
	}

}
