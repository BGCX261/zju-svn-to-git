/**
 * FileHanderAction.java        2009-12-15 下午09:08:41
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.action.admin.resource;

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
import com.jsict.base.util.ConvertUtil;
import com.jsict.base.util.Op;
import com.jsict.jszju.domain.ArticleContentDomain;
import com.jsict.jszju.domain.FileInfoDomain;
import com.jsict.jszju.domain.SchoolFellowForHelpDomain;
import com.jsict.jszju.domain.SchoolFellowPutInfoDoamin;
import com.jsict.jszju.entity.Fileinfo;
import com.jsict.jszju.form.FileHanderListForm;
import com.jsict.jszju.service.IFileService;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class FileHanderAction extends BaseListAction {
	
	public static final String SFVIEW = "sfview";
	
	public static final String FOCUSVIEW = "focusview";
	
	public static final String VIEWPICCONTENT = "viewpiccontent";
	
	public static final String VIEWPICCONTENT2 = "viewpiccontent2";
	
	private IFileService  fileService;
	

	public void setFileService(IFileService fileService) {
		this.fileService = fileService;
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
		
		FileHanderListForm fileForm = (FileHanderListForm) form; 
		  String actionType=request.getParameter("actionType");
		  
		  if(SFVIEW.equals(actionType))
		  {
			  EntityFilter tf=new EntityFilter();
			  tf.addFilter("dep", Op.EQUAL, "1");
			  PagedList<Fileinfo> list = fileService
				.getFilePagedList(getEntityFilter(), null, 5);
			  Collections.sort(list, new FileComparator());
			  request.setAttribute("filelist", list);
			  return mapping.findForward(SFVIEW);
		  }
		  else  if(VIEWPICCONTENT.equals(actionType))
		  {
			  String picid=request.getParameter("picviewId");
			  request.setAttribute("picid", picid);
			  
			  return mapping.findForward(VIEWPICCONTENT);
		  }
		  else  if(VIEWPICCONTENT2.equals(actionType))
		  {
			  String IdPic=request.getParameter("picId");
			  Fileinfo  entity=new Fileinfo();
			  entity=fileService.getFilePic(Integer.parseInt(IdPic));
			  request.setAttribute("picentity", entity);
			  PagedList<Fileinfo> list = fileService
				.getFilePagedList(getEntityFilter(), null, 5);
			  Collections.sort(list, new FileComparator());
			  request.setAttribute("filelist", list);
			  return mapping.findForward(VIEWPICCONTENT2);
		  }
		  
		  else  if(FOCUSVIEW.equals(actionType))
		  {
			  EntityFilter tf=new EntityFilter();
			  tf.addFilter("dep", Op.EQUAL, "2");
			  PagedList<Fileinfo> list = fileService
				.getFilePagedList(getEntityFilter(), null, null);
			  Collections.sort(list, new FileComparator());
			  request.setAttribute("focuslist", list);
			  return mapping.findForward(FOCUSVIEW);
		  }
		  else if(Consts.ACTION_TYPE_PICEDIT.equalsIgnoreCase(actionType)||Consts.ACTION_TYPE_SELECT.equalsIgnoreCase(actionType))
		   	{
			  Cookie[] cookies = request.getCookies();
				String adminrole = null;
				boolean flag=false;
				for (Cookie c : cookies) {
					if (c.getName().equals("role") && (c.getValue().equals("typernotes")||(c.getValue().equals("admin"))||(c.getValue().equals("superadmin")))) {
						flag=true;
						break;
					}
				}
				if(!flag)
				{
					return mapping.findForward("nopopedom");
				}
			  PagedList<Fileinfo> list = fileService
				.getFilePagedList(getEntityFilter(), null, null);
			  Collections.sort(list, new FileComparator());
			  fileForm.setResultList(list);
			  setPageInfo(request, (PagedList) list); //设置分页信息
			   return  mapping.findForward(Consts.ACTION_TYPE_PICEDIT);
		   	}
		  else  if(Consts.ACTION_TYPE_EDIT.equalsIgnoreCase(actionType))
	        {
			  Fileinfo entity = fileService.getFilePic(Integer.parseInt(request.getParameter("id")));
			  fileForm.setEntity(entity);
			  fileForm.setActionType(Consts.ACTION_TYPE_UPDATE);
	            return mapping.getInputForward();
			  
	        }
		  else  if(Consts.ACTION_TYPE_VIEW.equalsIgnoreCase(actionType))
	        {
			  Fileinfo entity = fileService.getFilePic(Integer.parseInt(request.getParameter("id")));
			  fileForm.setEntity(entity);
			  return mapping.findForward("picview");
			  
	        }
		  else  if(Consts.ACTION_TYPE_DISABLE.equalsIgnoreCase(actionType))
	        {
			  return mapping.findForward(Consts.ACTION_TYPE_PICEDIT);
	        }
		  else  if(Consts.ACTION_TYPE_CANCEL.equalsIgnoreCase(actionType))
	        {
			  return mapping.findForward(Consts.ACTION_TYPE_PICEDIT);
	        }
		  else if(Consts.ACTION_TYPE_UPDATE.equalsIgnoreCase(actionType))
	        {
			  
			  Fileinfo entity  = fileForm.getEntity();
			  fileService.saveFile(entity);
			  return mapping.findForward(Consts.ACTION_TYPE_PICEDIT);
			  
	        }
		return mapping.getInputForward();
	}
	
	class FileComparator implements Comparator {
		public int compare(Object o1, Object o2) {
			Fileinfo s1 = (Fileinfo) o1;
			if (s1.getFilename() == null) {
				s1.setFilename("20091010000000.jpg");
			}
			String[] s1A=s1.getFilename().split("\\.");
			Fileinfo s2 = (Fileinfo) o2;
			if (s2.getFilename() == null) {
				s2.setFilename("20091010000000.jpg");
			}
			String []s2A=s2.getFilename().split("\\.");
			int result1 = s1A[0].compareTo(s2A[0]);
			int result = (result1 < 0) ? 1 : -1;
			return result;
		}
	}

}
