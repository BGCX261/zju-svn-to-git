/**
 * SchoolFellowPutInfoDoamin.java        2009-11-27 下午01:49:14
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.domain;

import java.util.Date;

import com.jsict.base.BaseDomain;
import com.jsict.base.util.Text;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class SchoolFellowPutInfoDoamin extends BaseDomain {
	
	private String userid;
	private String title;
	private String content;
	private String time;
	private String visittime;
	private String isview;
	@Text
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Text
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Text
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Text
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Text
	public String getVisittime() {
		return visittime;
	}
	public void setVisittime(String visittime) {
		this.visittime = visittime;
	}
	@Text
	public String getIsview() {
		return isview;
	}
	public void setIsview(String isview) {
		this.isview = isview;
	}

}
