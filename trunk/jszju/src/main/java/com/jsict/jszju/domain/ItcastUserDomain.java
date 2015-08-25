/**
 * ItcastUserDomain.java        2009-8-25 下午09:37:42
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.domain;


import java.util.Date;

import com.jsict.base.BaseDomain;
import com.jsict.base.util.Text;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">蔡锷</a>
 * @version 1.0
 */
public class ItcastUserDomain extends BaseDomain {
	

	private String loginName;
	private String password;
	private String email;
	private String active;
	private String deleted;
	private Date registrationTime;
	private Date lastVisitTime;
	private String lastVisitIpAddr;
	private String autoLoginAuthKey;
	private String activationKey;
	private Integer topicCount;
	private Integer articleCount;
	private String nickname;
	private String sexName;
	private Date birthday;
	private String avatar;
	private String qq;
	private String msn;
	private String comeFrom;
	private String signature;
	private String realname;
	private String cellphone;
	private String major;
	private String graduyear;
	private String number;
	private String education;
	private String title;
	private String birthplace;
	private String address;
	private String postcode;
	private String marriage;
	private String car;
	private String speciality;
	private String resource;
	private String advice;
	private String activitypoint;

	@Text(label = "")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	@Text(label = "")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Text(label = "")
	public String getActivitypoint() {
		return activitypoint;
	}

	public void setActivitypoint(String activitypoint) {
		this.activitypoint = activitypoint;
	}

	@Text(label = "")
	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	@Text(label = "")
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
	@Text(label = "")
	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	@Text(label = "")
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	@Text(label = "")
	public String getGraduyear() {
		return graduyear;
	}

	public void setGraduyear(String graduyear) {
		this.graduyear = graduyear;
	}
	@Text(label = "")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	@Text(label = "")
	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}
	@Text(label = "")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@Text(label = "")
	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}
	@Text(label = "")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Text(label = "")
	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	@Text(label = "")
	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}
	@Text(label = "")
	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	@Text(label = "")
	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}
	@Text(label = "")
	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}
	@Text(label = "")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	@Text(label = "")
	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
	@Text(label = "")
	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	@Text(label = "")
	public Date getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(Date registrationTime) {
		this.registrationTime = registrationTime;
	}
	@Text(label = "")
	public Date getLastVisitTime() {
		return lastVisitTime;
	}

	public void setLastVisitTime(Date lastVisitTime) {
		this.lastVisitTime = lastVisitTime;
	}
	@Text(label = "")
	public String getLastVisitIpAddr() {
		return lastVisitIpAddr;
	}

	public void setLastVisitIpAddr(String lastVisitIpAddr) {
		this.lastVisitIpAddr = lastVisitIpAddr;
	}
	@Text(label = "")
	public String getAutoLoginAuthKey() {
		return autoLoginAuthKey;
	}

	public void setAutoLoginAuthKey(String autoLoginAuthKey) {
		this.autoLoginAuthKey = autoLoginAuthKey;
	}
	@Text(label = "")
	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}
	@Text(label = "")
	public Integer getTopicCount() {
		return topicCount;
	}

	public void setTopicCount(Integer topicCount) {
		this.topicCount = topicCount;
	}
	@Text(label = "")
	public Integer getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}
	@Text(label = "")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Text(label = "")
	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}
	@Text(label = "")
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	@Text(label = "")
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
	@Text(label = "")
	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}
	@Text(label = "")
	public String getComeFrom() {
		return comeFrom;
	}

	public void setComeFrom(String comeFrom) {
		this.comeFrom = comeFrom;
	}
	@Text(label = "")
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Text(label = "")
	public Date getBirthday() {
		return birthday;
	}

}
