package cn.itcast.bbs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author 传智播客.汤阳光 Apr 20, 2008
 */
public class PropertiesUtil {
	private static Log log = LogFactory.getLog(PropertiesUtil.class);
	
	public static void loadFileToProperties(Properties p, String file) {
		if (!new File(file).exists()) {
			log.info("文件[" + file + "] 不存在!");
			return;
		}
		log.debug("正在加载文件[" + file + "] ...");
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			p.load(fis);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			if (fis != null) {
				try {
					fis.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void storePropertiesToFile(Properties p, String file) {
		FileOutputStream fos = null;
		try {
			log.debug("正在写文件 [" + file + "]");
			fos = new FileOutputStream(file);
			p.store(fos, "By CommonUtils.storePropertiesToFile, " + new Date());
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			if (fos != null) {
				try {
					fos.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
