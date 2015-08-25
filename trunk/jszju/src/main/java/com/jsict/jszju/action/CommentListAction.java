/**
 * CommentListAction.java        2009-11-24 上午12:42:06
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.jszju.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.action.BaseListAction;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.Consts;
import com.jsict.base.util.ConvertUtil;
import com.jsict.base.util.Op;
import com.jsict.jszju.action.SchooleFellowInfoCommitListAction.SchoolFellowForHelpListComparator;
import com.jsict.jszju.domain.ArticleContentDomain;
import com.jsict.jszju.domain.CommentDomain;
import com.jsict.jszju.domain.SchoolFellowPutInfoDoamin;
import com.jsict.jszju.domain.UserInfoDomain;
import com.jsict.jszju.entity.Userinfo;
import com.jsict.jszju.form.CommentListForm;
import com.jsict.jszju.service.IArticleContentService;
import com.jsict.jszju.service.ICommentHanderService;
import com.jsict.jszju.service.IUserInfoService;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class CommentListAction extends BaseListAction {
	
	private static final String COMMENT = "comment";
	
	private static final String COMMENTUP = "commentup";
	
	private static final String COMMENTVIEW = "commentview";
	
	private IUserInfoService	userInfoService;
	
	private IArticleContentService  articleContentService;
	
	private ICommentHanderService  commentHanderService;
	
	@Required
	public void setUserInfoService(IUserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
	@Required
	public void setArticleContentService(
			IArticleContentService articleContentService) {
		this.articleContentService = articleContentService;
	}
	@Required
	public void setCommentHanderService(ICommentHanderService commentHanderService) {
		this.commentHanderService = commentHanderService;
	}



	/**
	 * <p>Description: The doListProcess</p>
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
		CommentListForm  beanForm=(CommentListForm)form;
		CommentDomain	commentDomain=beanForm.getCommentDomain();
		if (COMMENT.equalsIgnoreCase(actionType)) {
			return mapping.findForward("comment");
		}
		else if (COMMENTVIEW.equalsIgnoreCase(actionType)) {
			//String articleId=request.getParameter("commentArticleId");
			PagedList<CommentDomain> listDomain = new PagedList<CommentDomain>();
			EntityFilter ef=new EntityFilter();
			//ef.addFilter("articleId", Op.EQUAL, articleId);
			listDomain=commentHanderService.getCommentPageList(ef, null, null);
			Collections.sort(listDomain, new CommentViewListComparator());
			request.setAttribute("commentlist", listDomain);
			return mapping.findForward("commentview");
		}
		else if (COMMENTUP.equalsIgnoreCase(actionType)) {
			 
			CommentDomain domain=new CommentDomain();
			domain.setCommentcontent(commentDomain.getCommentcontent());
			String userName1=null;
			String userName=null;
			userName1=userInfoService.getUserNameFormCookie(request);
			try
			{
			userName = java.net.URLDecoder.decode(userName1,"UTF-8");
			}
			catch(Exception e)
			{
				
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String commitTime=df.format(new Date());
			domain.setCommentTime(commitTime);
			if(userName!=null)
			{
			Integer userId=userInfoService.getUserInfoId(userName);
			domain.setUserName(userName);
			String articleId=request.getParameter("articleId");
			domain.setArticleId(articleId);
			commentHanderService.saveCommentContent(domain);
		
			return mapping.findForward("commentsuccess");
			}
			else
			{
				
				return mapping.getInputForward();
			}
		}
		return mapping.getInputForward();
	}
	
	class CommentViewListComparator implements Comparator {
		public int compare(Object o1, Object o2) {
			CommentDomain s1 = (CommentDomain) o1;
			CommentDomain s2 = (CommentDomain) o2;
			if (s1.getCommentTime() == null) {
				s1.setCommentTime("2009-10-10 00:00:00");
			}
			if (s2.getCommentTime() == null) {
				s2.setCommentTime("2009-10-10 00:00:00");
			}
			int result1 = s1.getCommentTime().compareTo(s2.getCommentTime());
			int result = (result1 < 0) ? 1 : -1;
			return result;
		}
	}

}
