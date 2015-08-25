package cn.itcast.bbs.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.itcast.bbs.dao.ConfigDao;
import cn.itcast.bbs.entities.Config;

/**
 * @author 传智播客.汤阳光 Jun 25, 2008
 */
public class ConfigHelper {

	// 年-月-日 时-分-秒.毫秒 时区
	protected static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss.SSS Z");

	// ------------------------------------------------------
	// 获得配置
	// ------------------------------------------------------

	/**
	 * 
	 * @param configDao
	 * @param name
	 * @return 配置项name所对应的配置值
	 */
	public static String getValue(ConfigDao configDao, String name) {
		Config cfg = configDao.findByName(name);
		return cfg == null ? null : cfg.getValue();
	}

	/**
	 * 
	 * @param configDao
	 * @param name
	 * @param defaultValue
	 *            如果没有配置值，则返回此默认值
	 * @return 配置项name所对应的配置值
	 */
	public static String getValue(ConfigDao configDao, String name, String defaultValue) {
		String value = getValue(configDao, name);
		return value == null ? defaultValue : value;
	}

	/**
	 * 
	 * @param configDao
	 * @param name
	 * @return 配置项name所对应的配置值
	 */
	public static Integer getIntValue(ConfigDao configDao, String name) {
		String value = getValue(configDao, name);
		if (value == null) {
			return null;
		}

		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * 
	 * @param configDao
	 * @param name
	 * @param defaultValue
	 * @param defaultValue
	 *            如果没有配置值，则返回此默认值
	 * @return 配置项name所对应的配置值
	 */
	public static Integer getIntValue(ConfigDao configDao, String name, Integer defaultValue) {
		Integer result = getIntValue(configDao, name);
		return result == null ? defaultValue : result;
	}

	/**
	 * 
	 * @param configDao
	 * @param name
	 * @return 配置项name所对应的配置值
	 */
	public static Date getDateValue(ConfigDao configDao, String name) {
		String value = getValue(configDao, name);
		if (value == null) {
			return null;
		}

		try {
			return simpleDateFormat.parse(value);
		} catch (ParseException e) {
			return null;
		}
	}

	// ------------------------------------------------------
	// 设置配置
	// ------------------------------------------------------

	/**
	 * 设置配置，如果此配置项已存在，则为更新配置
	 * 
	 * @param configDao
	 * @param name
	 * @param date
	 */
	public static void setValue(ConfigDao configDao, String name, Date date) {
		String dateStr = simpleDateFormat.format(date);
		setValue(configDao, name, dateStr);
	}

	/**
	 * 设置配置，如果此配置项已存在，则为更新配置
	 * 
	 * @param configDao
	 * @param name
	 * @param number
	 */
	public static void setValue(ConfigDao configDao, String name, Number number) {
		String numberStr = String.valueOf(number);
		setValue(configDao, name, numberStr);
	}

	/**
	 * 设置配置，如果此配置项已存在，则为更新配置
	 * 
	 * @param configDao
	 * @param name
	 * @param value
	 */
	public static void setValue(ConfigDao configDao, String name, String value) {
		Config cfg = configDao.findByName(name);
		if (cfg == null) {
			cfg = new Config(name);
		}

		cfg.setValue(value);
		configDao.saveOrUpdate(cfg);
	}

}
