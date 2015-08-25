package cn.itcast.bbs.web.struts.forms.base;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
@SuppressWarnings("serial")
// FIXME 验证所有让用户输入的字符串的长度
public class BaseActionForm extends ActionForm {
	protected int id;
	protected int pageNum;

	public int getId() {
		return id;
	}

	public int getPageNum() {
		return pageNum > 0 ? pageNum : 1;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
}
