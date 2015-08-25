package com.jsict.jszju.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ItcastAttachment entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "itcast_attachment")
public class ItcastAttachment implements java.io.Serializable {

	// Fields

	private Integer id;
	private ItcastArticle itcastArticle;
	private String fileName;
	private String description;
	private Integer fileSize;
	private Date creationTime;
	private String path;
	private Integer downloadCount;
	private Integer idx;

	// Constructors

	/** default constructor */
	public ItcastAttachment() {
	}

	/** full constructor */
	public ItcastAttachment(ItcastArticle itcastArticle, String fileName,
			String description, Integer fileSize, Date creationTime,
			String path, Integer downloadCount, Integer idx) {
		this.itcastArticle = itcastArticle;
		this.fileName = fileName;
		this.description = description;
		this.fileSize = fileSize;
		this.creationTime = creationTime;
		this.path = path;
		this.downloadCount = downloadCount;
		this.idx = idx;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id_", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "articleId_")
	public ItcastArticle getItcastArticle() {
		return this.itcastArticle;
	}

	public void setItcastArticle(ItcastArticle itcastArticle) {
		this.itcastArticle = itcastArticle;
	}

	@Column(name = "fileName_")
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "description_")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "fileSize_")
	public Integer getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creationTime_", length = 19)
	public Date getCreationTime() {
		return this.creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	@Column(name = "path_")
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "downloadCount_")
	public Integer getDownloadCount() {
		return this.downloadCount;
	}

	public void setDownloadCount(Integer downloadCount) {
		this.downloadCount = downloadCount;
	}

	@Column(name = "idx_")
	public Integer getIdx() {
		return this.idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

}