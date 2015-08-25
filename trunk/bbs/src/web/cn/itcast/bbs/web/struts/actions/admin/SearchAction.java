package cn.itcast.bbs.web.struts.actions.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itcast.bbs.entities.privilege.Action;
import cn.itcast.bbs.entities.privilege.Privilege;
import cn.itcast.bbs.entities.privilege.Resource;
import cn.itcast.bbs.web.struts.action.base.BaseAdminAction;
import cn.itcast.bbs.web.struts.forms.SearchForm;

/**
 * @author 传智播客.汤阳光 Jul 19, 2008
 */
@Controller("/admin/search")
@Privilege(resource = Resource.SYSTEM, action = Action.SYSTEM_MANAGE)
public class SearchAction extends BaseAdminAction {

	public ActionForward info(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int totalIndexDocs = searchService.getTotalIndexDocs();
		int totalArticles = systemService.getArticleCount();

		request.setAttribute("totalArticles", totalArticles);
		request.setAttribute("totalIndexDocs", totalIndexDocs);
		return mapping.findForward("info");
	}

	/** 重新创建索引*/
	public ActionForward recreateIndices(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SearchForm searchForm = (SearchForm) form;
		if (searchForm.isRecreateAll()) {
			searchService.recreateAllArticleIndices();
		} else {
			searchService.recreateArticleIndices(searchForm.getStartDate(), searchForm.getEndDate());
		}
		return mapping.findForward("showInfo");
	}

}
