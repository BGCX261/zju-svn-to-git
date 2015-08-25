package cn.itcast.bbs.service.search.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bbs.cfg.SystemGlobals;
import cn.itcast.bbs.entities.PageInfo;
import cn.itcast.bbs.entities.QueryResult;
import cn.itcast.bbs.entities.article.Article;
import cn.itcast.bbs.entities.article.Reply;
import cn.itcast.bbs.entities.article.Topic;
import cn.itcast.bbs.entities.search.SearchArgs;
import cn.itcast.bbs.entities.search.SearchableArticle;
import cn.itcast.bbs.service.base.BaseService;
import cn.itcast.bbs.service.search.SearchService;

/**
 * @author 传智博客.汤阳光 Jun 21, 2008
 */
@Service("searchService")
public class SearchServiceImpl extends BaseService implements SearchService {
	/** TODO 一次获取的实体的数量的最大限制 */
	public static int MAX_OPERATE_ENTITY_NUMBER = 100;

	public PageInfo<SearchableArticle> search(SearchArgs args) {
		int pageNum = args.getPageNum();
		int pageSize = SystemGlobals.getSettings().getSearchResultPerPage();
		int firstResult = PageInfo.calculateFirstResult(pageNum, pageSize);

		QueryResult<SearchableArticle> qr = articleIndexDao.find(args, firstResult, pageSize);
		return PageInfo.populate(qr, pageNum, pageSize);
	}

	public int getTotalIndexDocs() {
		return articleIndexDao.totalDocs();
	}

	@Transactional(readOnly = true)
	public int recreateAllArticleIndices() {
		// 如果一次就把所有的文章都拿到Session中,就有可能会内存溢出
		int pageSize = MAX_OPERATE_ENTITY_NUMBER;

		QueryResult<Article> qr = articleDao.findAll(0, pageSize);
		int totalPage = PageInfo.calculateTotalPage(qr.getTotal(), pageSize);
		int pageNum = 1;
		do {
			createIndices(qr.getItems());// 创建索引

			int firstResult = PageInfo.calculateFirstResult(pageNum, pageSize);
			qr = articleDao.findAll(firstResult, pageSize);
		} while (pageNum++ <= totalPage);

		return qr.getTotal();
	}

	@Transactional
	public int recreateArticleIndices(Date start, Date end) {
		int pageSize = MAX_OPERATE_ENTITY_NUMBER;

		QueryResult<Article> qr = articleDao.findByPostTimeRange(start, end, 0, pageSize);
		int totalPage = PageInfo.calculateTotalPage(qr.getTotal(), pageSize);
		int pageNum = 1;
		do {
			createIndices(qr.getItems());// 创建索引

			int firstResult = PageInfo.calculateFirstResult(pageNum, pageSize);
			qr = articleDao.findByPostTimeRange(start, end, firstResult, pageSize);
		} while (pageNum++ <= totalPage);

		return qr.getTotal();
	}

	/**
	 * 为文章创建索引
	 * 
	 * @param items
	 */
	protected void createIndices(List<Article> items) {
		SearchableArticle[] searchableArticles = new SearchableArticle[items.size()];
		for (int i = 0; i < searchableArticles.length; i++) {
			Article a = items.get(i);
			if (a instanceof Topic) {
				searchableArticles[i] = new SearchableArticle((Topic) a);
			} else if (a instanceof Reply) {
				searchableArticles[i] = new SearchableArticle((Reply) a);
			}
		}

		articleDao.flushAndClearSession();
		articleIndexDao.save(searchableArticles);
	}

}
