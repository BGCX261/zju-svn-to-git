package cn.itcast.bbs.service.article;

import cn.itcast.bbs.entities.article.Attachment;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
public interface AttachmentService {

	/**
	 * 
	 * @param id
	 * @return 指定id的Attachment
	 */
	Attachment getAttachment(int id);

	/**
	 * 给指定的Attachment下载次数加1
	 * 
	 * @param id
	 */
	void incrementDownloadCount(int id);

	/**
	 * 添加附件, FIXME 附件应在调用之前被保存到文件系统中.
	 * 
	 * @param atts
	 */
	void addNew(Attachment... atts);

	/**
	 * 更新附件信息
	 * 
	 * @param att
	 */
	void updateAttachment(Attachment att);

	/**
	 * 删除附件
	 * @param id
	 */
	void deleteAttachment(int id);
}
