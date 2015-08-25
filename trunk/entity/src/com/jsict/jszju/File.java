package com.jsict.jszju;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * File entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "file", catalog = "cedatabase")
public class File implements java.io.Serializable {

	// Fields

	private Integer id;
	private String path;
	private String dep;
	private String display;
	private String title;
	private String linkaddress;
	private String filename;
	private String discrption;

	// Constructors

	/** default constructor */
	public File() {
	}

	/** full constructor */
	public File(String path, String dep, String display, String title,
			String linkaddress, String filename, String discrption) {
		this.path = path;
		this.dep = dep;
		this.display = display;
		this.title = title;
		this.linkaddress = linkaddress;
		this.filename = filename;
		this.discrption = discrption;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "path")
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "dep", length = 8)
	public String getDep() {
		return this.dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	@Column(name = "display", length = 8)
	public String getDisplay() {
		return this.display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	@Column(name = "title", length = 30)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "linkaddress", length = 90)
	public String getLinkaddress() {
		return this.linkaddress;
	}

	public void setLinkaddress(String linkaddress) {
		this.linkaddress = linkaddress;
	}

	@Column(name = "filename", length = 40)
	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name = "discrption", length = 65535)
	public String getDiscrption() {
		return this.discrption;
	}

	public void setDiscrption(String discrption) {
		this.discrption = discrption;
	}

}