package cn.itcast.bbs.entities.article;


/**
 * 版面(二级版面)
 * 
 * @author 传智博客.汤阳光 Jun 19, 2008
 */
public class Forum {

	private int id;
	private String name;// 名称
	private String description;// 描述
	private int order;// 显示顺序
	private int topicCount;// 总主题数量
	private int articleCount;// 总文章数量
	private Topic lastTopic;// 最后发表的文章
	private Category category;// 所属的分类

	public Forum() {}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getOrder() {
		return order;
	}

	public int getTopicCount() {
		return topicCount;
	}

	public int getArticleCount() {
		return articleCount;
	}

	public Topic getLastTopic() {
		return lastTopic;
	}

	public Category getCategory() {
		return category;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public void setTopicCount(int topicCount) {
		this.topicCount = topicCount;
	}

	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}

	public void setLastTopic(Topic lastTopic) {
		this.lastTopic = lastTopic;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return new StringBuffer().append("[Forum: ")//
				.append("id=").append(id)//
				.append(",name=").append(name)//
				.append(",description=").append(description)//
				.append(",topicCount=").append(topicCount)//
				.append(",articleCount=").append(articleCount)//
				.append("]")//
				.toString();
	}

}
