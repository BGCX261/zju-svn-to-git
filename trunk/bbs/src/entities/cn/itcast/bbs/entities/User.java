package cn.itcast.bbs.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import cn.itcast.bbs.entities.privilege.Group;

/**
 * 用户(会员)
 * 
 * @author 传智博客.汤阳光 Jun 19, 2008
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	// --- 基本信息
	private int id;
	private String loginName;// 登录名
	private String password;// 密码
	private String email;// Email地址

	private boolean active = true;// 是否是活动的(非锁定)
	private boolean deleted = false;// 是否已被删除

	private Date registrationTime;// 注册时间
	private Date lastVisitTime; // 最后(上次)访问时间
	private String lastVisitIpAddr; // 最后(上次)访问时所使用的ip地址

	private String autoLoginAuthKey;// 用于自动登陆的一个认证的字符串
	private String activationKey;// 锁定用户自己激活帐号所用的激活码

	private int topicCount;// 发表的总主题数
	private int articleCount;// 发表的总文章数

	private Set<Group> groups = new HashSet<Group>(0);// 所在的组

	// --- 个人信息
	private String nickname;// 昵称
	private Sex sex = Sex.SECRECY;// 性别, 默认为保密
	private Date birthday;// 生日
	private byte[] avatar;// 头像
	private String qq;
	private String msn;
	private String comeFrom;// 来自哪里
	private String signature;// 签名
	
	
	private String realname;	//真实姓名
	private String cellphone;	//常用手机	
	private String major;		//浙大毕业专业
	private String graduyear;	//毕业年份
	private String number;		//毕业证号
	private String education;	//学历
	private String title;		//职称
	private String birthplace;	//籍贯
	private String address;		//居住地址
	private String postcode;	//邮编
	private String marriage;	//婚姻状况
	private String car;			//常用交通工具
	private String speciality;	//本人特长
	private String resource;	//能为校友提供的资源
	private String advice;		//意见与建议
	private String activitypoint;//热心度
	private String memberid;//会员ID

	public User() {
	}

	public int getId() {
		return id;
	}

	public String getLoginName() {
		return loginName;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public boolean isActive() {
		return active;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public Date getLastVisitTime() {
		return lastVisitTime;
	}

	public String getAutoLoginAuthKey() {
		return autoLoginAuthKey;
	}

	public String getActivationKey() {
		return activationKey;
	}

	public int getTopicCount() {
		return topicCount;
	}

	public int getArticleCount() {
		return articleCount;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public String getNickname() {
		return nickname;
	}

	public Sex getSex() {
		return sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public String getQq() {
		return qq;
	}

	public String getMsn() {
		return msn;
	}

	public String getComeFrom() {
		return comeFrom;
	}

	public String getSignature() {
		return signature;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Date getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(Date registrationTime) {
		this.registrationTime = registrationTime;
	}

	public void setLastVisitTime(Date lastVisitTime) {
		this.lastVisitTime = lastVisitTime;
	}

	public void setAutoLoginAuthKey(String autoLoginAuthKey) {
		this.autoLoginAuthKey = autoLoginAuthKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

	public void setTopicCount(int topicCount) {
		this.topicCount = topicCount;
	}

	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public String getLastVisitIpAddr() {
		return lastVisitIpAddr;
	}

	public void setLastVisitIpAddr(String lastVisitIpAddr) {
		this.lastVisitIpAddr = lastVisitIpAddr;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public void setComeFrom(String comeFrom) {
		this.comeFrom = comeFrom;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((loginName == null) ? 0 : loginName.hashCode());
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
		final User other = (User) obj;
		if (loginName == null) {
			if (other.loginName != null)
				return false;
		} else if (!loginName.equals(other.loginName))
			return false;
		return true;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getGraduyear() {
		return graduyear;
	}

	public void setGraduyear(String graduyear) {
		this.graduyear = graduyear;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public String getActivitypoint() {
		return activitypoint;
	}

	public void setActivitypoint(String activitypoint) {
		this.activitypoint = activitypoint;
	}
	
	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	
	@Override
	public String toString() {
		return new StringBuffer().append("[User: ")//
				.append("id=").append(id)//
				.append(",loginName=").append(loginName)//
				.append(",email=").append(email)//
				.append(",registrationTime=").append(registrationTime)//
				.append(",nickname=").append(nickname)//
				.append(",active=").append(active)//
				.append(",deleted=").append(deleted)//
				.append(",topicCount=").append(topicCount)//
				.append(",articleCount=").append(articleCount)//
				.append(",realname=").append(realname)//
				.append(",cellphone=").append(cellphone)//
				.append(",major=").append(major)//
				.append(",graduyear=").append(graduyear)//
				.append(",number=").append(number)//
				.append(",education=").append(education)//
				.append(",title=").append(title)//
				.append(",birthplace=").append(birthplace)//
				.append(",address=").append(address)//
				.append(",postcode=").append(postcode)//
				.append(",marriage=").append(marriage)//
				.append(",car=").append(car)//
				.append(",speciality=").append(speciality)//
				.append(",resource=").append(resource)//
				.append(",advice=").append(advice)//
				.append(",activitypoint=").append(activitypoint)//
				.append(",memberid=").append(memberid)//
				.append("]")//
				.toString();
	}

}
