package cn.itcast.bbs.web.struts.actions.article;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itcast.bbs.entities.PageInfo;
import cn.itcast.bbs.entities.article.Forum;
import cn.itcast.bbs.entities.article.Topic;
import cn.itcast.bbs.web.helper.WebCommonHelper;
import cn.itcast.bbs.web.struts.action.base.BaseAction;

/**
 * @author 传智播客.汤阳光 Jun 6, 2008
 */
@Controller("/forum")
public class ForumAction extends BaseAction {

	/**
	 * 分类和版面列表显示, 论坛当前的统计信息显示
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Topic> topics = topicService.findRecommendations(8);
		request.setAttribute("recommendationTopics", topics);
		WebCommonHelper.preSystemStatus(request, systemService);
		setCategoriesInRequestScope(request);
		return mapping.findForward("list");
	}

	public ActionForward show(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int forumId = getIntParam(request, "id", 0);
		int pageNum = getIntParam(request, "pageNum", 1);
		if (pageNum < 1) {
			pageNum = 1;
		}

		Forum forum = forumService.getForum(forumId);
		PageInfo<Topic> page = topicService.findTopicsByForum(forumId, pageNum);

		setCategoriesInRequestScope(request);
		request.setAttribute("page", page);
		request.setAttribute("forum", forum);
		return mapping.findForward("show");
	}

}
