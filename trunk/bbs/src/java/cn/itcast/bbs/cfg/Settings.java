package cn.itcast.bbs.cfg;

import java.util.Properties;

import cn.itcast.bbs.entities.User;
import cn.itcast.bbs.entities.privilege.Group;
import cn.itcast.bbs.exception.runtime.ItcastException;

/**
 * 系统配置
 * 
 * @author 汤阳光 Nov 19, 2008
 */

public class Settings implements Cloneable {
	private Properties properties = new Properties();

	// 论坛名称, 描述
	private String name;
	private String version = "jszuaabbs 1.0";
	private String description;

	private boolean installed; // 是否已安装

	private String applicationPath; // 应用程序的真实路径
	private String applicationConfigPath; // 存放配置文件的路径
	private String avatarStorePath; // 头像保存的基路径
	private String attachmentStorePath; // 附件保存的基路径
	private String luceneIndexPath;// lucene索引文件的路径

	private int adminUserId;
	private int anonymousUserId;
	private int defaultUserGroupId;

	private User adminUser;
	private User anonymousUser;
	private Group defaultUserGroup;

	// 页面中显示的页码的数量, 每页显示用户数量, 每页显示主题数量, 每页显示文章数量, 每页显示搜索结果数量
	private int viewPageCount;
	private int usersPerPage;
	private int topicsPerPage;
	private int repliesPerPage;
	private int searchResultPerPage;

	// 登陆允许尝试次数, 超过尝试次数锁定登陆时间(分钟)
	private int loginTimesLimit;
	private int loginLockedMinutes;

	// 头像文件的最大大小, 头像的最大宽度(像素), 头像的最大高度(像素)
	private int avatarMaxFileSize;
	private int avatarMaxWidth;
	private int avatarMaxHeight;

	// 一篇文章中最多允许的附件的数量, 附件的最大大小
	private int attachmentMaxAmount;
	private int attachmentMaxFileSize;

	// 两次发帖的间隔时间
	private int postDelay;

	// --

	Settings(Properties props) {
		this.properties = props;
	}

	// --

	@Override
	public Settings clone() {
		try {
			return (Settings) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new ItcastException(e);
		}
	}

	// --

	public String getName() {
		return name;
	}

	public int getUsersPerPage() {
		return usersPerPage;
	}

	public int getTopicsPerPage() {
		return topicsPerPage;
	}

	public int getSearchResultPerPage() {
		return searchResultPerPage;
	}

	public int getLoginTimesLimit() {
		return loginTimesLimit;
	}

	public int getLoginLockedMinutes() {
		return loginLockedMinutes;
	}

	public String getDescription() {
		return description;
	}

	public int getAvatarMaxFileSize() {
		return avatarMaxFileSize;
	}

	public int getAvatarMaxWidth() {
		return avatarMaxWidth;
	}

	public int getAvatarMaxHeight() {
		return avatarMaxHeight;
	}

	public int getAttachmentMaxAmount() {
		return attachmentMaxAmount;
	}

	public int getAttachmentMaxFileSize() {
		return attachmentMaxFileSize;
	}

	public int getPostDelay() {
		return postDelay;
	}

	public boolean isInstalled() {
		return installed;
	}

	public String getApplicationPath() {
		return applicationPath;
	}

	public String getApplicationConfigPath() {
		return applicationConfigPath;
	}

	public int getViewPageCount() {
		return viewPageCount;
	}

	public String getAvatarStorePath() {
		return avatarStorePath;
	}

	public String getAttachmentStorePath() {
		return attachmentStorePath;
	}

	public int getAdminUserId() {
		return adminUserId;
	}

	public int getAnonymousUserId() {
		return anonymousUserId;
	}

	public int getDefaultUserGroupId() {
		return defaultUserGroupId;
	}

	public User getAdminUser() {
		return adminUser;
	}

	public User getAnonymousUser() {
		return anonymousUser;
	}

	public Group getDefaultUserGroup() {
		return defaultUserGroup;
	}

	// -- default修饰的setter, 不想在其他地方修改系统和配置

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getLuceneIndexPath() {
		return luceneIndexPath;
	}

	public void setLuceneIndexPath(String luceneIndexPath) {
		this.luceneIndexPath = luceneIndexPath;
	}

	public int getRepliesPerPage() {
		return repliesPerPage;
	}

	public void setRepliesPerPage(int repliesPerPage) {
		this.repliesPerPage = repliesPerPage;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setInstalled(boolean installed) {
		this.installed = installed;
	}

	public void setApplicationPath(String applicationPath) {
		this.applicationPath = applicationPath;
	}

	public void setApplicationConfigPath(String applicationConfigPath) {
		this.applicationConfigPath = applicationConfigPath;
	}

	public void setAvatarStorePath(String avatarStorePath) {
		this.avatarStorePath = avatarStorePath;
	}

	public void setAttachmentStorePath(String attachmentStorePath) {
		this.attachmentStorePath = attachmentStorePath;
	}

	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
	}

	public void setAnonymousUserId(int anonymousUserId) {
		this.anonymousUserId = anonymousUserId;
	}

	public void setDefaultUserGroupId(int defaultUserGroupId) {
		this.defaultUserGroupId = defaultUserGroupId;
	}

	public void setAdminUser(User adminUser) {
		this.adminUser = adminUser;
	}

	public void setAnonymousUser(User anonymousUser) {
		this.anonymousUser = anonymousUser;
	}

	public void setDefaultUserGroup(Group defaultUserGroup) {
		this.defaultUserGroup = defaultUserGroup;
	}

	public void setViewPageCount(int viewPageCount) {
		this.viewPageCount = viewPageCount;
	}

	public void setUsersPerPage(int usersPerPage) {
		this.usersPerPage = usersPerPage;
	}

	public void setTopicsPerPage(int topicsPerPage) {
		this.topicsPerPage = topicsPerPage;
	}

	public void setSearchResultPerPage(int searchResultPerPage) {
		this.searchResultPerPage = searchResultPerPage;
	}

	public void setLoginTimesLimit(int loginTimesLimit) {
		this.loginTimesLimit = loginTimesLimit;
	}

	public void setLoginLockedMinutes(int loginLockedMinutes) {
		this.loginLockedMinutes = loginLockedMinutes;
	}

	public void setAvatarMaxFileSize(int avatarMaxFileSize) {
		this.avatarMaxFileSize = avatarMaxFileSize;
	}

	public void setAvatarMaxWidth(int avatarMaxWidth) {
		this.avatarMaxWidth = avatarMaxWidth;
	}

	public void setAvatarMaxHeight(int avatarMaxHeight) {
		this.avatarMaxHeight = avatarMaxHeight;
	}

	public void setAttachmentMaxAmount(int attachmentMaxAmount) {
		this.attachmentMaxAmount = attachmentMaxAmount;
	}

	public void setAttachmentMaxFileSize(int attachmentMaxFileSize) {
		this.attachmentMaxFileSize = attachmentMaxFileSize;
	}

	public void setPostDelay(int postDelay) {
		this.postDelay = postDelay;
	}

	public void checkSettings() {
		if (this.adminUser == null //
				|| this.anonymousUser == null //
				|| this.defaultUserGroup == null) {
			throw new RuntimeException("系统数据不正确，请重新初始化系统数据");
		}
	}

	@Override
	public String toString() {
		return new StringBuffer().append("\n>-------------------------------------------------------------<\n") //
				.append("name = ").append(name).append("\n") //
				.append("description = ").append(description).append("\n") //
				.append("installed = ").append(installed).append("\n") //
				.append("applicationPath = ").append(applicationPath).append("\n") //
				.append("applicationConfigPath = ").append(applicationConfigPath).append("\n") //
				.append("avatarStorePath = ").append(avatarStorePath).append("\n") //
				.append("attachmentStorePath = ").append(attachmentStorePath).append("\n") //
				.append("luceneIndexPath = ").append(luceneIndexPath).append("\n") //

				.append("adminUserId = ").append(adminUserId).append("\n")//
				.append("anonymousUserId = ").append(anonymousUserId).append("\n")//
				.append("defaultUserGroupId = ").append(defaultUserGroupId).append("\n")//

				.append("usersPerPage = ").append(usersPerPage).append("\n") //
				.append("topicsPerPage = ").append(topicsPerPage).append("\n") //
				.append("repliesPerPage = ").append(repliesPerPage).append("\n") //
				.append("searchResultPerPage = ").append(searchResultPerPage).append("\n") //

				.append("loginTimesLimit = ").append(loginTimesLimit).append("\n") //
				.append("loginLockedMinutes = ").append(loginLockedMinutes).append("\n") //

				.append("avatarMaxFileSize = ").append(avatarMaxFileSize).append("\n") //
				.append("avatarMaxWidth = ").append(avatarMaxWidth).append("\n") //
				.append("avatarMaxHeight = ").append(avatarMaxHeight).append("\n") //

				.append("attachmentMaxAmount = ").append(attachmentMaxAmount).append("\n") //
				.append("attachmentMaxFileSize = ").append(attachmentMaxFileSize).append("\n") //

				.append("postDelay = ").append(postDelay).append("\n") //
				.append(">-------------------------------------------------------------<\n")//
				.toString();
	}

	public static void main(String[] args) {
		System.out.println(new Settings(null));
	}

}
