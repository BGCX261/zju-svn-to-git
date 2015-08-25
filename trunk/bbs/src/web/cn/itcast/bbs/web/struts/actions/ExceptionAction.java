package cn.itcast.bbs.web.struts.actions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itcast.bbs.entities.log.ExceptionLog;
import cn.itcast.bbs.exception.runtime.ItcastException;
import cn.itcast.bbs.logic.helper.LogHelper;
import cn.itcast.bbs.web.WebConstants;
import cn.itcast.bbs.web.struts.action.base.BaseAction;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
@Controller("/exception")
public class ExceptionAction extends BaseAction {

	/**
	 * 记录异常并转到异常处理页面
	 */
	public ActionForward show(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try { // 方法不再抛出异常, 否则就可能会死循环
			Boolean flag = (Boolean) request.getAttribute(WebConstants.REQUEST_KEY_HAS_EXCEPTIONACTION);
			// 通过一个标志预防死循环, 预防return那儿还有可能抛异常, 或是其他未想到的情况
			if (flag != null && flag) {
				saveExceptionLog(new ItcastException("在ExceptionAction中产生死循环!"), request);
				return null;
			}

			Exception ex = (Exception) request.getAttribute(Globals.EXCEPTION_KEY);
			saveExceptionLog(ex, request);
			request.setAttribute("exception", ex);
		} catch (Exception e) {
			try {
				saveExceptionLog(e, request);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return null;
		}

		request.setAttribute(WebConstants.REQUEST_KEY_HAS_EXCEPTIONACTION, true);
		return mapping.findForward("show");
	}

	/**
	 * 权限不足异常处理页面
	 */
	public ActionForward permissionDeniedUI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Exception ex = (Exception) request.getAttribute(Globals.EXCEPTION_KEY);
		request.setAttribute("exception", ex);
		return mapping.findForward("permissionDenied");
	}

	/**
	 * 生成并保存ExcpetionLog
	 * 
	 * @param ex
	 * @param request
	 */
	private void saveExceptionLog(Exception ex, HttpServletRequest request) {
		ExceptionLog elog = new ExceptionLog();
		elog.setLogTime(new Date());
		elog.setOperator(getCurrentUser(request));
		elog.setOperIpAddr(request.getRemoteAddr());

		if (ex == null) {
			elog.setComment("<没有Exception信息>");
		} else {
			elog.setClassName(ex.getClass().getName());
			elog.setSummary(ex.getMessage());
			elog.setDetailMessage(LogHelper.getDetailMessage(ex));
		}

		logService.saveExceptionLog(elog);

		// 替换为可以在网页上显示的换行与空白
		String detailMessage = elog.getDetailMessage()//
				.replace("\r\n", "<br>")//
				.replace("\t", "<span style='width: 30px;'></span>");
		request.setAttribute("detailMessage", detailMessage);
		request.setAttribute("exceptionLog", elog);
	}
}
