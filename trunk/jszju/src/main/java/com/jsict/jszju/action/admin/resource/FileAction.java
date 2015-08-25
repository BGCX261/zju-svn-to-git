package com.jsict.jszju.action.admin.resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;   
import javax.servlet.http.HttpServletResponse;   
import org.apache.struts.action.Action;   
import org.apache.struts.action.ActionForm;   
import org.apache.struts.action.ActionForward;   
import org.apache.struts.action.ActionMapping;   
import org.apache.struts.upload.FormFile;   
import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.Op;
import com.jsict.jszju.dao.IFileDao;
import com.jsict.jszju.entity.Fileinfo;
import com.jsict.jszju.form.FileForm;
import com.jsict.jszju.service.IAdminSourceManageService;
import com.jsict.jszju.service.IFileService;
   
  
 class FileAction extends Action {  
	 
	 private IFileService fileService;
	
	@Required
	public void setFileService(
			IFileService fileService) {
		this.fileService = fileService;
	}
	
    public ActionForward execute(ActionMapping mapping, ActionForm form,   
            HttpServletRequest request, HttpServletResponse response) {   
        FileForm fileForm = (FileForm) form;   
        
        FormFile file1=fileForm.getFile1();
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
        String savename = s+ file1.getFileName().substring(file1.getFileName().lastIndexOf("."));
        if(file1!=null){   
            //上传路径   
           String dir=request.getSession(true).getServletContext().getRealPath("/upload"); 
    		File file = new File(dir);
    		if (!file.exists()) {
    			file.mkdirs();
    		}
    		
            OutputStream fos=null;   
            try {  
                fos=new FileOutputStream(dir+"\\"+savename);   
                fos.write(file1.getFileData(),0,file1.getFileSize());   
                fos.flush();
                Fileinfo entity = new Fileinfo();
                entity.setPath(dir+"\\"+savename);
                entity.setDep(fileForm.getDep());
                entity.setDisplay(fileForm.getDisplay());
              //  entity.setLinkaddress(fileForm.getLinkaddress());
                entity.setTitle(fileForm.getTitle());
                entity.setDiscrption(fileForm.getDiscrption());
                entity.setFilename(savename);
                fileService.saveFile(entity);
                PagedList<Fileinfo> fileinfo=new PagedList<Fileinfo>();
                EntityFilter tf=new EntityFilter();
                fileinfo=fileService.getFilePagedListByFileName(tf, null, null, savename);
                String link="/jszju/fileView.do?actionType=viewpiccontent&picviewId="+fileinfo.get(0).getId().toString();
                fileinfo.get(0).setLinkaddress(link);
                fileService.saveFile(fileinfo.get(0));
                
            } catch (Exception e) {   
                // TODO Auto-generated catch block   
                e.printStackTrace();   
            }finally{   
                try{   
                fos.close();   
                }catch(Exception e){}   
            }   
        }   
        //页面跳转   
        return mapping.findForward("success");   
    }   
}  