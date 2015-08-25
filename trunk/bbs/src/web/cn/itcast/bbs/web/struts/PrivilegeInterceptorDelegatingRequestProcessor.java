package cn.itcast.bbs.web.struts;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.web.struts.DelegatingRequestProcessor;

import cn.itcast.bbs.ExecutionContext;
import cn.itcast.bbs.entities.User;
import cn.itcast.bbs.entities.privilege.Privilege;
import cn.itcast.bbs.exception.runtime.ItcastException;
import cn.itcast.bbs.exception.runtime.ItcastPermissionDeniedException;
import cn.itcast.bbs.logic.helper.PrivilegeController;

public class PrivilegeInterceptorDelegatingRequestProcessor extends DelegatingRequestProcessor {

	private static final Class<?>[] parameterTypes = { ActionMapping.class, //
			ActionForm.class, //
			HttpServletRequest.class, //
			HttpServletResponse.class };

	@Override
	protected ActionForward processActionPerform(HttpServletRequest request, HttpServletResponse response, Action action, ActionForm form,
			ActionMapping mapping) throws IOException, ServletException {

		// 1，获取要调用的方法
		String methodName = "execute";
		if (DispatchAction.class.isAssignableFrom(action.getClass())) {
			methodName = request.getParameter(mapping.getParameter());
			if (methodName == null) {
				methodName = "unspecified";
			}
		}

		Method method = null;
		try {
			method = action.getClass().getDeclaredMethod(methodName, parameterTypes);
		} catch (NoSuchMethodException e) {
			throw new ItcastException(e);
		} catch (Exception e) {
			throw new ItcastException(e);
		}

		// 2，得到这个方法所要求的权限
		Privilege privilege = method.getAnnotation(Privilege.class);
		if (privilege == null) {
			privilege = action.getClass().getAnnotation(Privilege.class);
		}

		if (privilege != null) {
			// 3，查看用户是否有这个权限
			User currentUser = ExecutionContext.get().getCurrentUser();
			if (!PrivilegeController.isUserHasPermission(//
					currentUser, //
					privilege.resource(), //
					privilege.action())) {
				// 4，如果没有权限，抛出异常
				String msg = "您没有权限【resource=" + privilege.resource() + ",action=" + privilege.action() //
						+ "】访问方法【" + action.getClass() + "." + methodName + "】";
				throw new ItcastPermissionDeniedException(msg);
			}
		}

		return super.processActionPerform(request, response, action, form, mapping);

	}

	// private void checkPrililege() {
	// // 1，获取要调用的方法
	// // 2，得到这个方法要求的权限
	// // 3，查看用户是否有这个权限
	// // 4，如果没有权限，抛出异常
	// }
}
