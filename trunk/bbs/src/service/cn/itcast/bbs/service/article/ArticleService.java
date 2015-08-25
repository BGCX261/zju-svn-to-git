package cn.itcast.bbs.service.article;

import cn.itcast.bbs.entities.article.Article;

public interface ArticleService {

	/**
	 * 获取Article
	 * 
	 * @param id
	 * @return
	 */
	Article getArticle(int id);
}
