package cn.itcast.bbs.web.struts.actions.article;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itcast.bbs.entities.article.Topic;
import cn.itcast.bbs.entities.article.Vote;
import cn.itcast.bbs.entities.article.VoteItem;
import cn.itcast.bbs.entities.article.VoteRecord;
import cn.itcast.bbs.entities.privilege.Action;
import cn.itcast.bbs.entities.privilege.Privilege;
import cn.itcast.bbs.entities.privilege.Resource;
import cn.itcast.bbs.web.struts.action.base.BaseAction;
import cn.itcast.bbs.web.struts.forms.VoteForm;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
@Controller("/vote")
public class VoteAction extends BaseAction {

	/**
	 * 投票添加页面
	 */
	@Privilege(resource = Resource.VOTE, action = Action.CREATE)
	public ActionForward addUI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		setCategoriesInRequestScope(request);
		return mapping.findForward("add");
	}

	/**
	 * 投票添加 FIXME 添加页面中的结束时间的选择应可以精确到时分秒
	 */
	@Privilege(resource = Resource.VOTE, action = Action.CREATE)
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (validateFailed(form, mapping, request)) { // 验证表单
			return addUI(mapping, form, request, response);
		}

		VoteForm voteForm = (VoteForm) form;
		Topic topic = topicService.getTopic(voteForm.getTopicId());

		// 准备 VoteItem s
		List<VoteItem> voteItems = new ArrayList<VoteItem>();
		for (String itemContent : voteForm.getItems()) {
			if (StringUtils.isNotBlank(itemContent)) {
				voteItems.add(new VoteItem(itemContent));
			}
		}

		Vote vote = new Vote();
		vote.setTitle(voteForm.getTitle());
		vote.setCloseTime(voteForm.getCloseTime());
		vote.setMultiple(voteForm.isMultiple());
		vote.setCreationTime(new Date());
		vote.setTopic(topic);
		vote.setVoteItems(voteItems);
		voteService.addNew(vote);

		ActionForward af = mapping.findForward("showTopic");
		return new ActionForward(af.getPath() + "&pageNum=1&id=" + voteForm.getTopicId(), af.getRedirect());
	}

	/**
	 * 投票
	 */
	@Privilege(resource = Resource.VOTE, action = Action.VOTE)
	public ActionForward vote(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		VoteForm voteForm = (VoteForm) form;
		List<VoteItem> voteItems = voteService.getVoteItems(voteForm.getVoteItemIds());

		if (voteItems.size() == 0) {
			addError(request, "voteItem", "请至少选择一个项目", false);
			ActionForward af = mapping.findForward("showTopic");
			return new ActionForward(af.getPath() + "&pageNum=1&id=" + voteForm.getTopicId(), false);
		}

		// FIXME 是否能进行投票的判断(测试时把下面注释掉, 否则不让投票)
		// Vote vote = voteItems.get(0).getVote();
		// if(!voteService.canVote(vote, getCurrentUser(request))){
		// addError(request, "vote", "您不能参与投票", false);
		// ActionForward af = mapping.findForward("showTopic");
		// return new ActionForward(af.getPath() + "&pageNum=1&id=" + voteForm.getTopicId(), false);
		// }

		VoteRecord voteRecord = new VoteRecord();
		voteRecord.setVoter(getCurrentUser(request));
		voteRecord.setIpAddr(request.getRemoteAddr());
		voteRecord.setVoteItems(new HashSet<VoteItem>(voteItems));
		voteRecord.setVoteTime(new Date());
		voteService.vote(voteRecord);

		ActionForward af = mapping.findForward("showTopic");
		return new ActionForward(af.getPath() + "&pageNum=1&id=" + voteForm.getTopicId(), true);
	}

	/**
	 * 投票删除
	 */
	@Privilege(resource = Resource.VOTE, action = Action.DELETE)
	public ActionForward delVote(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		VoteForm voteForm = (VoteForm) form;
		voteService.deleteVote(voteForm.getId());
		ActionForward af = mapping.findForward("showTopic");
		return new ActionForward(af.getPath() + "&pageNum=1&id=" + voteForm.getTopicId(), true);
	}

}
