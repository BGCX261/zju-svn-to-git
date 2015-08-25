package cn.itcast.bbs.web.struts.actions.article;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itcast.bbs.entities.PageInfo;
import cn.itcast.bbs.entities.search.SearchArgs;
import cn.itcast.bbs.entities.search.SearchableArticle;
import cn.itcast.bbs.web.struts.action.base.BaseAction;
import cn.itcast.bbs.web.struts.forms.SearchForm;

/**
 * @author 传智播客.汤阳光 Jul 7, 2008
 */
@Controller("/search")
public class SearchAction extends BaseAction {

	/**
	 * 搜索页面
	 */
	public ActionForward searchUI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SearchForm searchForm = (SearchForm) form;
		// 没有找到结果. 点击重新搜索转过来时, 关键字需要处理中文
		encodingQueryString(searchForm);
		return mapping.findForward("search");
	}

	/**
	 * 搜索
	 */
	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(validateFailed(form, mapping, request)){
			addError(request, "queryString", "请输入要搜索的内容", false);
			return mapping.findForward("search");
		}
		
		SearchForm searchForm = (SearchForm) form;
		encodingQueryString(searchForm);// 关键字需要处理中文

		SearchArgs args = new SearchArgs();
		BeanUtils.copyProperties(args, searchForm);
		PageInfo<SearchableArticle> page = searchService.search(args);

		request.setAttribute("pageInfo", page);
		request.setAttribute("searchArgs", args);
		return mapping.findForward("showResult");
	}

	/**
	 * 搜索的表单是使用get方式传递的数据,使用此方法可以处理SearchForm.queryString为正确的中文
	 * 
	 * @param searchForm
	 */
	private void encodingQueryString(SearchForm searchForm) throws Exception {
		String queryString = searchForm.getQueryString();
		queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
		searchForm.setQueryString(queryString);
	}
}
