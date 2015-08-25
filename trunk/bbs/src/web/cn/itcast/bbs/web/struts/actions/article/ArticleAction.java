package cn.itcast.bbs.web.struts.actions.article;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itcast.bbs.entities.PageInfo;
import cn.itcast.bbs.entities.User;
import cn.itcast.bbs.entities.article.Article;
import cn.itcast.bbs.entities.article.Forum;
import cn.itcast.bbs.entities.article.Reply;
import cn.itcast.bbs.entities.article.Topic;
import cn.itcast.bbs.entities.privilege.Action;
import cn.itcast.bbs.entities.privilege.Privilege;
import cn.itcast.bbs.entities.privilege.Resource;
import cn.itcast.bbs.web.helper.WebArticleHelper;
import cn.itcast.bbs.web.struts.action.base.BaseAction;
import cn.itcast.bbs.web.struts.forms.ArticleForm;

@Controller("/article")
public class ArticleAction extends BaseAction {

	/**
	 * 主题显示
	 */
	// TODO 通过查询结果(postId)直接定位到回复所在页的具体位置
	public ActionForward showTopic(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArticleForm articleForm = (ArticleForm) form;
		Topic topic = topicService.getTopic(articleForm.getId());

		PageInfo<Reply> page = replyService.findRepliesByTopicId(articleForm.getId(), articleForm.getPageNum());
		// 如果是要查看的页码比超过总页码数, 则显示最后一页
		if (page.getCurrentPage() > 1 && page.getCurrentPage() > page.getTotalPage()) {
			page = replyService.findRepliesByTopicId(articleForm.getId(), page.getTotalPage());
		}

		// if (topic.getVote() != null) { // prepare vote.canVote
		// boolean canVote = getVoteService().canVote(topic.getVote(),//
		// getCurrentUser(request),//
		// request.getRemoteAddr());
		// boolean canCreateArticle = SecurityHelper.canCreateArticle(getCurrentUser(request));
		// topic.getVote().setCanVote(canVote && canCreateArticle);
		//			
		// if (ArticleHelper.canEdit(topic.getFirstPost(), getCurrentUser(request))) {
		// topic.getVote().setCanEdit(true); // 当前用户是否能修改投票
		// }
		// }

		topicService.incrementViewCount(articleForm.getId()); // increment view count

		request.setAttribute("page", page);
		request.setAttribute("topic", topic);
		return mapping.findForward("show");
	}

	/**
	 * 主题发表页面
	 */
	@Privilege(resource = Resource.TOPIC, action = Action.CREATE)
	public ActionForward addTopicUI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 这里不要使用request.getParameter来获得forumId,
		// 因为 form 是 multipart/form-data 编码类型,
		// FIXME ?? 如果是验证失败后转发过来的,使用request.getParameter就得不到参数了

		ArticleForm articleForm = (ArticleForm) form;
		Forum forum = forumService.getForum(articleForm.getForumId());

		setCategoriesInRequestScope(request);
		request.setAttribute("forum", forum);
		return mapping.findForward("saveTopic");
	}

	/**
	 * 主题发表(只是标题和内容)
	 */
	@Privilege(resource = Resource.TOPIC, action = Action.CREATE)
	public ActionForward addTopic(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (validateFailed(form, "validateTopic", mapping, request)) { // 验证表单
			return addTopicUI(mapping, form, request, response);
		}

		// 验证发帖时间间隔
		if (!WebArticleHelper.checkPostDelay(request)) {
			addError(request, "postDelay", "距上次发帖间隔时间太短, 您不能发表新文章. 请稍候再试.", false);
			return addTopicUI(mapping, form, request, response);
		}

		ArticleForm articleForm = (ArticleForm) form;
		Forum forum = forumService.getForum(articleForm.getForumId());
		User user = getCurrentUser(request);

		Topic topic = new Topic();
		topic.setAuthor(user);
		topic.setForum(forum);
		topic.setTitle(articleForm.getTitle());
		topic.setContent(articleForm.getContent());
		topic.setPostTime(new Date());
		topic.setIpAddr(request.getRemoteAddr());

		topicService.addNew(topic); // Add new
		WebArticleHelper.refreshLastPostTime(request); // refresh last post time

		ActionForward af = mapping.findForward("showTopic");
		return new ActionForward(af.getPath() + "&pageNum=1&id=" + topic.getId(), af.getRedirect());
	}

	/**
	 * 主题修改页面
	 */
	@Privilege(resource = Resource.TOPIC, action = Action.EDIT)
	public ActionForward editTopicUI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ArticleForm articleForm = (ArticleForm) form;
		Topic topic = topicService.getTopic(articleForm.getId());

		articleForm.setTitle(topic.getTitle());
		articleForm.setContent(topic.getContent());

		request.setAttribute("topic", topic);
		return mapping.findForward("saveTopic");
	}

	/**
	 * 主题修改
	 */
	@Privilege(resource = Resource.TOPIC, action = Action.EDIT)
	public ActionForward editTopic(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (validateFailed(form, "validateTopic", mapping, request)) { // 验证表单
			return mapping.findForward("saveTopic");
		}

		ArticleForm articleForm = (ArticleForm) form;
		Topic topic = topicService.getTopic(articleForm.getId());

		topic.setTitle(articleForm.getTitle());
		topic.setContent(articleForm.getContent());
		topicService.updateTopic(topic);

		ActionForward af = mapping.findForward("showTopic");
		return new ActionForward(af.getPath() + "&pageNum=1&id=" + topic.getId(), af.getRedirect());

	}

	// ---

	/**
	 * 回复/引用 页面
	 */
	@Privilege(resource = Resource.REPLY, action = Action.CREATE)
	public ActionForward addReplyUI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ArticleForm articleForm = (ArticleForm) form;
		Topic t = topicService.getTopic(articleForm.getTopicId());

		int quotedArticleId = getIntParam(request, "quotedArticleId", 0); // 有此参数说明是引用
		if (quotedArticleId > 0) {
			Article quotedArticle = articleService.getArticle(quotedArticleId);
			String content = "<div class='quote'><b>" //
					+ quotedArticle.getAuthor().getNickname() //
					+ " 说:</b> " + quotedArticle.getContent() //
					+ "</div><br>";
			articleForm.setContent(content);
		}

		setCategoriesInRequestScope(request);
		request.setAttribute("topic", t);
		return mapping.findForward("saveReply");
	}

	/**
	 * 回复添加(只是内容)
	 */
	@Privilege(resource = Resource.REPLY, action = Action.CREATE)
	public ActionForward addReply(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (validateFailed(form, "validateReply", mapping, request)) { // 验证表单
			return addReplyUI(mapping, form, request, response);
		}

		// 验证发帖时间间隔
		if (!WebArticleHelper.checkPostDelay(request)) {
			addError(request, "postDelay", "距上次发帖间隔时间太短, 您不能发表新文章. 请稍候再试.", false);
			return addTopicUI(mapping, form, request, response);
		}

		ArticleForm articleForm = (ArticleForm) form;
		Topic topic = topicService.getTopic(articleForm.getTopicId());
		User user = getCurrentUser(request);

		Reply reply = new Reply();
		reply.setAuthor(user);
		reply.setTopic(topic);
		reply.setContent(articleForm.getContent());
		reply.setPostTime(new Date());
		reply.setIpAddr(request.getRemoteAddr());
		replyService.addNew(reply);

		ActionForward af = mapping.findForward("showTopic");
		int totalPage = getIntParam(request, "totalPage", 0); // 应显示本回复所在的页(最后一页)
		return new ActionForward(af.getPath() + "&id=" + topic.getId() + "&pageNum=" + (totalPage + 1), af.getRedirect());
	}

	/**
	 * 回复修改页面
	 */
	@Privilege(resource = Resource.REPLY, action = Action.EDIT)
	public ActionForward editReplyUI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ArticleForm articleForm = (ArticleForm) form;
		Reply reply = replyService.getReply(articleForm.getId());

		articleForm.setContent(reply.getContent());
		request.setAttribute("reply", reply);
		return mapping.findForward("saveReply");
	}

	/**
	 * 回复修改
	 */
	@Privilege(resource = Resource.REPLY, action = Action.EDIT)
	public ActionForward editReply(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (validateFailed(form, "validateReply", mapping, request)) { // 验证表单
			return mapping.findForward("saveReply");
		}

		ArticleForm articleForm = (ArticleForm) form;
		Reply reply = replyService.getReply(articleForm.getId());

		reply.setContent(articleForm.getContent());
		replyService.updateReply(reply);

		ActionForward af = mapping.findForward("showTopic");
		return new ActionForward(af.getPath() + "&id=" + reply.getTopic().getId() + "&pageNum=" + articleForm.getPageNum(), af.getRedirect());
	}

}
