package cn.itcast.bbs.web.struts.forms;

import org.apache.struts.action.ActionForm;

/**
 * @author 传智播客.汤阳光 Jul 5, 2008
 * @deprecated
 */
public class PermissionsForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private int groupId;
	private boolean administration;
	
	private int[] createArticle;
	private int[] createAttachment;
	private int[] createVote;
	
	public int getGroupId() {
		return groupId;
	}
	
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	
	public boolean isAdministration() {
		return administration;
	}
	
	public void setAdministration(boolean administration) {
		this.administration = administration;
	}
	
	public int[] getCreateArticle() {
		return createArticle;
	}
	
	public void setCreateArticle(int[] createArticle) {
		this.createArticle = createArticle;
	}
	
	public int[] getCreateAttachment() {
		return createAttachment;
	}
	
	public void setCreateAttachment(int[] createAttachment) {
		this.createAttachment = createAttachment;
	}
	
	public int[] getCreateVote() {
		return createVote;
	}
	
	public void setCreateVote(int[] createVote) {
		this.createVote = createVote;
	}
	
}
