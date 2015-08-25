package cn.itcast.bbs.entities.article;

/**
 * 回复
 * 
 * @author 传智博客.汤阳光 Jun 19, 2008
 */
public class Reply extends Article {

	private int floor;// 楼层
	private Topic topic;// 所属的主题

	public Reply() {}

	public Topic getTopic() {
		return topic;
	}

	public int getFloor() {
		return floor;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	@Override
	public String toString() {
		return new StringBuffer().append("[Reply: ")//
				.append("id=").append(getId())//
				.append(",author=").append(getAuthor() == null ? null : getAuthor().getLoginName())//
				.append(",postTime=").append(getPostTime())//
				.append(",ipAddr=").append(getIpAddr())//
				.append(",topic=").append(topic == null ? null : topic.getTitle())//
				.append(",floor=").append(floor)//
				.append(",content=").append(getContent())//
				.append("]")//
				.toString();
	}
}
