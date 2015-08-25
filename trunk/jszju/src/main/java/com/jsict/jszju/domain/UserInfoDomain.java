/**
 * LoginOnDomain.java        2009-8-25 下午09:37:42
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.domain;


import com.jsict.base.BaseDomain;
import com.jsict.base.util.Text;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">蔡锷</a>
 * @version 1.0
 */
public class UserInfoDomain extends BaseDomain {
	

	private String name;
	private String age;
	private String sex;
	private String password;
	private String department;
	private String role;
	private String introduceself;
	private String email;
	private String contactway;
	private String company;
	private String activitypoint;
	private String checkstatus;
	private String marriage;
	private String realname;
	private String cellphone;
	private String birthday;
	private String major;
	private String graduyear;
	private String number;
	private String education;
	private String title;
	private String birthplace;
	private String address;
	private String postcode;
	private String car;
	private String interests;
	private String speciality;
	private String resource;
	private String help;
	private String advice;

	@Text(label = "")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Text(label = "")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	@Text(label = "")
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	@Text(label = "")
	public String getIntroduceself() {
		return introduceself;
	}

	public void setIntroduceself(String introduceself) {
		this.introduceself = introduceself;
	}

	@Text(label = "")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Text(label = "")
	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	@Text(label = "")
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Text(label = "")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Text(label = "")
	public String getContactway() {
		return contactway;
	}

	public void setContactway(String contactway) {
		this.contactway = contactway;
	}
	@Text(label = "")
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	@Text(label = "")
	public String getActivitypoint() {
		return activitypoint;
	}

	public void setActivitypoint(String activitypoint) {
		this.activitypoint = activitypoint;
	}
	@Text(label = "")
	public String getCheckstatus() {
		return checkstatus;
	}

	public void setCheckstatus(String checkstatus) {
		this.checkstatus = checkstatus;
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
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
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
	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
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
	public String getHelp() {
		return help;
	}

	public void setHelp(String help) {
		this.help = help;
	}
	@Text(label = "")
	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

}
