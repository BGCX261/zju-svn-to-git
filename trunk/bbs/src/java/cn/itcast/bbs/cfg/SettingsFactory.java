package cn.itcast.bbs.cfg;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import cn.itcast.bbs.entities.User;
import cn.itcast.bbs.entities.privilege.Group;
import cn.itcast.bbs.service.UserService;
import cn.itcast.bbs.service.privilege.GroupService;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
public class SettingsFactory {
	private static final Log log = LogFactory.getLog(SettingsFactory.class);

	/**
	 * 用properties构建出一个Settings,
	 * 
	 * @param props
	 * @return
	 */
	public Settings buildSettings(Properties props, ApplicationContext applicationContext) {
		Settings settings = new Settings(props);

		// ---
		String name = getProp(props, Environment.NAME);
		String description = getProp(props, Environment.DESCRIPTION);

		settings.setName(name);
		settings.setDescription(description);

		// ---

		boolean installed = getBoolProp(props, Environment.INSTALLED);
		String applicationPath = getProp(props, Environment.APPLICATION_PATH);
		String applicationConfigPath = getProp(props, Environment.APPLICATION_CONFIG_PATH);
		String avatarStorePath = getProp(props, Environment.APPLICATION_UPLOAD_AVATAR_PATH);
		String attachmentStorePath = getProp(props, Environment.APPLICATION_UPLOAD_ATTACHMENT_PATH);
		String luceneIndexPath = getProp(props, Environment.APPLICATION_LUCENE_INDEX_PATH);

		settings.setInstalled(installed);
		settings.setApplicationPath(applicationPath);
		settings.setApplicationConfigPath(applicationConfigPath);
		settings.setAvatarStorePath(avatarStorePath);
		settings.setAttachmentStorePath(attachmentStorePath);
		settings.setLuceneIndexPath(luceneIndexPath);

		// ---

		int adminUserId = getIntProp(props, Environment.ADMIN_USER_ID);
		int anonymousUserId = getIntProp(props, Environment.ANONYMOUS_USER_ID);
		int defaultUserGroupId = getIntProp(props, Environment.DEFAULT_USER_GROUP_ID);

		settings.setAdminUserId(adminUserId);
		settings.setAnonymousUserId(anonymousUserId);
		settings.setDefaultUserGroupId(defaultUserGroupId);

		// --- TODO 使用了UserService和GroupService

		UserService userService = (UserService) applicationContext.getBean("userService");
		GroupService groupService = (GroupService) applicationContext.getBean("groupService");

		User adminUser = userService.getUser(adminUserId);
		User anonymousUser = userService.getUser(anonymousUserId);
		Group defaultUserGroup = groupService.getGroup(defaultUserGroupId);

		settings.setAdminUser(adminUser);
		settings.setAnonymousUser(anonymousUser);
		settings.setDefaultUserGroup(defaultUserGroup);

		if (settings.getAdminUser() == null) {
			log.error("AdminUser is null!");
		}
		if (settings.getAnonymousUser() == null) {
			log.error("AnonymousUser is null!");
		}
		if (settings.getDefaultUserGroup() == null) {
			log.error("DefaultUserGroup is null!");
		}

		// ---

		int viewPageCount = getIntProp(props, Environment.PAGE_VIEW_PAGE_COUNT);
		int usersPerPage = getIntProp(props, Environment.PAGE_SIZE_USER_LIST);
		int topicsPerPage = getIntProp(props, Environment.PAGE_SIZE_TOPIC);
		int repliesPerPage = getIntProp(props, Environment.PAGE_SIZE_REPLY);
		int searchResultPerPage = getIntProp(props, Environment.PAGE_SIZE_SEARCH_RESULT);

		settings.setViewPageCount(viewPageCount);
		settings.setUsersPerPage(usersPerPage);
		settings.setTopicsPerPage(topicsPerPage);
		settings.setRepliesPerPage(repliesPerPage);
		settings.setSearchResultPerPage(searchResultPerPage);

		// ---

		int loginTimesLimit = getIntProp(props, Environment.LOGIN_TIMES_LIMIT);
		int loginLockedMinutes = getIntProp(props, Environment.LOGIN_LOCKED_MINUTES);

		settings.setLoginTimesLimit(loginTimesLimit);
		settings.setLoginLockedMinutes(loginLockedMinutes);
		// ---

		int avatarMaxFileSize = getIntProp(props, Environment.AVATAR_MAX_FILE_SIZE);
		int avatarMaxWidth = getIntProp(props, Environment.AVATAR_MAX_WIDTH);
		int avatarMaxHeight = getIntProp(props, Environment.AVATAR_MAX_HEIGHT);

		settings.setAvatarMaxFileSize(avatarMaxFileSize);
		settings.setAvatarMaxWidth(avatarMaxWidth);
		settings.setAvatarMaxHeight(avatarMaxHeight);

		// ---

		int attachmentMaxAmount = getIntProp(props, Environment.ATTACHMENT_MAX_AMOUNT);
		int attachmentMaxFileSize = getIntProp(props, Environment.ATTACHMENT_MAX_FILE_SIZE);

		settings.setAttachmentMaxAmount(attachmentMaxAmount);
		settings.setAttachmentMaxFileSize(attachmentMaxFileSize);

		// ---

		int postDelay = getIntProp(props, Environment.POST_DELAY);
		settings.setPostDelay(postDelay);

		log.info("Settings构建完毕:" + settings);
		return settings;
	}

	public String getProp(Properties props, String key) {
		String value = props.getProperty(key);
		if (value == null) {
			throw new RuntimeException("配置[" + key + "]的值为null");
		}
		return value.trim(); // TODO trim ?
	}

	public int getIntProp(Properties props, String key) {
		String value = getProp(props, key);

		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			String msg = "(int类型)配置格式不正确[key=" + key + ", value=" + value + "]";
			log.error(msg);
			throw new RuntimeException(msg, e);
		}
	}

	public boolean getBoolProp(Properties props, String key) {
		String value = getProp(props, key);
		return "true".equals(value.trim());
	}

}
