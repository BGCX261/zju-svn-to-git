/**
 * AllArticleViewAction.java        2009-12-19 下午01:26:23
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.action.frontMenu;

import java.util.Collections;
import java.util.Comparator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.jsict.base.action.BaseListAction;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.jszju.domain.ArticleContentDomain;
import com.jsict.jszju.form.ArticleContentListForm;
import com.jsict.jszju.service.IArticleContentService;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class AllArticleViewAction extends BaseListAction {
	
	public static final String VIEWARTICLE = "viewarticle";
	
	private IArticleContentService	articleContentService;
	
	public void setArticleContentService(
			IArticleContentService articleContentService) {
		this.articleContentService = articleContentService;
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
		
		ArticleContentListForm artform=(ArticleContentListForm)form;
		String channelId=request.getParameter("channelId");
		String actionType=request.getParameter("actionType");
		request.setAttribute("channelId", channelId);
		if (VIEWARTICLE.equalsIgnoreCase(actionType)) {
			
			initList(request,artform,channelId);
			return mapping.findForward("view");
		}
		return mapping.findForward("goto");
	}
	
	 private void initList(HttpServletRequest request,
			 ArticleContentListForm form,String channelId) throws SystemException,
				ApplicationException {
		PagedList<ArticleContentDomain> list = articleContentService
				.getArticleContentPagedListByChannelId(getEntityFilter(), getPageNo(),
						getPageSize(),channelId);
		Collections.sort(list, new ArticleCotentListComparator());
		form.setResultList(list);
		setPageInfo(request, (PagedList) list); //设置分页信息
	}
	 
	 class ArticleCotentListComparator implements Comparator {
			public int compare(Object o1, Object o2) {
				ArticleContentDomain s1 = (ArticleContentDomain) o1;
				ArticleContentDomain s2 = (ArticleContentDomain) o2;
				if (s1.getInputData() == null) {
					s1.setInputData("2009-10-10 00:00:00");
				}
				if (s2.getInputData() == null) {
					s2.setInputData("2009-10-10 00:00:00");
				}
				int result1 = s1.getInputData().compareTo(s2.getInputData());
				int result = (result1 < 0) ? 1 : -1;
				return result;
			}
		}



}
