package cn.itcast.bbs.web.struts.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import cn.itcast.bbs.web.struts.forms.base.BaseActionForm;
import cn.itcast.bbs.web.struts.utils.ActionFormDate;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
@SuppressWarnings("serial")
public class VoteForm extends BaseActionForm {
	private int topicId;
	private String title;
	private boolean multiple = false;
	private ActionFormDate closeTime = new ActionFormDate();
	// FIXME request.getparameterValues()没有声明是有顺的, 通过做实验是知道有顺的. 但不应依赖他, 应使用索引的属性
	private String[] items = {};

	private int[] voteItemIds; // 投票时用

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if (StringUtils.isBlank(title)) {
			errors.add("title", new ActionMessage("请输入投票的主题", false));
		}

		int itemsCount = 0;
		for (String item : items) { // 空白的字符串的是无效的
			if (StringUtils.isNotBlank(item)) {
				itemsCount++;
			}
		}
		if (itemsCount < 2) {
			errors.add("title", new ActionMessage("请至少提供2项投票项", false));
		}
		return errors;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isMultiple() {
		return multiple;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	public ActionFormDate getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(ActionFormDate closeTime) {
		this.closeTime = closeTime;
	}

	public String[] getItems() {
		return items;
	}

	public void setItems(String[] items) {
		this.items = items;
	}

	public int[] getVoteItemIds() {
		return voteItemIds;
	}

	public void setVoteItemIds(int[] voteItemIds) {
		this.voteItemIds = voteItemIds;
	}

}
