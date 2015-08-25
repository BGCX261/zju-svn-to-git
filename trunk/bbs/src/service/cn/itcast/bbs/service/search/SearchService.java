package cn.itcast.bbs.service.search;

import java.util.Date;

import cn.itcast.bbs.entities.PageInfo;
import cn.itcast.bbs.entities.search.SearchArgs;
import cn.itcast.bbs.entities.search.SearchableArticle;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
public interface SearchService {

	/**
	 * @param args
	 *            搜索条件
	 * @return
	 */
	PageInfo<SearchableArticle> search(SearchArgs args);

	/**
	 * @return 索引库中的总记录数量
	 */
	int getTotalIndexDocs();

	/**
	 * 为发表时间在指定时间范围的文章重新创建索引
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public int recreateArticleIndices(Date start, Date end);

	/**
	 * 重新创建所有的索引 (索引库将会重新创建)
	 * 
	 * @return
	 */
	public int recreateAllArticleIndices();
}
