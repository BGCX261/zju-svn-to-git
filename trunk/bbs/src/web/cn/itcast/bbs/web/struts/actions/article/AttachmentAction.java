package cn.itcast.bbs.web.struts.actions.article;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.springframework.stereotype.Controller;

import cn.itcast.bbs.cfg.SystemGlobals;
import cn.itcast.bbs.entities.article.Article;
import cn.itcast.bbs.entities.article.Attachment;
import cn.itcast.bbs.entities.privilege.Action;
import cn.itcast.bbs.entities.privilege.Privilege;
import cn.itcast.bbs.entities.privilege.Resource;
import cn.itcast.bbs.web.WebConstants;
import cn.itcast.bbs.web.struts.action.base.BaseAction;
import cn.itcast.bbs.web.struts.forms.AttachmentsForm;
import cn.itcast.bbs.web.utils.WebAppUtils;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
@Controller("/attachment")
public class AttachmentAction extends BaseAction {

	/**
	 * 增加页面
	 */
	@Privilege(resource = Resource.ATTACHMENT, action = Action.CREATE)
	public ActionForward addUI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		setCategoriesInRequestScope(request);
		return mapping.findForward("add");
	}

	/**
	 * 增加
	 */
	@Privilege(resource = Resource.ATTACHMENT, action = Action.CREATE)
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AttachmentsForm attachmentsForm = (AttachmentsForm) form;
		Article article = articleService.getArticle(attachmentsForm.getArticleId());

		List<Attachment> attachments = new ArrayList<Attachment>();
		for (String key : attachmentsForm.getFiles().keySet()) {
			FormFile formFile = attachmentsForm.getFile(key);
			String desc = attachmentsForm.getDesc(key);

			Attachment att = new Attachment();
			att.setArticle(article);
			att.setDescription(desc);
			att.setCreationTime(new Date());
			att.setFileName(formFile.getFileName());
			att.setFileSize(formFile.getFileSize());
			att.setInputStream(formFile.getInputStream());

			attachments.add(att);
		}
		attachmentService.addNew(attachments.toArray(new Attachment[attachments.size()]));

		ActionForward af = mapping.findForward("showTopic");
		int topicId = getIntParam(request, "topicId", 0);
		return new ActionForward(af.getPath() + "&id=" + topicId + "&pageNum=" + attachmentsForm.getPageNum(), af.getRedirect());
	}

	/**
	 * 修改页面
	 */
	@Privilege(resource = Resource.ATTACHMENT, action = Action.EDIT)
	public ActionForward editUI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AttachmentsForm attachmentsForm = (AttachmentsForm) form;
		Attachment att = attachmentService.getAttachment(attachmentsForm.getId());
		attachmentsForm.setFileName(att.getFileName());
		attachmentsForm.setDescription(att.getDescription());
		return mapping.findForward("edit");
	}

	/**
	 * 修改(只修改备注)
	 */
	@Privilege(resource = Resource.ATTACHMENT, action = Action.EDIT)
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AttachmentsForm attachmentsForm = (AttachmentsForm) form;
		Attachment att = attachmentService.getAttachment(attachmentsForm.getId());
		att.setDescription(attachmentsForm.getDescription());
		attachmentService.updateAttachment(att);

		ActionForward af = mapping.findForward("showTopic");
		int topicId = getIntParam(request, "topicId", 0);
		return new ActionForward(af.getPath() + "&id=" + topicId + "&pageNum=" + attachmentsForm.getPageNum(), af.getRedirect());
	}

	/**
	 * 删除
	 */
	@Privilege(resource = Resource.ATTACHMENT, action = Action.DELETE)
	public ActionForward del(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AttachmentsForm attachmentsForm = (AttachmentsForm) form;
		attachmentService.deleteAttachment(attachmentsForm.getId());

		ActionForward af = mapping.findForward("showTopic");
		int topicId = getIntParam(request, "topicId", 0);
		return new ActionForward(af.getPath() + "&id=" + topicId + "&pageNum=" + attachmentsForm.getPageNum(), af.getRedirect());
	}

	/**
	 * 下载附件
	 */
	@Privilege(resource = Resource.ATTACHMENT, action = Action.DOWNLOAD)
	public ActionForward download(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int topicId = getIntParam(request, "topicId", 0);
		int pageNum = getIntParam(request, "pageNum", 1);

		// 在获得附件因权限不足失败时, 会转到指定的 returnPath
		String returnPath = WebAppUtils.getBaseUrl(request) + "/article.do?method=showTopic&id=" + topicId + "&pageNum=" + pageNum;
		request.setAttribute(WebConstants.SCOPE_ATTR_RETURN_PATH, returnPath);

		AttachmentsForm attachmentsForm = (AttachmentsForm) form;
		Attachment att = attachmentService.getAttachment(attachmentsForm.getId());

		response.setContentType("application/x-msdownload;charset=gbk");
		// 解决下载的默认文件名是乱码
		// FIXME IE的150问题, 应在上传文件时处理文件名, 超长的文件名用..加编号处理
		String filename = new String(att.getFileName().getBytes("GBK"), "iso8859-1");
		response.addHeader("Content-Disposition", "attachment;filename=" + filename);

		// FIXME 应支持断点续传(迅雷下载)
		FileInputStream fis = null;
		try {
			String basePath = SystemGlobals.getSettings().getAttachmentStorePath();
			fis = new FileInputStream(basePath + "/" + att.getPath());
			ServletOutputStream sos = response.getOutputStream();
			byte[] buf = new byte[81920];
			for (int i = -1; (i = fis.read(buf)) != (-1);) {
				sos.write(buf, 0, i);
			}
			response.getOutputStream().flush();
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			if (fis != null) {
				fis.close();
			}
		}

		// 更新下载次数
		attachmentService.incrementDownloadCount(attachmentsForm.getId());
		return null;
	}

}
