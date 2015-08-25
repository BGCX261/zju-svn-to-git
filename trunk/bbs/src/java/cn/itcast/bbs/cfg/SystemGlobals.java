package cn.itcast.bbs.cfg;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import cn.itcast.bbs.dao.search.ArticleIndexDao;
import cn.itcast.bbs.utils.PropertiesUtil;

/**
 * 用于读取或操作配置文件, default.properties和custom,properties文件<br>
 * 此类不能被实例化
 * 
 * @author 传智播客.汤阳光 Jun 5, 2008
 */
public class SystemGlobals {
	private static Log log = LogFactory.getLog(SystemGlobals.class);

	private static Properties defaults = new Properties(); // 存放default.properties中的配置
	private static Properties custom = new Properties(); // 存放custom.properties中的配置
	private static Properties transientValues = new Properties(); // 存放临时配置

	private static SettingsFactory settingsFactory = new SettingsFactory();
	private static Settings settings;

	private static String defaultConfig; // 默认配置文件(默认为default.properties)
	private static ApplicationContext applicationContext;

	private SystemGlobals() { // 私有的构造方法
	}

	/**
	 * 初始化, 按以下顺序加载配置：<br>
	 * 1. 系统默认配置default.properties <br>
	 * 2. 数据库默认配置文件${database.type}.properties <br>
	 * 3. 用户定制配置文件custom.properties
	 * 
	 * @param applicationPath
	 *            应用程序的路径
	 * @param defaultConfig
	 *            默认配置文件
	 */
	public static void init(String applicationPath, String defaultConfig, ApplicationContext applicationContext) {
		SystemGlobals.defaultConfig = defaultConfig;
		SystemGlobals.applicationContext = applicationContext;
		// 先设置一下, 后面加载配置文件的时候用到了这个路径
		SystemGlobals.setValue(Environment.APPLICATION_PATH, applicationPath);

		// 加载默认配置文件
		PropertiesUtil.loadFileToProperties(defaults, SystemGlobals.defaultConfig);

		// 加载custom.properties配置文件
		String customFile = getValue(Environment.APPLICATION_CONFIG_CUSTOM_PATH);
		PropertiesUtil.loadFileToProperties(custom, customFile); // 使用其中的 [数据库类型] 信息
		PropertiesUtil.loadFileToProperties(custom, getValue(Environment.APPLICATION_CONFIG_DATABASE_PATH)); // 加载数据库的默认配置文件
		PropertiesUtil.loadFileToProperties(custom, customFile); // 再加载一遍用户配置文件覆盖默认设置

		// 加载完配置文件后再设置应用程序的路径, 以防止配置文件中的路径会覆盖真实路径
		SystemGlobals.setValue(Environment.APPLICATION_PATH, applicationPath);

		// Build Settings
		Properties allProperties = getAllProperties(); // build settings
		SystemGlobals.settings = settingsFactory.buildSettings(allProperties, applicationContext);
		SystemGlobals.settings.setApplicationPath(applicationPath);

		// LuceneIndexDao需要使用Settings中的配置,所以要在生成Settings后再调用init方法
		ArticleIndexDao luceneIndexDao = (ArticleIndexDao) applicationContext.getBean("articleIndexDao");
		luceneIndexDao.init(); // initialize luceneIndexDao

		log.info("SystemGlobals初始化完成");
	}

	public static void saveSettings() {

	}

	/**
	 * 重新初始化(重新读取配置文件)
	 */
	public static void restart() {
		if (SystemGlobals.defaultConfig == null || applicationContext == null) {
			throw new IllegalStateException("请先初始化 SystemGlobals");
		}

		String appPath = getValue(Environment.APPLICATION_PATH);

		defaults.clear();
		custom.clear();
		transientValues.clear();
		settings = null;

		ArticleIndexDao luceneIndexDao = (ArticleIndexDao) applicationContext.getBean("articleIndexDao");
		luceneIndexDao.close(); // close luceneIndexDao's Compass object.

		init(appPath, defaultConfig, applicationContext);
	}

	// ---

	/**
	 * 保存设置到custom配置文件, 在加载配置文件时, 其中的配置就会覆盖默认的配置
	 */
	public static void saveCustomProperties(Properties props) {
		String customFile = getValue(Environment.APPLICATION_CONFIG_CUSTOM_PATH);
		PropertiesUtil.storePropertiesToFile(props, customFile);
	}

	/**
	 * @return 所有的配置, 已解析完所有变量
	 */
	public static Properties getAllProperties() {
		Properties props = new Properties();
		props.putAll(defaults);
		props.putAll(custom);
		props.putAll(transientValues);

		for (Object obj : props.keySet()) { // 解析变量
			String key = (String) obj;
			String value = getValue(key); //
			props.setProperty(key, value);
		}

		return props;
	}

	// ---

	/**
	 * 设置的key 和value 在调用saveCustom() 方法时会被保存
	 * 
	 * @param key
	 * @param value
	 */
	public static void setValue(String key, String value) {
		custom.setProperty(key, value);
	}

	/**
	 * 将解析用 "${" 和 "}" 包围的变量 取值顺序: 1) transientValues; 2) custom; 3) defaults;
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		String preValue = transientValues.getProperty(key);

		if (preValue == null) {
			preValue = custom.getProperty(key);
		}

		if (preValue == null) {
			preValue = defaults.getProperty(key);
		}

		if (preValue == null) {
			log.error("没有和 [" + key + "]  对应的值!!!");
			throw new NullPointerException("没有和 [" + key + "]  对应的值!!!");
		}

		return VariableExpander.expandVariables(preValue);
	}

	// ---

	/**
	 * 设置的信息只在本次运行时存在, 且在调用 getCustomProperties 方法时不会被保存到文件
	 * 
	 * @param key
	 * @param value
	 */
	public static void setTransientValue(String key, String value) {
		transientValues.put(key, value);
	}

	/**
	 * @param key
	 * @return 临时配置
	 */
	public static String getTransientValue(String key) {
		return transientValues.getProperty(key);
	}

	// ---

	public static Settings getSettings() {
		return settings;
	}

	public static void setSettings(Settings settings) {
		SystemGlobals.settings = settings;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static void setApplicationContext(ApplicationContext applicationContext) {
		SystemGlobals.applicationContext = applicationContext;
	}

}
