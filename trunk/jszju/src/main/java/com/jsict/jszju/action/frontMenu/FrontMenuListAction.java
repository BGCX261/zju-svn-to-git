/**
 * FrontMenuListAction.java        2009-11-10 下午08:54:00
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.jszju.action.frontMenu;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

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
import com.jsict.jszju.service.IArticleContentService;
import com.jsict.platform.form.MenuForm;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class FrontMenuListAction extends BaseListAction {

	public static final String DYNAMIC = "dynamic";
	public static final String FILE = "file";
	public static final String ZJUCOMMU = "zjucommu";
	public static final String PIC = "pic";
	public static final String SUBZJU = "subzju";
	public static final String MEMBERHOME = "memberhome";
	public static final String FIRSTPAGE = "firstpage";

	public static final String PICTURE = "picture";
	public static final String VIDEO = "video";
	public static final String OLDZJUER = "oldzjuer";
	public static final String YOUNGZJUER = "youngzjuer";
	public static final String MEDICAL = "medical";

	private IArticleContentService articleContentService;

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
		String actionType = request.getParameter("actionType");
		HttpSession session = request.getSession();
		MenuForm formbean = (MenuForm) session.getAttribute("menuForm");
		if (DYNAMIC.equals(actionType)) {
			session.setAttribute("dynamicMenuForm", formbean);

			PagedList<ArticleContentDomain> list = articleContentService
					.getArticleContentPagedList(getEntityFilter(), null, null);
			Collections.sort(list, new ArticleContentListComparator());
			session.setAttribute("dynamicList", list);
			return mapping.findForward(DYNAMIC);
		} else if (FILE.equals(actionType)) {
			session.setAttribute("fileMenuForm", formbean);
			PagedList<ArticleContentDomain> list = articleContentService
					.getArticleContentPagedList(getEntityFilter(), null, null);
			Collections.sort(list, new ArticleContentListComparator());
			session.setAttribute("fileList", list);
			return mapping.findForward(FILE);
		} else if (ZJUCOMMU.equals(actionType)) {
			session.setAttribute("zjucommuMenuForm", formbean);
			PagedList<ArticleContentDomain> list = articleContentService
					.getArticleContentPagedList(getEntityFilter(), null, null);
			Collections.sort(list, new ArticleContentListComparator());
			session.setAttribute("zjucommuList", list);

			return mapping.findForward(ZJUCOMMU);
		} else if (PIC.equals(actionType)) {
			session.setAttribute("picMenuForm", formbean);

			String channelId = "3";
			PagedList<ArticleContentDomain> list = articleContentService
					.getArticleContentPagedListByChannelId(getEntityFilter(),
							getPageNo(), getPageSize(), channelId);
			session.setAttribute("picList", list);
			return mapping.findForward(PIC);
		} else if (SUBZJU.equals(actionType)) {
			session.setAttribute("subzjuMenuForm", formbean);

			String channelId = "4";
			PagedList<ArticleContentDomain> list = articleContentService
					.getArticleContentPagedListByChannelId(getEntityFilter(),
							getPageNo(), getPageSize(), channelId);
			session.setAttribute("subzjuList", list);

			return mapping.findForward(SUBZJU);
		} else if (MEMBERHOME.equals(actionType)) {
			session.setAttribute("memberhomeMenuForm", formbean);
			PagedList<ArticleContentDomain> list = articleContentService
					.getArticleContentPagedList(getEntityFilter(), null, null);
			session.setAttribute("memberhomeList", list);

			return mapping.findForward(MEMBERHOME);
		} else if (FIRSTPAGE.equals(actionType)) {
			session.setAttribute("firstpageMenuForm", formbean);

			PagedList<ArticleContentDomain> list = articleContentService
					.getArticleContentPagedList(getEntityFilter(), null, null);
			Collections.sort(list, new ArticleContentListComparator());
			session.setAttribute("firstpageList", list);
			return mapping.findForward(FIRSTPAGE);
		}

		else if (PICTURE.equals(actionType)) {
			session.setAttribute("pictureMenuForm", formbean);
			PagedList<ArticleContentDomain> list = articleContentService
					.getArticleContentPagedList(getEntityFilter(), null, null);
			Collections.sort(list, new ArticleContentListComparator());
			session.setAttribute("pictureList", list);
			return mapping.findForward(PICTURE);
		} else if (VIDEO.equals(actionType)) {
			session.setAttribute("videoMenuForm", formbean);
			PagedList<ArticleContentDomain> list = articleContentService
					.getArticleContentPagedList(getEntityFilter(), null, null);
			Collections.sort(list, new ArticleContentListComparator());
			session.setAttribute("videoList", list);
			return mapping.findForward(VIDEO);
		} else if (OLDZJUER.equals(actionType)) {
			session.setAttribute("oldzjuerMenuForm", formbean);
			PagedList<ArticleContentDomain> list = articleContentService
					.getArticleContentPagedList(getEntityFilter(), null, null);
			Collections.sort(list, new ArticleContentListComparator());
			session.setAttribute("oldzjuerList", list);
			return mapping.findForward(OLDZJUER);
		} else if (YOUNGZJUER.equals(actionType)) {
			session.setAttribute("youngzjuerMenuForm", formbean);
			PagedList<ArticleContentDomain> list = articleContentService
					.getArticleContentPagedList(getEntityFilter(), null, null);
			Collections.sort(list, new ArticleContentListComparator());
			session.setAttribute("youngzjuerList", list);
			return mapping.findForward(YOUNGZJUER);
		} else if (MEDICAL.equals(actionType)) {
			session.setAttribute("medicalMenuForm", formbean);
			PagedList<ArticleContentDomain> list = articleContentService
					.getArticleContentPagedList(getEntityFilter(), null, null);
			Collections.sort(list, new ArticleContentListComparator());
			session.setAttribute("medicalList", list);
			return mapping.findForward(MEDICAL);
		} else {
			session.setAttribute("menuForm", formbean);
			return mapping.getInputForward();
		}
	}

	class ArticleContentListComparator implements Comparator {
		public int compare(Object o1, Object o2) {
			ArticleContentDomain s1 = (ArticleContentDomain) o1;
			ArticleContentDomain s2 = (ArticleContentDomain) o2;
			if(s1.getInputData()==null)
			{
				s1.setInputData("2009-10-10 00:00:00");
			}
			if(s2.getInputData()==null)
			{
				s2.setInputData("2009-10-10 00:00:00");
			}
			int result1=s1.getInputData().compareTo(s2.getInputData());
			int result = (result1<0) ? 1 : -1;
			return result;
		}
	}

}
