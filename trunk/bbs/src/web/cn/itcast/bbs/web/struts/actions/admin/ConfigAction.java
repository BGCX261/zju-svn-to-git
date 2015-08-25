package cn.itcast.bbs.web.struts.actions.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itcast.bbs.cfg.Environment;
import cn.itcast.bbs.cfg.SystemGlobals;
import cn.itcast.bbs.entities.privilege.Action;
import cn.itcast.bbs.entities.privilege.Privilege;
import cn.itcast.bbs.entities.privilege.Resource;
import cn.itcast.bbs.web.WebConstants;
import cn.itcast.bbs.web.struts.action.base.BaseAdminAction;
import cn.itcast.bbs.web.struts.forms.SettingsForm;

/**
 * @author 传智播客.汤阳光 Jun 6, 2008
 */
@Controller("/admin/config")
@Privilege(resource = Resource.SYSTEM, action = Action.SYSTEM_MANAGE)
public class ConfigAction extends BaseAdminAction {

	/**
	 * 系统设置页面
	 */
	public ActionForward editUI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("edit");
	}

	/**
	 * 更新系统设置
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (validateFailed(form, mapping, request)) { // validate form bean
			return mapping.findForward("edit");
		}

		SettingsForm settingsForm = (SettingsForm) form;
		systemService.updateSettings(settingsForm.getCfg());

		// 更新 application 作用域中的settings
		request.getSession().getServletContext().setAttribute(//
				WebConstants.SCOPE_ATTR_SETTINGS,//
				SystemGlobals.getSettings());

		return mapping.findForward("showEditUI");
	}

	/**
	 * 列出的配置文件
	 */
	public ActionForward listFiles(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String appPath = SystemGlobals.getSettings().getApplicationPath();
		String configFilesPath = SystemGlobals.getValue(Environment.APPLICATION_CONFIG_PATH);

		List<File> files = new ArrayList<File>();
		File configDir = new File(configFilesPath);

		for (File f : configDir.listFiles()) {
			if (f.isFile()) {
				files.add(f);
			}
		}

		request.setAttribute("files", files);
		request.setAttribute("appPath", appPath);
		return mapping.findForward("listFiles");
	}

	/**
	 * 重新加载所有配置文件
	 */
	public ActionForward reloadFiles(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SystemGlobals.restart();

		// 更新 application 作用域中的settings
		request.getSession().getServletContext().setAttribute(//
				WebConstants.SCOPE_ATTR_SETTINGS,//
				SystemGlobals.getSettings());

		return mapping.findForward("showFiles");
	}

}
