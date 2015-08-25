package cn.itcast.bbs.web.helper;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import cn.itcast.bbs.cfg.SystemGlobals;
import cn.itcast.bbs.web.WebConstants;

public class WebArticleHelper {

	/**
	 * 检查本次发表文章时间与上一次发表文章时间之间的间隔, 是否没有超过了规定的最小发帖的间隔时间,
	 * 
	 * @param request
	 * @return
	 */
	public static boolean checkPostDelay(HttpServletRequest request) {
		Long lastPostTime = (Long) request.getSession().getAttribute(WebConstants.SESSION_KEY_LAST_POST_TIME);
		if (lastPostTime != null) {
			int delay = SystemGlobals.getSettings().getPostDelay() * 1000;
			if (System.currentTimeMillis() < (lastPostTime.longValue() + delay)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 刷新最后发帖时间
	 * 
	 * @param request
	 */
	public static void refreshLastPostTime(HttpServletRequest request) {
		request.getSession().setAttribute(//
				WebConstants.SESSION_KEY_LAST_POST_TIME,//
				System.currentTimeMillis());
	}

	// ====================================

	/**
	 * 空白的html标签,如果内容中只含有这些标签,则认为内容是空白的
	 */
	public static final String[] HTML_EMPTY_TAGS = new String[] { "<br>", "&nbsp;", "<br/>", "<br />" };

	/**
	 * 使用fckeditor发表的文章内容是html格式的, 此方法用于判断发表的文章内容在显示时是否是空白.<br>
	 * 例如只含有 '<br>', '&nbsp;' 等空白字符,则认为内容为空白.<br>
	 * 比较时会忽略标签的大小写
	 * 
	 * @param content
	 * @return
	 */
	public static boolean isHtmlContentEmpty(String content) {
		if (StringUtils.isBlank(content)) {
			return true;
		}

		String html = content.trim();

		// TODO 对 <br> &nbsp; 等的判断,
		// 比较时注意大小写的问题, 都转成小写后再进行比较
		String temp = html.toLowerCase();
		for (String emptyTag : HTML_EMPTY_TAGS) {
			temp = temp.replace(emptyTag, "");
		}

		if (temp.trim().length() == 0) {
			return true;
		}

		return false;
	}
	// ====================================
}
