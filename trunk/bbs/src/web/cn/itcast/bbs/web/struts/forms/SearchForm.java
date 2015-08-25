package cn.itcast.bbs.web.struts.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import cn.itcast.bbs.web.struts.forms.base.BaseActionForm;
import cn.itcast.bbs.web.struts.utils.ActionFormDate;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
@SuppressWarnings("serial")
public class SearchForm extends BaseActionForm {
	private boolean searchAuthorNameOnly = false;// 是否只是按作者昵称搜索文章
	private String queryString = "";// 搜索的字符串
	private String[] forumNames;// 在指定的版面中查询
	private ActionFormDate startDate;
	private ActionFormDate endDate;
	private boolean recreateAll = false;

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if (StringUtils.isBlank(queryString)) {
			errors.add("queryString", new ActionMessage("请输入要搜索的内容", false));
		}
		return errors;
	}

	public boolean isSearchAuthorNameOnly() {
		return searchAuthorNameOnly;
	}

	public void setSearchAuthorNameOnly(boolean searchAuthorNameOnly) {
		this.searchAuthorNameOnly = searchAuthorNameOnly;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String[] getForumNames() {
		return forumNames;
	}

	public void setForumNames(String[] forumNames) {
		this.forumNames = forumNames;
	}

	public ActionFormDate getStartDate() {
		return startDate;
	}

	public void setStartDate(ActionFormDate startDate) {
		this.startDate = startDate;
	}

	public ActionFormDate getEndDate() {
		return endDate;
	}

	public void setEndDate(ActionFormDate endDate) {
		this.endDate = endDate;
	}

	public boolean isRecreateAll() {
		return recreateAll;
	}

	public void setRecreateAll(boolean recreateAll) {
		this.recreateAll = recreateAll;
	}

}
