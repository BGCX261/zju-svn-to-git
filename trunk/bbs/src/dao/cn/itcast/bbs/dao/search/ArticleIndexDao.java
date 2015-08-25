package cn.itcast.bbs.dao.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.compass.core.Compass;
import org.compass.core.CompassCallback;
import org.compass.core.CompassException;
import org.compass.core.CompassHits;
import org.compass.core.CompassQuery;
import org.compass.core.CompassSession;
import org.compass.core.CompassTemplate;
import org.compass.core.config.CompassConfiguration;
import org.compass.core.config.CompassEnvironment;
import org.compass.core.lucene.LuceneEnvironment;
import org.springframework.stereotype.Repository;

import cn.itcast.bbs.cfg.SystemGlobals;
import cn.itcast.bbs.entities.QueryResult;
import cn.itcast.bbs.entities.article.Reply;
import cn.itcast.bbs.entities.article.Topic;
import cn.itcast.bbs.entities.search.SearchArgs;
import cn.itcast.bbs.entities.search.SearchableArticle;

/**
 * @author 传智播客.汤阳光 May 18, 2008
 * 
 * FIXME 抽出接口, 并实现如 GenericDao 的结构.
 */
@Repository("articleIndexDao")
public class ArticleIndexDao {
	private static Log log = LogFactory.getLog(ArticleIndexDao.class);
	private Compass compass;
	private int fragmentSize = 200; // 高亮后返回的文字片断的大小

	/** 初始化 Compass */
	public void init() {
		String connection = SystemGlobals.getSettings().getLuceneIndexPath();
		CompassConfiguration cfg = new CompassConfiguration()//
				.configure()//
				.setConnection(connection)//
				.addClass(SearchableArticle.class)//
				.setSetting("compass.engine.highlighter.default.fragmenter.simple.size", fragmentSize);
		compass = cfg.buildCompass();
		fragmentSize = cfg.getSettings().getSettingAsInt(LuceneEnvironment.Highlighter.Fragmenter.SIMPLE_SIZE, fragmentSize);

		log.info("已初始化Compass【connection=" + cfg.getSettings().getSetting(CompassEnvironment.CONNECTION) + "】");
	}

	/** 销毁 Compass */
	public void close() {
		if (compass != null) {
			compass.close();
		}
	}

	/**
	 * @return 被索引的文章的总数量
	 */
	public int totalDocs() {
		CompassTemplate ct = new CompassTemplate(compass);
		return ct.execute(new CompassCallback<Long>() {
			public Long doInCompass(CompassSession session) {
				return session.queryBuilder()//
						.matchAll()//
						.setAliases(SearchableArticle.class.getSimpleName())//
						.count();
			}
		}).intValue();
	}

	/** 创建索引 */
	public void create(SearchableArticle article) {
		CompassTemplate compassTemplate = new CompassTemplate(compass);
		compassTemplate.create(article);
	}

	/** 删除索引 */
	public void delete(SearchableArticle article) {
		CompassTemplate compassTemplate = new CompassTemplate(compass);
		compassTemplate.delete(article);
	}

	/**
	 * 重新创建索引
	 * 
	 * @param list
	 * @param args
	 * @return 重新创建的索引记录的数量
	 */
	public int save(final SearchableArticle... articles) {
		CompassTemplate ct = new CompassTemplate(compass);
		return ct.execute(new CompassCallback<Integer>() {
			public Integer doInCompass(CompassSession session) {
				if (articles == null) {
					return 0;
				}

				int count = 0;
				for (SearchableArticle article : articles) {
					session.save(article);
					count++;
				}
				return count;
			}
		});
	}

	/** 查询 */
	public QueryResult<SearchableArticle> find(final SearchArgs args, final int first, final int max) {
		CompassTemplate compassTemplate = new CompassTemplate(compass);

		return compassTemplate.execute(new CompassCallback<QueryResult<SearchableArticle>>() {

			public QueryResult<SearchableArticle> doInCompass(CompassSession session) {
				List<SearchableArticle> resultList = new ArrayList<SearchableArticle>();
				CompassHits hits = args.buildCompassQuery(session.queryBuilder()).hits();

				int end = Math.min(first + max, hits.getLength());
				for (int i = first; i < end; i++) {
					SearchableArticle p = (SearchableArticle) hits.data(i);

					// 如果这个字段中没有出现相应关键字, 则返回 null, 所以要先判断一下
					String ht = hits.highlighter(i).fragment("title");
					String hc = hits.highlighter(i).fragment("content");
					if (hc == null) {
						String content = p.getContent();
						hc = content.substring(0, Math.min(fragmentSize, content.length()));
					}

					p.setTitle(ht != null ? ht : p.getTitle());
					p.setContent(hc);

					resultList.add(p);
				}

				return new QueryResult<SearchableArticle>(hits.getLength(), resultList);
			}
		});
	}

	/**
	 * 删除属于指定主题的所有文章
	 * 
	 * @param
	 * @return 删除的索引的数量
	 */
	public int deleteByTopicId(final int topicId) {
		CompassTemplate ct = new CompassTemplate(compass);
		return ct.execute(new CompassCallback<Long>() {
			public Long doInCompass(CompassSession session) throws CompassException {
				CompassQuery query = session.queryBuilder().term("topicId", topicId);
				session.delete(query);
				return query.count();
			}
		}).intValue();
	}

	/**
	 * 更新主题中所有的文章的索引
	 * 
	 * @param topic
	 */
	public void updateAllArticles(final Topic topic) {
		CompassTemplate ct = new CompassTemplate(compass);
		ct.execute(new CompassCallback<Object>() {
			public Object doInCompass(CompassSession session) throws CompassException {
				session.save(new SearchableArticle(topic));
				for (Reply r : topic.getReplies()) {
					session.save(new SearchableArticle(r));
				}
				session.flush();
				return null;
			}
		});
	}
}
