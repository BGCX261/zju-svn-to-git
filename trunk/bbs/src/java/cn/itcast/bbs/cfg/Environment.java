package cn.itcast.bbs.cfg;

/**
 * @author 传智播客.汤阳光 Date Mar 28, 2008
 */
public final class Environment {

	private Environment() {}

	// =======================================================
	// 以下配置对应配置文件中的配置项 -- part 1
	// =======================================================

	/** 是否已安装 */
	public static final String INSTALLED = "installed";

	/**
	 * 匿名用户(anonymous)的id
	 */
	public static final String ANONYMOUS_USER_ID = "anonymous.user.id";

	/**
	 * 超级用户(admin)的id
	 */
	public static final String ADMIN_USER_ID = "admin.user.id";

	/**
	 * 新注册用户默认所属的组的id
	 */
	public static final String DEFAULT_USER_GROUP_ID = "default.user.group.id";

	// ===========================================
	// part 2, 路径配置
	// ===========================================

	/**
	 * 应用程序的路径
	 */
	public static final String APPLICATION_PATH = "application.path";

	/**
	 * 存放配置文件的路径
	 */
	public static final String APPLICATION_CONFIG_PATH = "application.config.path";

	/**
	 * 安装生成的配置文件(用户定制文件)的路径
	 */
	public static final String APPLICATION_CONFIG_CUSTOM_PATH = "application.config.custom.path";

	/**
	 * 数据库的默认配置文件
	 */
	public static final String APPLICATION_CONFIG_DATABASE_PATH = "application.config.database.path";

	/**
	 * 用户头像的保存路径
	 */
	public static final String APPLICATION_UPLOAD_AVATAR_PATH = "application.upload.avatar.path";

	/**
	 * 附件的保存路径
	 */
	public static final String APPLICATION_UPLOAD_ATTACHMENT_PATH = "application.upload.attachment.path";

	/**
	 * Lucene 索引的位置
	 */
	public static final String APPLICATION_LUCENE_INDEX_PATH = "application.lucene.index.path";

	// ===========================================
	// part 3, 论坛设置
	// ===========================================

	// ----------------------
	// base information
	// ----------------------

	/**
	 * 论坛名称
	 */
	public static final String NAME = "bbs.name";

	/**
	 * 论坛描述
	 */
	public static final String DESCRIPTION = "bbs.description";

	// ----------------------
	// page size
	// ----------------------

	/** 页面中最多显示的页码的数量 */
	public static final String PAGE_VIEW_PAGE_COUNT = "bbs.page.viewPageCount";

	/**
	 * 用户列表页面大小
	 */
	public static final String PAGE_SIZE_USER_LIST = "bbs.page.size.user";

	/**
	 * 主题列表页面大小
	 */
	public static final String PAGE_SIZE_TOPIC = "bbs.page.size.topic";

	/**
	 * 回复列表页面大小
	 */
	public static final String PAGE_SIZE_REPLY = "bbs.page.size.reply";

	/**
	 * 搜索结果列表页面大小
	 */
	public static final String PAGE_SIZE_SEARCH_RESULT = "bbs.page.size.search.result";

	// ----------------------
	// login
	// ----------------------

	/**
	 * 登陆允许尝试次数
	 */
	public static final String LOGIN_TIMES_LIMIT = "bbs.login.times.limit";

	/**
	 * 超过尝试次数锁定登陆时间(分钟)
	 */
	public static final String LOGIN_LOCKED_MINUTES = "bbs.login.locked.minutes";

	// ----------------------
	// avatar
	// ----------------------

	/**
	 * 头像文件的最大大小
	 */
	public static final String AVATAR_MAX_FILE_SIZE = "bbs.avatar.max.file.size";

	/**
	 * 头像的最大宽度(像素)
	 */
	public static final String AVATAR_MAX_WIDTH = "bbs.avatar.max.width";

	/**
	 * 头像的最大高度(像素)
	 */
	public static final String AVATAR_MAX_HEIGHT = "bbs.avatar.max.height";

	// ----------------------
	// attachment
	// ----------------------

	/**
	 * 一篇文章中最多允许的附件的数量
	 */
	public static final String ATTACHMENT_MAX_AMOUNT = "bbs.attachment.max.amount";

	/**
	 * 附件的最大大小 TODO 管理员和普通会员可以使用的空间不是一样大的
	 */
	public static final String ATTACHMENT_MAX_FILE_SIZE = "bbs.attachment.max.file.size";

	// ----------------------
	// other
	// ----------------------

	/**
	 * 两次发帖的最小间隔时间(秒)
	 */
	public static final String POST_DELAY = "bbs.postDelay";

	// ===========================================
	// part 4, 数据库设置
	// ===========================================

	// ----------------------
	// 连接信息
	// ----------------------

	/**
	 * 数据库类型
	 */
	public static final String DATABASE_TYPE = "database.type";

	/**
	 * 主机
	 */
	public static final String DATABASE_HOST = "database.host";

	/**
	 * 端口号
	 */
	public static final String DATABASE_PORT = "database.port";

	/**
	 * 数据库名称
	 */
	public static final String DATABASE_NAME = "database.name";

	/**
	 * 数据库的管理员的用户名
	 */
	public static final String DATABASE_ROOT_USER_NAME = "database.root.user.name";

	/**
	 * 连接数据库使用的用户名
	 */
	public static final String DATABASE_CONNECTION_USER_NAME = "database.connection.user.name";

	/**
	 * 连接数据库使用的密码
	 */
	public static final String DATABASE_CONNECTION_USER_PASSWORD = "database.connection.user.password";

	/**
	 * 连接字符串
	 */
	public static final String DATABASE_CONNECTION_URL = "database.connection.url";

	/**
	 * 驱动类
	 */
	public static final String DATABASE_DRIVER_CLASS_NAME = "database.connection.driver.class.name";

	// ===========================================
	// 其它设置 -- %%%%%%%%%%%%%%%%%%
	// ===========================================

	/* ======================================================= */

	/** 配置允许使用的 Html 元素及属性的配置文件 */
	public static final String BBS_SAFE_HTML_TAGS_FILE = "bbs.safeHtmlTagsFile";

	/** 过滤的短语列表 */
	public static final String BBS_BAD_WORDS_LIST_FILE = "bbs.badWordsListFile";

	// -----------

	/** 应用程序所支持的数据库 */
	public static final String DATABASE_SUPPORT_TYPES = "database.supportTypes";

	/* =========================================================== */

	public static final String HTML_WELCOME_TAGS = null;

	public static final String HTML_WELCOME_ATTRIBUTES = null;

	public static final String MAIL_ERROR_REPORTER_SINA_USERNAMES = "mail.error.reporter.sina.usernames";

}
