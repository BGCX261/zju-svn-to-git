/**
 * ArticleContentListAction.java        2009-11-8 下午01:29:42
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.jszju.action.admin;

import java.util.Collections;
import java.util.Comparator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.jsict.base.action.BaseListAction;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.Consts;
import com.jsict.jszju.domain.ArticleContentDomain;
import com.jsict.jszju.domain.CommentDomain;
import com.jsict.jszju.form.ArticleContentListForm;
import com.jsict.jszju.service.IArticleContentService;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class ArticleContentListAction extends BaseListAction {

	private IArticleContentService articleContentService;

	public void setArticleContentService(
			IArticleContentService articleContentService) {
		this.articleContentService = articleContentService;
	}

	/**
	 * <p>Description: The doListProcess</p>
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws ApplicationException
	 * @throws SystemException
	 * @author: caie
	 * @update: [updatedate] [changer][change description]
	 */

	@Override
	public ActionForward doListProcess(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ApplicationException, SystemException {
		ActionForward forward = null;
		ArticleContentListForm listForm = (ArticleContentListForm) form;
		String actionType = request.getParameter("actionType");

		if (Consts.ACTION_TYPE_SELECT.equals(actionType)
				|| Consts.ACTION_TYPE_INIT.equals(actionType)) {
			String channelId = request.getParameter("chaid");
			Cookie nameCookie = new Cookie("chalId", channelId); // 可以使用md5或着自己的加密算法保存
			nameCookie.setPath("/"); // 这个要注意
			nameCookie.setMaxAge(1000);
			response.addCookie(nameCookie);

			initList(request, listForm, channelId);
			forward = mapping.findForward(Consts.ACTION_TYPE_JSZUAANEWS);
		} else if (Consts.ACTION_TYPE_JSZUAANEWS.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typerbbs")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
					//进入本会信息页面
					String channelId = request.getParameter("jszuaaNews");
					request.setAttribute("chaid", channelId);
					initList(request, listForm, channelId);
					return  mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");

		} else if (Consts.ACTION_TYPE_ZJUNEWS.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typernotes")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入浙大要闻页面
			String channelId = request.getParameter("zjuNews");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_ZUAANEWS.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typernotes")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入总会信息页面
			String channelId = request.getParameter("zuaaNews");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_OTHZUAANEWS.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typernotes")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入各地校友会信息页面
			String channelId = request.getParameter("othzuaaNews");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_ZUAAFILES.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typerfile")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入总会文件页面
			String channelId = request.getParameter("zuaaFiles");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_JSZUAAILES.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typerfile")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入本会文件页面
			String channelId = request.getParameter("jszuaaFiles");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_ZUAACOMUU.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typerfile")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入总会通讯页面
			String channelId = request.getParameter("zuaaCommu");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_JSZUAACOMMU.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typerfile")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入本会通讯页面
			String channelId = request.getParameter("jszuaaCommu");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_OTHFILE.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typerfile")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入其它相关文件页面
			String channelId = request.getParameter("othFile");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_ENJOYWRITING.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typerbbs")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			
			//进入佳作共赏页面
			String channelId = request.getParameter("enjoyWriting");
			initList(request, listForm, channelId);
			forward = mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_SUCCESSWAY.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typerbbs")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入成功之道页面
			String channelId = request.getParameter("successWay");
			initList(request, listForm, channelId);
			forward = mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_TALENTHUNTING
				.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typerbbs")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入人才招聘页面
			String channelId = request.getParameter("talentHunting");
			initList(request, listForm, channelId);
			forward = mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_JOBHUNTING.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typerbbs")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入人才求职页面
			String channelId = request.getParameter("jobHunting");
			initList(request, listForm, channelId);
			forward = mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_PICFILES.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typerpic")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入图片资料页面
			String channelId = request.getParameter("picFiles");
			initList(request, listForm, channelId);
			forward = mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_VIDEOFILES.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typerpic")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入影视资料页面
			String channelId = request.getParameter("videoFiles");
			initList(request, listForm, channelId);
			forward = mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_OLDBRANCH.equalsIgnoreCase(actionType)) {
			//进入老年分会页面
			String channelId = request.getParameter("oldBranch");
			initList(request, listForm, channelId);
			forward = mapping.findForward(actionType);
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_ACTIVITIESDYNAMIC
				.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typerfile")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入活动动态页面
			String channelId = request.getParameter("activitiesDynamic");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_TALKFORETIME.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typerfile")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入话说当年页面
			String channelId = request.getParameter("talkForetime");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_EXERCISE.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typerfile")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入保健强身页面
			String channelId = request.getParameter("exercise");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_OLDENJOY.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typerfile")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入老有所乐页面
			String channelId = request.getParameter("oldEnjoy");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_MIENALSO.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typerfile")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入风采依旧页面
			String channelId = request.getParameter("mienAlso");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_CONSULTHELP.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typerfile")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入咨询互助页面
			String channelId = request.getParameter("consultHelp");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_YONGBRANCH.equalsIgnoreCase(actionType)) {
			//进入青年分会页面
			String channelId = request.getParameter("yongBranch");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
		} else if (Consts.ACTION_TYPE_ACTIVITIESCOVER
				.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typerpic")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入活动报道页面
			String channelId = request.getParameter("activitiesCover");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_ACTIVITIESPIC
				.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typerpic")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入活动图片页面
			String channelId = request.getParameter("activitiesPic");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_INTRESTINGACTIVITIES
				.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typerpic")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入兴趣组活动页面
			String channelId = request.getParameter("intrestingActivities");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_FASHIONLIFE.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typerpic")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入时尚生活页面
			String channelId = request.getParameter("fashionLife");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_SUCCESSROAD.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typerpic")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入成功有道页面
			String channelId = request.getParameter("successRoad");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_YONG.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typerpic")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入风华正茂页面
			String channelId = request.getParameter("yong");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_MEDBRANCH.equalsIgnoreCase(actionType)) {
			//进入医学分会页面
			String channelId = request.getParameter("medBranch");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
		} else if (Consts.ACTION_TYPE_BOOKCLUB.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typermember")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			
			//进入书友俱乐部页面
			String channelId = request.getParameter("bookClub");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_CARCLUB.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typermember")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入车友俱乐部页面
			String channelId = request.getParameter("carClub");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_SPORTSCLUB.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typermember")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入体育俱乐部页面
			String channelId = request.getParameter("sportsClub");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_ENTERTAINMENT
				.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typermember")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入娱乐大本营页面
			String channelId = request.getParameter("entertainmentClub");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_LEISURFOOD.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typermember")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入休闲美食页面
			String channelId = request.getParameter("leisureFood");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_MARRIAGE.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typermember")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入谈婚论嫁页面
			String channelId = request.getParameter("marriage");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_CARRERHELP.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typermember")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入职场互助页面
			String channelId = request.getParameter("carrerHelp");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_OMEMBERSERVICE
				.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typermember")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入会员服务页面
			String channelId = request.getParameter("memberService");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_MACTIVTIESCOVER
				.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typemedical")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入医学活动报道页面
			String channelId = request.getParameter("MactivtiesCover");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_MACTIVITIESPIC
				.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typemedical")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入医学活动图片页面
			String channelId = request.getParameter("MactivitiesPic");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_MMETHOD.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typemedical")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入医学经验之谈页面
			String channelId = request.getParameter("Mmethod");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_MSERVICE.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typemedical")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入医学咨询服务页面
			String channelId = request.getParameter("Mservice");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_MSUCCESS.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typemedical")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入医学成功有道页面
			String channelId = request.getParameter("Msuccess");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		} else if (Consts.ACTION_TYPE_MMEDICAL.equalsIgnoreCase(actionType)) {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("role")
						&& (c.getValue().equals("typemedical")
								|| c.getValue().equals("admin") || c.getValue()
								.equals("superadmin"))) {
			//进入医学济世有道页面
			String channelId = request.getParameter("Mmedical");
			initList(request, listForm, channelId);
			return mapping.findForward(actionType);
				}
			}
			return mapping.findForward("nopopedom");
		}

		else if (Consts.ACTION_TYPE_NEW.equalsIgnoreCase(actionType)) {
			//进入新增数据页面
			return mapping.findForward(actionType);
		} else if (Consts.ACTION_TYPE_EDIT.equalsIgnoreCase(actionType)) {
			//进入数据编辑页面
			return mapping.findForward(actionType);
		} else if (Consts.ACTION_TYPE_CANCEL.equalsIgnoreCase(actionType)) {
			//进入数据编辑页面
			return mapping.findForward(Consts.ACTION_TYPE_JSZUAANEWS);
		} else if (Consts.ACTION_TYPE_VIEW.equals(actionType)) {
			String id = request.getParameter("id");
			ArticleContentDomain domain = articleContentService
					.getArticleContent(id);
			request.setAttribute("domain", domain);
			return mapping.findForward(actionType);
		} else if (Consts.ACTION_TYPE_DISABLE.equals(actionType)) {
			String id = request.getParameter("id");
			articleContentService.disableArticleContent(id);
			initList2(request, listForm);
			return mapping.findForward(Consts.ACTION_TYPE_JSZUAANEWS);
		}

		return forward;
	}

	private void initList(HttpServletRequest request,
			ArticleContentListForm form, String channelId)
			throws SystemException, ApplicationException {
		PagedList<ArticleContentDomain> list = articleContentService
				.getArticleContentPagedListByChannelId(getEntityFilter(),
						getPageNo(), getPageSize(), channelId);
		Collections.sort(list, new ArticleCotentListComparator());
		form.setResultList(list);
		setPageInfo(request, (PagedList) list); //设置分页信息
	}

	private void initList2(HttpServletRequest request,
			ArticleContentListForm form) throws SystemException,
			ApplicationException {
		PagedList<ArticleContentDomain> list = articleContentService
				.getArticleContentPagedList(getEntityFilter(), getPageNo(),
						getPageSize());
		Collections.sort(list, new ArticleCotentListComparator());
		form.setResultList(list);
		setPageInfo(request, (PagedList) list); //设置分页信息
	}

	class ArticleCotentListComparator implements Comparator {
		public int compare(Object o1, Object o2) {
			ArticleContentDomain s1 = (ArticleContentDomain) o1;
			ArticleContentDomain s2 = (ArticleContentDomain) o2;
			if (s1.getInputData() == null) {
				s1.setInputData("2009-10-10 00:00:00");
			}
			if (s2.getInputData() == null) {
				s2.setInputData("2009-10-10 00:00:00");
			}
			int result1 = s1.getInputData().compareTo(s2.getInputData());
			int result = (result1 < 0) ? 1 : -1;
			return result;
		}
	}

}
