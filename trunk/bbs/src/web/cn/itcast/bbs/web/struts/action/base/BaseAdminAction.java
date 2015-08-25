package cn.itcast.bbs.web.struts.action.base;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.itcast.bbs.entities.log.OperationLog;
import cn.itcast.bbs.entities.log.OperationLog.OperationLogType;

/**
 * @author 传智播客.汤阳光 Jul 7, 2008
 */
@SuppressWarnings("unchecked")
public class BaseAdminAction extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// if (!checkAdmin(request)) {
		// log.info("请您登陆管理员账号, 转到登陆页面");
		// throw new ItcastIllegalAdminAccessException();
		// }

		// 记录管理痕迹
		String methodName = getMethodName(mapping, form, request, response,//
				getParameter(mapping, form, request, response));

		// 转到表单页面的不记录
		if (methodName != null //
				&& !methodName.endsWith("list") //
				&& !methodName.endsWith("info")//
				&& !methodName.endsWith("UI")) {

			// 记录访问的Action与方法名
			StringBuffer sbf = new StringBuffer()//
					.append("ActionClass=").append(this.getClass().getName()).append(", ")//
					.append("methodName=").append(methodName).append(".\n")//
					.append("parameters={");

			Map parameters = request.getParameterMap();
			for (Object name : parameters.keySet()) {
				Object[] value = (Object[]) parameters.get(name);
				sbf.append(String.valueOf(name)).append("=").append(Arrays.toString(value)).append(", ");
			}

			String comment = sbf.append("}").toString();

			OperationLog olog = new OperationLog();
			olog.setType(OperationLogType.MANAGE_TRACE);
			olog.setComment(comment);
			olog.setLogTime(new Date());
			olog.setOperator(getCurrentUser(request));
			olog.setOperIpAddr(request.getRemoteAddr());

			logService.saveOperationLog(olog); // Save log
		}

		return super.execute(mapping, form, request, response);
	}

}
