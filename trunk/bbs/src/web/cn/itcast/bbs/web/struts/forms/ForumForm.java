package cn.itcast.bbs.web.struts.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import cn.itcast.bbs.web.struts.forms.base.BaseActionForm;

/**
 * @author 传智播客.汤阳光 Jul 7, 2008
 */
@SuppressWarnings("serial")
public class ForumForm extends BaseActionForm {
	private String name;
	private String description;
	private int categoryId;

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if (StringUtils.isBlank(name)) {
			errors.add("name", new ActionMessage("请输入版面名称", false));
		}
		return errors;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

}
