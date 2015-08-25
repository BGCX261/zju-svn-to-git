package com.jsict.jszju;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Userinfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "userinfo")
public class Userinfo implements java.io.Serializable {

	// Fields

	private Integer id;
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
	private String marriage;
	private String checkstatus;
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

	// Constructors

	/** default constructor */
	public Userinfo() {
	}

	/** minimal constructor */
	public Userinfo(String name, String password) {
		this.name = name;
		this.password = password;
	}

	/** full constructor */
	public Userinfo(String name, String age, String sex, String password,
			String department, String role, String introduceself, String email,
			String contactway, String company, String marriage,
			String checkstatus, String realname, String cellphone,
			String birthday, String major, String graduyear, String number,
			String education, String title, String birthplace, String address,
			String postcode, String car, String interests, String speciality,
			String resource, String help, String advice) {
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.password = password;
		this.department = department;
		this.role = role;
		this.introduceself = introduceself;
		this.email = email;
		this.contactway = contactway;
		this.company = company;
		this.marriage = marriage;
		this.checkstatus = checkstatus;
		this.realname = realname;
		this.cellphone = cellphone;
		this.birthday = birthday;
		this.major = major;
		this.graduyear = graduyear;
		this.number = number;
		this.education = education;
		this.title = title;
		this.birthplace = birthplace;
		this.address = address;
		this.postcode = postcode;
		this.car = car;
		this.interests = interests;
		this.speciality = speciality;
		this.resource = resource;
		this.help = help;
		this.advice = advice;
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

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "age", length = 10)
	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Column(name = "sex", length = 10)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "password", nullable = false, length = 8)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "department", length = 30)
	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name = "role", length = 10)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Column(name = "introduceself", length = 65535)
	public String getIntroduceself() {
		return this.introduceself;
	}

	public void setIntroduceself(String introduceself) {
		this.introduceself = introduceself;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "contactway", length = 30)
	public String getContactway() {
		return this.contactway;
	}

	public void setContactway(String contactway) {
		this.contactway = contactway;
	}

	@Column(name = "company")
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "marriage", length = 4)
	public String getMarriage() {
		return this.marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	@Column(name = "checkstatus", length = 6)
	public String getCheckstatus() {
		return this.checkstatus;
	}

	public void setCheckstatus(String checkstatus) {
		this.checkstatus = checkstatus;
	}

	@Column(name = "realname", length = 20)
	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Column(name = "cellphone", length = 20)
	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@Column(name = "birthday", length = 30)
	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Column(name = "major", length = 30)
	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Column(name = "graduyear", length = 20)
	public String getGraduyear() {
		return this.graduyear;
	}

	public void setGraduyear(String graduyear) {
		this.graduyear = graduyear;
	}

	@Column(name = "number", length = 20)
	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name = "education", length = 20)
	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@Column(name = "title", length = 20)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "birthplace", length = 40)
	public String getBirthplace() {
		return this.birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	@Column(name = "address", length = 50)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "postcode", length = 7)
	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Column(name = "car", length = 20)
	public String getCar() {
		return this.car;
	}

	public void setCar(String car) {
		this.car = car;
	}

	@Column(name = "interests", length = 65535)
	public String getInterests() {
		return this.interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	@Column(name = "speciality")
	public String getSpeciality() {
		return this.speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	@Column(name = "resource")
	public String getResource() {
		return this.resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	@Column(name = "help")
	public String getHelp() {
		return this.help;
	}

	public void setHelp(String help) {
		this.help = help;
	}

	@Column(name = "advice")
	public String getAdvice() {
		return this.advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

}