package cn.itcast.bbs.entities.article;

import java.io.InputStream;
import java.util.Date;

/**
 * 附件
 * 
 * @author 传智博客.汤阳光 Jun 19, 2008
 */
public class Attachment {

	private int id;

	private String fileName;// 文件名
	private String description;// 附件说明
	private int fileSize;// 文件大小, 单位B(字节)
	private Date creationTime;// 创建(上传)时间
	private String path;// 在服务器端保存的路径
	private int downloadCount; // 被下载/查看 的次数
	// private boolean enableThumb;// TODO 是否有缩略图(图片才有)
	private Article article;// 所属文章

	private InputStream inputStream;// 附件文件的内容, 不对应数据库中的列.

	public Attachment() {}

	public int getId() {
		return id;
	}

	public String getFileName() {
		return fileName;
	}

	public String getDescription() {
		return description;
	}

	public int getFileSize() {
		return fileSize;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public String getPath() {
		return path;
	}

	public int getDownloadCount() {
		return downloadCount;
	}

	public Article getArticle() {
		return article;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Attachment other = (Attachment) obj;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuffer().append("[Attachment: ")//
				.append("id=").append(id)//
				.append(",fileName=").append(fileName)//
				.append(",description=").append(description)//
				.append(",fileSize=").append(fileSize)//
				.append(",creationTime=").append(creationTime)//
				.append(",path=").append(path)//
				.append(",downloadCount=").append(downloadCount)//
				.append(",article=").append(article == null ? null : "{articleId:" + article.getId() + "}")//
				.append("]")//
				.toString();
	}
}
