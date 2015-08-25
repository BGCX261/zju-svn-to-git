package cn.itcast.bbs.service.article.impl;

import java.io.File;
import java.util.Arrays;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bbs.cfg.SystemGlobals;
import cn.itcast.bbs.entities.article.Article;
import cn.itcast.bbs.entities.article.Attachment;
import cn.itcast.bbs.exception.runtime.ItcastException;
import cn.itcast.bbs.logic.helper.AttachmentHelper;
import cn.itcast.bbs.service.article.AttachmentService;
import cn.itcast.bbs.service.base.BaseService;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
@Service("attachmentService")
@Transactional(readOnly = true)
public class AttachmentServiceImpl extends BaseService implements AttachmentService {

	public Attachment getAttachment(int id) {
		return attachmentDao.get(id);
	}

	@Transactional
	public void incrementDownloadCount(int id) {
		Attachment att = attachmentDao.get(id);
		att.setDownloadCount(att.getDownloadCount() + 1);
	}

	@Transactional
	public void addNew(Attachment... atts) {
		if (atts == null) {
			return;
		}

		for (Attachment att : atts) {
			if (att.getCreationTime() == null) {
				att.setCreationTime(new Date());
			}

			Article article = att.getArticle();
			article.getAttachments().add(att);
			articleDao.update(article); // 使用设置的级联保存附件

			int attachmentMaxAmount = SystemGlobals.getSettings().getAttachmentMaxAmount();
			if (article.getAttachments().size() > attachmentMaxAmount) {
				throw new ItcastException("一篇文章中最多允许" + attachmentMaxAmount + "个附件, 但此文章中有" + article.getAttachments().size() + "个附件");
			}
		}

		// 保存附件到服务器的文件系统中
		AttachmentHelper.storeAttachmentFiles(Arrays.asList(atts));
	}

	@Transactional
	public void updateAttachment(Attachment att) {
		attachmentDao.update(att);
	}

	@Transactional
	public void deleteAttachment(int id) {
		Attachment att = attachmentDao.get(id);

		// 1, 删除数据库中的记录
		// 要先移除关联关系, 否则报错:deleted object would be re-saved by cascade
		att.getArticle().getAttachments().remove(att);
		attachmentDao.delete(att);

		// 2, 删除文件
		String basePath = SystemGlobals.getSettings().getAttachmentStorePath();
		File file = new File(basePath + "/" + att.getPath());
		file.delete();
	}

}
