package cn.itcast.bbs.service.impl;

import java.util.Properties;

import org.springframework.stereotype.Service;

import cn.itcast.bbs.cfg.Environment;
import cn.itcast.bbs.cfg.Settings;
import cn.itcast.bbs.cfg.SystemGlobals;
import cn.itcast.bbs.service.SystemService;
import cn.itcast.bbs.service.base.BaseService;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
@Service("systemService")
public class SystemServiceImpl extends BaseService implements SystemService {

	public int getTopicCount() {
		return topicDao.getTotal();
	}

	public int getArticleCount() {
		return articleDao.getTotal();
	}

	public int getUserCount() {
		return userDao.getTotal();
	}

	public void updateSettings(Settings cfg) {
		log.info("正在更新系统设置 ...");

		Properties props = new Properties();
		props.put(Environment.NAME, String.valueOf(cfg.getName()));
		props.put(Environment.DESCRIPTION, String.valueOf(cfg.getDescription()));

		props.put(Environment.PAGE_SIZE_USER_LIST, String.valueOf(cfg.getUsersPerPage()));
		props.put(Environment.PAGE_SIZE_TOPIC, String.valueOf(cfg.getTopicsPerPage()));
		props.put(Environment.PAGE_SIZE_REPLY, String.valueOf(cfg.getRepliesPerPage()));
		props.put(Environment.PAGE_SIZE_SEARCH_RESULT, String.valueOf(cfg.getSearchResultPerPage()));
		props.put(Environment.PAGE_VIEW_PAGE_COUNT, String.valueOf(cfg.getViewPageCount()));

		props.put(Environment.POST_DELAY, String.valueOf(cfg.getPostDelay()));

		props.put(Environment.AVATAR_MAX_FILE_SIZE, String.valueOf(cfg.getAvatarMaxFileSize()));
		props.put(Environment.AVATAR_MAX_WIDTH, String.valueOf(cfg.getAvatarMaxWidth()));
		props.put(Environment.AVATAR_MAX_HEIGHT, String.valueOf(cfg.getAvatarMaxHeight()));

		props.put(Environment.ATTACHMENT_MAX_AMOUNT, String.valueOf(cfg.getAttachmentMaxAmount()));
		props.put(Environment.ATTACHMENT_MAX_FILE_SIZE, String.valueOf(cfg.getAttachmentMaxFileSize()));

		SystemGlobals.saveCustomProperties(props);

		log.info("正在重新加载配置文件 ...");
		SystemGlobals.restart();
	}
}
