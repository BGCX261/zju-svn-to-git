package com.jsict.jszju.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ItcastUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "itcast_user")
public class ItcastUser implements java.io.Serializable {

	// Fields

	private Integer id;
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
	private Set<ItcastUsersgroups> itcastUsersgroupses = new HashSet<ItcastUsersgroups>(
			0);
	private Set<ItcastVoterecord> itcastVoterecords = new HashSet<ItcastVoterecord>(
			0);
	private Set<ItcastSystemlog> itcastSystemlogs = new HashSet<ItcastSystemlog>(
			0);
	private Set<ItcastArticle> itcastArticles = new HashSet<ItcastArticle>(0);

	// Constructors

	/** default constructor */
	public ItcastUser() {
	}

	/** full constructor */
	public ItcastUser(String loginName, String password, String email,
			String active, String deleted, Date registrationTime,
			Date lastVisitTime, String lastVisitIpAddr,
			String autoLoginAuthKey, String activationKey, Integer topicCount,
			Integer articleCount, String nickname, String sexName,
			Date birthday, String avatar, String qq, String msn,
			String comeFrom, String signature, String realname,
			String cellphone, String major, String graduyear, String number,
			String education, String title, String birthplace, String address,
			String postcode, String marriage, String car, String speciality,
			String resource, String advice, String activitypoint,
			Set<ItcastUsersgroups> itcastUsersgroupses,
			Set<ItcastVoterecord> itcastVoterecords,
			Set<ItcastSystemlog> itcastSystemlogs,
			Set<ItcastArticle> itcastArticles) {
		this.loginName = loginName;
		this.password = password;
		this.email = email;
		this.active = active;
		this.deleted = deleted;
		this.registrationTime = registrationTime;
		this.lastVisitTime = lastVisitTime;
		this.lastVisitIpAddr = lastVisitIpAddr;
		this.autoLoginAuthKey = autoLoginAuthKey;
		this.activationKey = activationKey;
		this.topicCount = topicCount;
		this.articleCount = articleCount;
		this.nickname = nickname;
		this.sexName = sexName;
		this.birthday = birthday;
		this.avatar = avatar;
		this.qq = qq;
		this.msn = msn;
		this.comeFrom = comeFrom;
		this.signature = signature;
		this.realname = realname;
		this.cellphone = cellphone;
		this.major = major;
		this.graduyear = graduyear;
		this.number = number;
		this.education = education;
		this.title = title;
		this.birthplace = birthplace;
		this.address = address;
		this.postcode = postcode;
		this.marriage = marriage;
		this.car = car;
		this.speciality = speciality;
		this.resource = resource;
		this.advice = advice;
		this.activitypoint = activitypoint;
		this.itcastUsersgroupses = itcastUsersgroupses;
		this.itcastVoterecords = itcastVoterecords;
		this.itcastSystemlogs = itcastSystemlogs;
		this.itcastArticles = itcastArticles;
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

	@Column(name = "loginName_", length = 64)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "password_", length = 45)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email_", length = 64)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "active_")
	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Column(name = "deleted_")
	public String getDeleted() {
		return this.deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "registrationTime_", length = 19)
	public Date getRegistrationTime() {
		return this.registrationTime;
	}

	public void setRegistrationTime(Date registrationTime) {
		this.registrationTime = registrationTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastVisitTime_", length = 19)
	public Date getLastVisitTime() {
		return this.lastVisitTime;
	}

	public void setLastVisitTime(Date lastVisitTime) {
		this.lastVisitTime = lastVisitTime;
	}

	@Column(name = "lastVisitIpAddr_")
	public String getLastVisitIpAddr() {
		return this.lastVisitIpAddr;
	}

	public void setLastVisitIpAddr(String lastVisitIpAddr) {
		this.lastVisitIpAddr = lastVisitIpAddr;
	}

	@Column(name = "autoLoginAuthKey_", length = 45)
	public String getAutoLoginAuthKey() {
		return this.autoLoginAuthKey;
	}

	public void setAutoLoginAuthKey(String autoLoginAuthKey) {
		this.autoLoginAuthKey = autoLoginAuthKey;
	}

	@Column(name = "activationKey_", length = 45)
	public String getActivationKey() {
		return this.activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

	@Column(name = "topicCount_")
	public Integer getTopicCount() {
		return this.topicCount;
	}

	public void setTopicCount(Integer topicCount) {
		this.topicCount = topicCount;
	}

	@Column(name = "articleCount_")
	public Integer getArticleCount() {
		return this.articleCount;
	}

	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}

	@Column(name = "nickname_", length = 64)
	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "sexName_")
	public String getSexName() {
		return this.sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "birthday_", length = 19)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "avatar_")
	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Column(name = "qq_", length = 15)
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "msn_", length = 64)
	public String getMsn() {
		return this.msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	@Column(name = "comeFrom_")
	public String getComeFrom() {
		return this.comeFrom;
	}

	public void setComeFrom(String comeFrom) {
		this.comeFrom = comeFrom;
	}

	@Column(name = "signature_", length = 65535)
	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Column(name = "realname_", length = 30)
	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Column(name = "cellphone_", length = 30)
	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@Column(name = "major_", length = 50)
	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Column(name = "graduyear_", length = 10)
	public String getGraduyear() {
		return this.graduyear;
	}

	public void setGraduyear(String graduyear) {
		this.graduyear = graduyear;
	}

	@Column(name = "number_", length = 50)
	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name = "education_", length = 30)
	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@Column(name = "title_", length = 50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "birthplace_")
	public String getBirthplace() {
		return this.birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	@Column(name = "address_")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "postcode_", length = 10)
	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Column(name = "marriage_", length = 10)
	public String getMarriage() {
		return this.marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	@Column(name = "car_", length = 20)
	public String getCar() {
		return this.car;
	}

	public void setCar(String car) {
		this.car = car;
	}

	@Column(name = "speciality_")
	public String getSpeciality() {
		return this.speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	@Column(name = "resource_")
	public String getResource() {
		return this.resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	@Column(name = "advice_")
	public String getAdvice() {
		return this.advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	@Column(name = "activitypoint_", length = 10)
	public String getActivitypoint() {
		return this.activitypoint;
	}

	public void setActivitypoint(String activitypoint) {
		this.activitypoint = activitypoint;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "itcastUser")
	public Set<ItcastUsersgroups> getItcastUsersgroupses() {
		return this.itcastUsersgroupses;
	}

	public void setItcastUsersgroupses(
			Set<ItcastUsersgroups> itcastUsersgroupses) {
		this.itcastUsersgroupses = itcastUsersgroupses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "itcastUser")
	public Set<ItcastVoterecord> getItcastVoterecords() {
		return this.itcastVoterecords;
	}

	public void setItcastVoterecords(Set<ItcastVoterecord> itcastVoterecords) {
		this.itcastVoterecords = itcastVoterecords;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "itcastUser")
	public Set<ItcastSystemlog> getItcastSystemlogs() {
		return this.itcastSystemlogs;
	}

	public void setItcastSystemlogs(Set<ItcastSystemlog> itcastSystemlogs) {
		this.itcastSystemlogs = itcastSystemlogs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "itcastUser")
	public Set<ItcastArticle> getItcastArticles() {
		return this.itcastArticles;
	}

	public void setItcastArticles(Set<ItcastArticle> itcastArticles) {
		this.itcastArticles = itcastArticles;
	}

}