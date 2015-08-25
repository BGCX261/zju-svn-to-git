/**
 * ArticleListViewAction.java        2009-11-11 下午05:07:15
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.jszju.action.frontMenu;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.jsict.base.action.BaseListAction;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.jszju.domain.ArticleContentDomain;
import com.jsict.jszju.domain.SchoolFellowForHelpDomain;
import com.jsict.jszju.domain.SchoolFellowNotesDomain;
import com.jsict.jszju.domain.SchoolFellowPutInfoDoamin;
import com.jsict.jszju.domain.UserInfoDomain;
import com.jsict.jszju.form.baseform.LinkToViewForm;
import com.jsict.jszju.service.IArticleContentService;
import com.jsict.jszju.service.ISchoolFellowInfoCommitService;
import com.jsict.jszju.service.ISchoolFellowNotesService;
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
public class ArticleListViewAction extends BaseListAction {
	
	private IArticleContentService articleContentService;

	private ISchoolFellowInfoCommitService schoolFellowInfoCommitService;

	private ISchooleFellowInfoConsultService schooleFellowInfoConsultService;
	
	private ISchoolFellowNotesService schoolFellowNotesService;
	
	
	public void setSchoolFellowNotesService(
			ISchoolFellowNotesService schoolFellowNotesService) {
		this.schoolFellowNotesService = schoolFellowNotesService;
	}

	public void setSchooleFellowInfoConsultService(
			ISchooleFellowInfoConsultService schooleFellowInfoConsultService) {
		this.schooleFellowInfoConsultService = schooleFellowInfoConsultService;
	}

	public void setSchoolFellowInfoCommitService(
			ISchoolFellowInfoCommitService schoolFellowInfoCommitService) {
		this.schoolFellowInfoCommitService = schoolFellowInfoCommitService;
	}

	public void setArticleContentService(
			IArticleContentService articleContentService) {
		this.articleContentService = articleContentService;
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
		Cookie[] cookies = request.getCookies();
		String name = null;
		String password = null;
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("name")) {
					name = c.getValue();
					String actionType = request.getParameter("actionType");
					if (actionType != null) {
						Integer id = Integer.parseInt(actionType);
						ArticleContentDomain artDomain=new ArticleContentDomain();
						artDomain=articleContentService.getArticleContent(id.toString());
						if(artDomain.getVisitTime()==null)
						{
							artDomain.setVisitTime("1");
						}
						else
						{
							Integer vt=Integer.parseInt(artDomain.getVisitTime())+1;
							artDomain.setVisitTime(vt.toString());
						}
						articleContentService.updateArticleContent(artDomain);
						HttpSession session = request.getSession();
						PagedList<ArticleContentDomain> list = articleContentService
								.getArticleContentPagedList(getEntityFilter(),
										null, null);
						session.setAttribute("id", id);
						session.setAttribute("List", list);
					} else {
						String forhelpview = request
								.getParameter("forhelpview");
						if (forhelpview != null) {
							Integer id = Integer.parseInt(forhelpview);
							SchoolFellowForHelpDomain sfDomain=new SchoolFellowForHelpDomain();
							sfDomain=schoolFellowInfoCommitService.getSchoolFelloHelp(id.toString());
							if(sfDomain.getVisittimes()==null)
							{
								sfDomain.setVisittimes("1");
							}
							else
							{
								Integer vt=Integer.parseInt(sfDomain.getVisittimes())+1;
								sfDomain.setVisittimes(vt.toString());
							}
							
							schoolFellowInfoCommitService.updateSchoolFelloHelp(sfDomain);
							HttpSession session = request.getSession();
							PagedList<SchoolFellowForHelpDomain> list = schoolFellowInfoCommitService
									.getSchoolFelloHelpPagedList(
											getEntityFilter(), null, null);
							session.setAttribute("id", id);
							session.setAttribute("List", list);
						} else {
							String consultview = request
									.getParameter("consultview");
							if(consultview!=null){
							Integer id = Integer.parseInt(consultview);
							SchoolFellowPutInfoDoamin sfDomain=new SchoolFellowPutInfoDoamin();
							sfDomain=schooleFellowInfoConsultService.getSchooleFellowInfoConsult(id.toString());
							if(sfDomain.getVisittime()==null)
							{
								sfDomain.setVisittime("1");
							}
							else
							{
								Integer vt=Integer.parseInt(sfDomain.getVisittime())+1;
								sfDomain.setVisittime(vt.toString());
							}
							schooleFellowInfoConsultService.updateSchooleFellowInfoConsult(sfDomain);
							HttpSession session = request.getSession();
							PagedList<SchoolFellowPutInfoDoamin> list = schooleFellowInfoConsultService
									.getSchooleFellowInfoConsultPagedList(
											getEntityFilter(), null, null);
							session.setAttribute("id", id);
							session.setAttribute("List", list);
							}else
							{
								String notesview = request
								.getParameter("notesview");
								if(notesview!=null){
										Integer id = Integer.parseInt(notesview);
										SchoolFellowNotesDomain sfDomain=new SchoolFellowNotesDomain();
										sfDomain=schoolFellowNotesService.getSchoolFellowNotes(id.toString());
										if(sfDomain.getVisittime()==null)
										{
											sfDomain.setVisittime("1");
										}
										else
										{
											Integer vt=Integer.parseInt(sfDomain.getVisittime())+1;
											sfDomain.setVisittime(vt.toString());
										}
										schoolFellowNotesService.updateSchoolFellowNotes(sfDomain);
										HttpSession session = request.getSession();
										PagedList<SchoolFellowNotesDomain> list = schoolFellowNotesService.getSchoolFellowNotesPagedList(
												getEntityFilter(), null, null);
										session.setAttribute("id", id);
										session.setAttribute("List", list);
							}
						}
					}
					}

					return mapping.findForward("success");
				}
			}
		}

		return mapping.findForward("nologin");

	}

}
