package cn.itcast.bbs.web.struts.action.base;

import java.lang.reflect.Method;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import cn.itcast.bbs.entities.User;
import cn.itcast.bbs.entities.article.Category;
import cn.itcast.bbs.exception.runtime.ItcastException;
import cn.itcast.bbs.exception.runtime.ItcastPermissionDeniedException;
import cn.itcast.bbs.service.SystemService;
import cn.itcast.bbs.service.UserService;
import cn.itcast.bbs.service.article.ArticleService;
import cn.itcast.bbs.service.article.AttachmentService;
import cn.itcast.bbs.service.article.CategoryService;
import cn.itcast.bbs.service.article.ForumService;
import cn.itcast.bbs.service.article.ReplyService;
import cn.itcast.bbs.service.article.TopicService;
import cn.itcast.bbs.service.article.VoteService;
import cn.itcast.bbs.service.log.LogService;
import cn.itcast.bbs.service.privilege.GroupService;
import cn.itcast.bbs.service.privilege.PrivilegeService;
import cn.itcast.bbs.service.privilege.RoleService;
import cn.itcast.bbs.service.search.SearchService;
import cn.itcast.bbs.web.WebConstants;
import cn.itcast.bbs.web.helper.WebCommonHelper;

/**
 * FIXME 防止表单重复提交(后退后再次提交)
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
public class BaseAction extends DispatchAction {
	protected static Log log = LogFactory.getLog(BaseAction.class);

	@Resource protected UserService userService;
	@Resource protected SystemService systemService;
	@Resource protected LogService logService;
	
	@Resource protected GroupService groupService;
	@Resource protected RoleService roleService;
	@Resource protected PrivilegeService privilegeService;

	@Resource protected CategoryService categoryService;
	@Resource protected ForumService forumService;
	
	@Resource protected ArticleService articleService;
	@Resource protected TopicService topicService;
	@Resource protected ReplyService replyService;
	
	@Resource protected AttachmentService attachmentService;
	@Resource protected VoteService voteService;
	@Resource protected SearchService searchService;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return super.execute(mapping, form, request, response);
		}
		// 如果没有权限执行某个方法
		catch (ItcastPermissionDeniedException e) {
			// 1. 如果未登陆, 转到登陆界面
			if (!WebCommonHelper.isLoggedOn(request)) {
				// 可以在登陆后再转到登陆前的页面, 这个页面地址可以放到 request 作用域中;
				// 或是不放, 这将使用出现异常时访问的页面
				String returnPath = (String) request.getAttribute("returnPath");
				WebCommonHelper.toLogin(request, response, returnPath);
				return null;
			}

			// 2. 如果已登陆, 抛出权限不足异常
			throw e;
		}
	}

	/**
	 * @param request
	 * @return 当前存在session作用域(登陆)的用户
	 */
	protected User getCurrentUser(HttpServletRequest request) {
		return WebCommonHelper.getLoggedOnUser(request);
	}

	/**
	 * 更新application作用域中的categories集合
	 * 
	 * @param request
	 */
	protected void setCategoriesInRequestScope(HttpServletRequest request) {
		List<Category> categories = categoryService.findAll();
		request.setAttribute(WebConstants.SCOPE_ATTR_CATEGORIES, categories);
	}

	/**
	 * 验证FormBean, 通过调用 {@link ActionForm#validate(ActionMapping, HttpServletRequest)}方法
	 * 
	 * @param form
	 * @param mapping
	 * @param request
	 * @return 表单是否验证失败
	 */
	protected boolean validateFailed(ActionForm form, ActionMapping mapping, HttpServletRequest request) {
		ActionMessages errors = form.validate(mapping, request);
		if (errors != null && errors.size() > 0) {
			this.saveErrors(request, errors);
			return true;
		}
		return false;
	}

	/**
	 * 验证formbean, 通过指定的验证方法, 要求这个验证方法的签名要和<br>
	 * {@link ActionForm#validate(ActionMapping, HttpServletRequest)}方法的签名一致
	 * 
	 * @param form
	 * @param validateMethodName
	 *            验证方法
	 * @param mapping
	 * @param request
	 * @return
	 */
	protected boolean validateFailed(ActionForm form, String validateMethodName, ActionMapping mapping, HttpServletRequest request) {
		try {
			Method method = form.getClass().getMethod(validateMethodName, ActionMapping.class, HttpServletRequest.class);
			ActionMessages errors = (ActionMessages) method.invoke(form, mapping, request);
			if (errors.size() > 0) {
				this.saveErrors(request, errors);
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new ItcastException(e);
		}
	}

	/**
	 * 添加一个错误消息, 并把这个错误信息的集合放到request作用域
	 * 
	 * @param request
	 * @param property
	 * @param key
	 * @param resourse
	 * @return
	 */
	protected ActionMessages addError(HttpServletRequest request, String property, String key, boolean resourse) {
		ActionMessages errors = new ActionMessages();
		errors.add(property, new ActionMessage(key, resourse));
		addErrors(request, errors);
		return errors;
	}

	/**
	 * 获取布尔型参数值
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	protected boolean getBoolParam(HttpServletRequest request, String name) {
		return "true".equals(request.getParameter(name));
	}

	/**
	 * 获取int型参数值. 如果没有传递, 则返回 defaultValue
	 * 
	 * @param request
	 * @param name
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	protected int getIntParam(HttpServletRequest request, String name, int defaultValue) {
		try {
			return Integer.parseInt(request.getParameter(name));
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}
}
