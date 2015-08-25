package cn.itcast.bbs.entities.log;

/**
 * 操作日志
 * 
 * @author 传智播客.汤阳光 Jul 16, 2008
 */
@SuppressWarnings("unchecked")
public class OperationLog extends SystemLog{

	/**
	 * 操作类型
	 * 
	 * @author 传智播客.汤阳光 Apr 25, 2009
	 * 
	 */
	public static enum OperationLogType {
		/** 锁定主题 */
		LOCK_TOPIC,
		/** 解锁主题 */
		UNLOCK_TOPIC,
		/** 移动主题 */
		MOVE_TOPICS,
		/** 删除主题 */
		DELETE_TOPIC,
		/** 删除回复 */
		DELETE_REPLY,
		/** 后台操作信息 */
		MANAGE_TRACE;
	}

	private Class entityType;// 所操作的实体(资源)类型
	private int entityId;// 所操作的实体的id
	private OperationLogType type;

	public OperationLog() {}

	public Class getEntityType() {
		return entityType;
	}

	public int getEntityId() {
		return entityId;
	}

	public OperationLogType getType() {
		return type;
	}

	public void setEntityType(Class entityType) {
		this.entityType = entityType;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public void setType(OperationLogType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return new StringBuffer().append("[OperationLog: ")//
				.append("id=").append(getId())//
				.append(",logTime=").append(getLogTime())//
				.append(",operator=").append(getOperator() == null ? null : getOperator().getLoginName())//
				.append(",operIpAddr=").append(getOperIpAddr())//
				.append(",comment=").append(getComment())//
				.append(",entityType=").append(entityType)//
				.append(",entityId=").append(entityId)//
				.append(",type=").append(type)//
				.append("]")//
				.toString();
	}
}
