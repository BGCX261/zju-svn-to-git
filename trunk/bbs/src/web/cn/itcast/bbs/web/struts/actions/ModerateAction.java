package cn.itcast.bbs.web.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itcast.bbs.entities.article.Reply;
import cn.itcast.bbs.entities.article.Topic;
import cn.itcast.bbs.entities.privilege.Action;
import cn.itcast.bbs.entities.privilege.Privilege;
import cn.itcast.bbs.entities.privilege.Resource;
import cn.itcast.bbs.web.struts.action.base.BaseAction;
import cn.itcast.bbs.web.struts.forms.ModerateForm;

@Controller("/moderate")
public class ModerateAction extends BaseAction {

	/**
	 * 更改主题的类型
	 */
	@Privilege(resource = Resource.TOPIC, action = Action.EDIT)
	public ActionForward changeTopicType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModerateForm moderateForm = (ModerateForm) form;
		Topic topic = topicService.getTopic(moderateForm.getId());
		topic.setType(moderateForm.getTopicType());

		topicService.updateTopic(topic);
		ActionForward af = mapping.findForward("showTopic");
		return new ActionForward(af.getPath() + "&pageNum=1&id=" + topic.getId(), af.getRedirect());
	}

	/**
	 * 更改主题的状态
	 */
	@Privilege(resource = Resource.TOPIC, action = Action.EDIT)
	public ActionForward changeTopicStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModerateForm moderateForm = (ModerateForm) form;
		Topic topic = topicService.getTopic(moderateForm.getId());
		topic.setStatus(moderateForm.getTopicStatus());

		topicService.updateTopic(topic);
		ActionForward af = mapping.findForward("showTopic");
		return new ActionForward(af.getPath() + "&pageNum=1&id=" + topic.getId(), af.getRedirect());
	}

	/**
	 * 主题删除页面
	 */
	@Privilege(resource = Resource.TOPIC, action = Action.DELETE)
	public ActionForward delTopicsUI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModerateForm moderateForm = (ModerateForm) form;
		List<Topic> topics = topicService.getTopics(moderateForm.getIds());
		request.setAttribute("topics", topics);
		request.setAttribute("action", "delTopics");
		setCategoriesInRequestScope(request);
		return mapping.findForward("topicManage");
	}

	/**
	 * 主题删除
	 */
	@Privilege(resource = Resource.TOPIC, action = Action.DELETE)
	public ActionForward delTopics(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModerateForm moderateForm = (ModerateForm) form;
		topicService.deleteTopics(moderateForm.getIds(), moderateForm.getReason());

		ActionForward af = mapping.findForward("showForum");
		return new ActionForward(af.getPath() + "&pageNum=1&id=" + moderateForm.getForumId(), af.getRedirect());
	}

	/**
	 * 主题移动页面
	 */
	@Privilege(resource = Resource.TOPIC, action = Action.MOVE)
	public ActionForward moveTopicsUI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModerateForm moderateForm = (ModerateForm) form;
		List<Topic> topics = topicService.getTopics(moderateForm.getIds());

		setCategoriesInRequestScope(request);
		request.setAttribute("topics", topics);
		request.setAttribute("action", "moveTopics");
		return mapping.findForward("topicManage");
	}

	/**
	 * 主题移动
	 */
	@Privilege(resource = Resource.TOPIC, action = Action.MOVE)
	public ActionForward moveTopics(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModerateForm moderateForm = (ModerateForm) form;
		topicService.moveTopics(moderateForm.getIds(), moderateForm.getDestForumId(), moderateForm.getReason());

		ActionForward af = mapping.findForward("showForum");
		return new ActionForward(af.getPath() + "&pageNum=1&id=" + moderateForm.getForumId(), af.getRedirect());
	}

	/**
	 * 主题锁定页面
	 */
	@Privilege(resource = Resource.TOPIC, action = Action.EDIT)
	public ActionForward lockTopicsUI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModerateForm moderateForm = (ModerateForm) form;
		List<Topic> topics = topicService.getTopics(moderateForm.getIds());

		setCategoriesInRequestScope(request);
		request.setAttribute("topics", topics);
		request.setAttribute("action", "lockTopics");
		return mapping.findForward("topicManage");
	}

	/**
	 * 主题锁定
	 */
	@Privilege(resource = Resource.TOPIC, action = Action.EDIT)
	public ActionForward lockTopics(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModerateForm moderateForm = (ModerateForm) form;
		topicService.lockTopics(moderateForm.getIds(), moderateForm.getReason());

		ActionForward af = mapping.findForward("showForum");
		return new ActionForward(af.getPath() + "&pageNum=1&id=" + moderateForm.getForumId(), af.getRedirect());
	}

	/**
	 * 主题解锁页面
	 */
	@Privilege(resource = Resource.TOPIC, action = Action.EDIT)
	public ActionForward unlockTopicsUI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModerateForm moderateForm = (ModerateForm) form;
		List<Topic> topics = topicService.getTopics(moderateForm.getIds());

		setCategoriesInRequestScope(request);
		request.setAttribute("topics", topics);
		request.setAttribute("action", "unlockTopics");
		return mapping.findForward("topicManage");
	}

	/**
	 * 主题解锁
	 */
	@Privilege(resource = Resource.TOPIC, action = Action.EDIT)
	public ActionForward unlockTopics(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModerateForm moderateForm = (ModerateForm) form;
		topicService.unlockTopics(moderateForm.getIds(), moderateForm.getReason());

		ActionForward af = mapping.findForward("showForum");
		return new ActionForward(af.getPath() + "&pageNum=1&id=" + moderateForm.getForumId(), af.getRedirect());
	}

	// ---

	/**
	 * 回复删除页面
	 */
	@Privilege(resource = Resource.REPLY, action = Action.DELETE)
	public ActionForward delReplyUI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModerateForm moderateForm = (ModerateForm) form;
		Reply reply = replyService.getReply(moderateForm.getId());

		setCategoriesInRequestScope(request);
		request.setAttribute("reply", reply);
		return mapping.findForward("replyManage");
	}

	/**
	 * 回复删除
	 */
	@Privilege(resource = Resource.REPLY, action = Action.DELETE)
	public ActionForward delReply(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModerateForm moderateForm = (ModerateForm) form;
		int replyId = moderateForm.getId();
		replyService.deleteReplies(new int[] { replyId }, moderateForm.getReason());

		ActionForward af = mapping.findForward("showTopic");
		return new ActionForward(af.getPath() + "&pageNum=1&id=" + moderateForm.getTopicId(), af.getRedirect());
	}

}
