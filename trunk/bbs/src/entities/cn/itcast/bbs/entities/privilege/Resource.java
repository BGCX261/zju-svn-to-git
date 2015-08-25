package cn.itcast.bbs.entities.privilege;

import java.util.Arrays;
import java.util.List;

/**
 * 资源
 * 
 * @author 传智播客.汤阳光 Apr 24, 2009
 * 
 */
public enum Resource {
	
	TOPIC(	"主题", //
			new SupportedAction("发表主题", Action.CREATE),//
			new SupportedAction("删除主题", Action.DELETE),//
			new SupportedAction("修改主题", Action.EDIT),//
			new SupportedAction("移动主题", Action.MOVE)//
	),
			
	REPLY		("回复",//
			new SupportedAction("发表回复", Action.CREATE),//
			new SupportedAction("删除回复", Action.DELETE),//
			new SupportedAction("修改回复", Action.EDIT)//
	), 
			
	ATTACHMENT	("附件",//
			new SupportedAction("发表附件", Action.CREATE),//
			new SupportedAction("删除附件", Action.DELETE),//
			new SupportedAction("修改附件", Action.EDIT),//
			new SupportedAction("下载附件", Action.DOWNLOAD)//
	), 
	
	VOTE		("投票",//
			new SupportedAction("发表投票", Action.CREATE),//
			new SupportedAction("删除投票", Action.DELETE),//
			new SupportedAction("参与投票", Action.VOTE)//
	),
	
	SYSTEM		("后台管理",//
			new SupportedAction("后台管理", Action.SYSTEM_MANAGE)//
	);
	
	/**
	 * （某）资源支持的（某）操作
	 */
	public static class SupportedAction{
		private String name;
		private Action action;
		private SupportedAction(String name, Action action) { 
			this.name = name; 
			this.action = action; 
		}
		
		public String getName() { return name; }
		public Action getAction() { return action; }
	}
		
	private String name;
	private List<SupportedAction> supportedActions;
	private Resource(String name, SupportedAction... supportedActions) {
		this.name = name;
		this.supportedActions = Arrays.asList(supportedActions);
	}
	
	public String getValue() { return this.name(); }
	public String getName() { return this.name; }
	public List<SupportedAction> getSupportedActions() { return supportedActions; }
}
