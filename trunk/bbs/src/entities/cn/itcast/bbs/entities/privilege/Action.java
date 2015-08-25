package cn.itcast.bbs.entities.privilege;

/**
 * 对资源的操作
 * 
 * @author 传智播客.汤阳光 Apr 24, 2009
 * 
 */
public enum Action {

	/** 查询 / 查看 */
	 VIEW			(0x0001),

	/** 增加 / 创建 */
	CREATE			(0x0004),

	/** 更新 / 修改 */
	EDIT			(0x0008),

	/** 删除 */
	DELETE			(0x0010),
	
	/** 移动 */
	MOVE			(0x0020),

	/** 下载 */
	DOWNLOAD		(0x0040),
	
	/** 投票 */
	VOTE			(0x0080),
	
	/** 管理系统 */
	SYSTEM_MANAGE	(0x1000);
	
	private long flag;
	private Action(long flag) { this.flag = flag; }
	
	/** @return 标志位 */
	public long getFlag() { return this.flag; };

	public String getName() { return this.name(); }
}
