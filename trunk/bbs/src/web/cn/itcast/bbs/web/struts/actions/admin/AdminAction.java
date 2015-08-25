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
import cn.itcast.bbs.web.helper.WebCommonHelper;
import cn.itcast.bbs.web.struts.action.base.BaseAdminAction;

/**
 * @author 传智播客.汤阳光 Jun 6, 2008
 */
@Controller("/admin")
@Privilege(resource = Resource.SYSTEM, action = Action.SYSTEM_MANAGE)
public class AdminAction extends BaseAdminAction {

	/**
	 * 默认显示后台首页
	 */
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("index");
	}

	/**
	 * 系统统计信息
	 */
	public ActionForward info(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WebCommonHelper.preSystemStatus(request, systemService);
		return mapping.findForward("info");
	}
}
