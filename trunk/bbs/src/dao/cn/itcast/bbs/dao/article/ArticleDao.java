package cn.itcast.bbs.dao.article;

import java.util.Date;

import cn.itcast.bbs.dao.base.GenericDao;
import cn.itcast.bbs.entities.QueryResult;
import cn.itcast.bbs.entities.article.Article;

public interface ArticleDao extends GenericDao<Article> {

	/**
	 * 查询发表时间在指定的范围的文章
	 * 
	 * @param start
	 * @param end *
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	QueryResult<Article> findByPostTimeRange(Date start, Date end, int firstResult, int maxResult);
}
