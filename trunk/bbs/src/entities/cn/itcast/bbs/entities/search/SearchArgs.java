package cn.itcast.bbs.entities.search;

import java.util.Date;

import org.compass.core.CompassQuery;
import org.compass.core.CompassQueryBuilder;
import org.compass.core.CompassQueryBuilder.CompassBooleanQueryBuilder;

/**
 * @author 传智播客.汤阳光 Jul 7, 2008
 */
public class SearchArgs {

	private boolean searchAuthorNameOnly = false;// 是否只是按作者昵称搜索文章
	private String queryString;// 搜索的字符串
	private String[] forumNames;// 在指定的版面中查询
	private Date startDate;// 文章发表时间的范围的开始时间
	private Date endDate;// 文章发表时间的范围的结束时间
	private int pageNum;// 页码

	public SearchArgs() {}

	/**
	 * 构建CompassQuery对象
	 * 
	 * @param queryBuilder
	 * @return
	 */
	// FIXME "百度 版主" 不会匹配 "百度知道_版主"
	public CompassQuery buildCompassQuery(CompassQueryBuilder queryBuilder) {
		CompassBooleanQueryBuilder boolQueryBuilder = queryBuilder.bool().addMust(queryBuilder.alias(SearchableArticle.class.getSimpleName()));

		if (searchAuthorNameOnly) {// 查询指定作者发表的文章
			CompassQuery authorQuery = queryBuilder.term("authorNickname", queryString);
			boolQueryBuilder.addMust(authorQuery);

		} else {// 在文章的标题和内容中进行查询
			CompassQuery articleTitleAndContentQuery = queryBuilder.multiPropertyQueryString(queryString)//
					.add("title")//
					.add("content")//
					.toQuery();
			boolQueryBuilder.addMust(articleTitleAndContentQuery);
		}

		// 限定文章发表的日期范围
		if (startDate != null && endDate != null) {
			CompassQuery dateRangeQuery = queryBuilder.between("postTime", startDate, endDate, true);
			boolQueryBuilder.addMust(dateRangeQuery);
		}

		// 限定文章所在的版面, 如果没有指定, 就认为是没有此项限制.
		if (forumNames != null && forumNames.length > 0) {
			for (String fname : forumNames) {
				CompassQuery forumQuery = queryBuilder.term("forumName", fname);
				boolQueryBuilder.addShould(forumQuery);
			}
		}

		return boolQueryBuilder.toQuery();
	}

	public boolean isSearchAuthorNameOnly() {
		return searchAuthorNameOnly;
	}

	public String getQueryString() {
		return queryString;
	}

	public String[] getForumNames() {
		return forumNames;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setSearchAuthorNameOnly(boolean searchAuthorNameOnly) {
		this.searchAuthorNameOnly = searchAuthorNameOnly;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public void setForumNames(String[] forumNames) {
		this.forumNames = forumNames;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

}
