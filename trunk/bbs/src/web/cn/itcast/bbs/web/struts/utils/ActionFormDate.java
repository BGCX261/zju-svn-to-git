package cn.itcast.bbs.web.struts.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 重写了{@link #toString()}方法, 以便在form bean验证失败后在输入页面中显示期望的时间格式
 * 
 * 
 * @author 汤阳光 Nov 28, 2008
 */
@SuppressWarnings("serial")
public class ActionFormDate extends Date {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 默认使用的 pattern 为 yyyy-MM-dd
	 */
	public ActionFormDate() {}

	/**
	 * 使用指定的 pattern 格式化日期
	 * 
	 * @param pattern
	 */
	public ActionFormDate(String pattern) {
		sdf = new SimpleDateFormat(pattern);
	}

	public ActionFormDate(Date date) {
		super(date.getTime());
	}

	@Override
	public String toString() {
		return sdf.format(this);
	}

}
