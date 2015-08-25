package cn.itcast.bbs.web.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import cn.itcast.bbs.entities.User;
import cn.itcast.bbs.entities.privilege.Action;
import cn.itcast.bbs.entities.privilege.Resource;
import cn.itcast.bbs.logic.helper.PrivilegeController;
import cn.itcast.bbs.web.helper.WebCommonHelper;

@SuppressWarnings("serial")
public class PrivilegeTag extends TagSupport {

	private String resource;
	private String action;

	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
		User user = WebCommonHelper.getLoggedOnUser(req);
		if (user == null) { // 未登录
			return SKIP_BODY;
		}

		try {
			Resource r = Resource.valueOf(resource);
			Action a = Action.valueOf(action);
			if (PrivilegeController.isUserHasPermission(user, r, a)) {
				return EVAL_BODY_INCLUDE;
			}
		} catch (RuntimeException e) {
			// do nothing
		}

		return SKIP_BODY;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
