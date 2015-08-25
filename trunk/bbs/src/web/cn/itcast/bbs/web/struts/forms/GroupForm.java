package cn.itcast.bbs.web.struts.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import cn.itcast.bbs.web.struts.forms.base.BaseActionForm;

/**
 * @author 传智播客.汤阳光 Jul 7, 2008
 */
@SuppressWarnings("serial")
public class GroupForm extends BaseActionForm {
	private String name;
	private String description;

	private int[] rolesId;

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if (name == null || name.trim().length() == 0) {
			errors.add("name", new ActionMessage("名称不能为空", false));
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

	public int[] getRolesId() {
		return rolesId;
	}

	public void setRolesId(int[] rolesId) {
		this.rolesId = rolesId;
	}

}
