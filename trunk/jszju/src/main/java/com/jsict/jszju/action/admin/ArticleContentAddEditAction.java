/**
 * ArticleContentAddEditAction.java        2009-11-8 下午09:38:13
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.action.admin;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.jsict.base.action.BaseAction;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.Consts;
import com.jsict.jszju.domain.ArticleContentDomain;
import com.jsict.jszju.form.ArticleContentForm;
import com.jsict.jszju.service.IArticleContentService;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class ArticleContentAddEditAction extends BaseAction {
	
	private IArticleContentService	articleContentService;
	
	public void setArticleContentService(
			IArticleContentService articleContentService) {
		this.articleContentService = articleContentService;
	}


	/**
	 * <p>Description: The process</p>
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
	protected ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ApplicationException, SystemException {

		ArticleContentForm articleContentForm = (ArticleContentForm) form;
	        String actionType = articleContentForm.getActionType();

	        ActionForward forward = new ActionForward();
	        if(Consts.ACTION_TYPE_NEW.equalsIgnoreCase(actionType))
	        {
	            //进入新增数据页面
	        	String channelId=request.getParameter("channelId");
	        	
	        	Cookie nameCookie = new Cookie("chalId", channelId); // 可以使用md5或着自己的加密算法保存
	    		nameCookie.setPath("/"); // 这个要注意
	    		nameCookie.setMaxAge(1000);
	    		response.addCookie(nameCookie);
	    		
	        	ArticleContentDomain domain = new ArticleContentDomain();
	        	articleContentForm.setDomain(domain);
	        	articleContentForm.setActionType(Consts.ACTION_TYPE_INSERT);
	            forward = mapping.getInputForward();
	        }
	        else if(Consts.ACTION_TYPE_EDIT.equalsIgnoreCase(actionType))
	        {
	        	 //根据界面传入ID取得对应的domain,进入数据编辑页面
	        	ArticleContentDomain domain = articleContentService.getArticleContent(
	                    request.getParameter("id"));
	        	articleContentForm.setDomain(domain);
	        	
	        	Cookie nameCookie = new Cookie("chalId", domain.getChannelId()); // 可以使用md5或着自己的加密算法保存
	    		nameCookie.setPath("/"); // 这个要注意
	    		nameCookie.setMaxAge(1000);
	    		response.addCookie(nameCookie);
	        	
	        	articleContentForm.setActionType(Consts.ACTION_TYPE_UPDATE);
	            forward = mapping.getInputForward();
	        }
	        else if(Consts.ACTION_TYPE_INSERT.equalsIgnoreCase(actionType))
	        {
	            //点击保存按钮后,如果ACTION_TYPE是insert, 则插入数据
	        	ArticleContentDomain domain = articleContentForm.getDomain();
	        	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				String commitTime=df.format(new Date());
				domain.setInputData(commitTime);
	        	articleContentService.addArticleContent(domain);
	        	
	        	Cookie nameCookie = new Cookie("chalId", domain.getChannelId()); // 可以使用md5或着自己的加密算法保存
	    		nameCookie.setPath("/"); // 这个要注意
	    		nameCookie.setMaxAge(1000);
	    		response.addCookie(nameCookie);
	    		
	            request.setAttribute("domain", domain);
	            forward = mapping.findForward(Consts.ACTION_TYPE_JSZUAANEWS);
	        }
	        else if(Consts.ACTION_TYPE_UPDATE.equalsIgnoreCase(actionType))
	        {
	          //点击保存按钮后,如果ACTION_TYPE是update, 则修改数据
	        	ArticleContentDomain domain = articleContentForm.getDomain();
	        	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				String commitTime=df.format(new Date());
				domain.setInputData(commitTime);
	        	articleContentService.updateArticleContent(domain);
	        	
	        	Cookie nameCookie = new Cookie("chalId", domain.getChannelId()); // 可以使用md5或着自己的加密算法保存
	    		nameCookie.setPath("/"); // 这个要注意
	    		nameCookie.setMaxAge(1000);
	    		response.addCookie(nameCookie);
	    		
	            request.setAttribute("domain", articleContentService.getArticleContent(domain.getId()));
	            forward = mapping.findForward(Consts.ACTION_TYPE_JSZUAANEWS);
	        }
	        else if(Consts.ACTION_TYPE_CANCEL.equalsIgnoreCase(actionType))
	        {
	            forward = mapping.findForward(Consts.ACTION_TYPE_CANCEL);
	        }
	        return forward;
	    }

}
