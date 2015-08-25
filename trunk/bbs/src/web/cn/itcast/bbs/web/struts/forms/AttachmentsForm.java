package cn.itcast.bbs.web.struts.forms;

import java.util.Map;
import java.util.TreeMap;

import org.apache.struts.upload.FormFile;

import cn.itcast.bbs.web.struts.forms.base.BaseActionForm;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
@SuppressWarnings("serial")
public class AttachmentsForm extends BaseActionForm {
	private String fileName;
	private String description;
	private int articleId;

	private Map<String, FormFile> files = new TreeMap<String, FormFile>();
	private Map<String, String> descs = new TreeMap<String, String>();

	public String getDesc(String key) {
		return descs.get(key);
	}

	public void setDesc(String key, String value) {
		descs.put(key, value);
	}

	public FormFile getFile(String key) {
		return files.get(key);
	}

	public void setFile(String key, FormFile value) {
		if (value != null && value.getFileSize() > 0) {
			files.put(key, value);
		}
	}

	// ---

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public Map<String, FormFile> getFiles() {
		return files;
	}

	public void setFiles(Map<String, FormFile> files) {
		this.files = files;
	}

	public Map<String, String> getDescs() {
		return descs;
	}

	public void setDescs(Map<String, String> descs) {
		this.descs = descs;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
