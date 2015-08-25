package cn.itcast.bbs.web.struts.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import cn.itcast.bbs.cfg.Settings;
import cn.itcast.bbs.cfg.SystemGlobals;
import cn.itcast.bbs.web.struts.forms.base.BaseActionForm;

/**
 * 和{@link SystemConfig}一样
 * 
 * @author 传智播客.汤阳光 Jul 7, 2008
 */
@SuppressWarnings("serial")
public class SettingsForm extends BaseActionForm {
	private Settings cfg = SystemGlobals.getSettings().clone();

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

		// ValidateUtils vu = new ValidateUtils();
		// ActionErrors errors = vu.validateRequited(null, this,//
		// new String[] { "name", "description" }, //
		// new String[] { "论坛名称", "论坛描述" });
		//		
		// // 如果页面中数字的格式输入的不正确, 在封装到form bean 时转换的值是 0, 并不报错
		// // 给数字值都指定一个合理的范围, 值在范围之外, 就有可能是输入错误造成的.
		// // 如果想改成范围之外的值, 应直接修改配置文件
		//		
		// vu.validateIntRange(errors, this, //
		// new String[] { "usersPerPage", "topicsPerPage", "postsPerPage",//
		// "searchResultPerPage" },//
		// new int[] { 1, 1000, 1, 1000, 1, 1000, 1, 1000 }, //
		// new String[] { "每页显示用户数量", "每页显示主题数量", "每页显示回复数量", "每页显示搜索结果数量" });
		//		
		// vu.validateIntRange(errors, this, //
		// new String[] { "postDelay" }, //
		// new int[] { 0, 10000 }, //
		// new String[] { "两次发帖的间隔时间" });
		//		
		// vu.validateIntRange(errors, this, //
		// new String[] { "avatarMaxFileSize", "avatarMaxWidth", "avatarMaxHeight" }, //
		// new int[] { 1, 1024 * 1024, 20, 500, 20, 500 }, //
		// new String[] { "头像文件的最大大小", "头像的最大宽度(像素)", "头像的最大高度(像素)" });
		//		
		// vu.validateIntRange(errors, this, //
		// new String[] { "attachmentMaxAmount", "attachmentMaxFileSize" }, //
		// new int[] { 0, 1024, 1, 100 * 1024 * 1024 }, //
		// new String[] { "一篇文章中最多允许的附件的数量", "单个附件的最大大小" });
		//
		// return errors;

		return null;
	}

	public Settings getCfg() {
		return cfg;
	}

	public void setCfg(Settings cfg) {
		this.cfg = cfg;
	}

}
