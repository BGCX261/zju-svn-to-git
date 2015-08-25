package com.jsict.platform.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.jsict.base.form.BaseForm;
import com.jsict.base.util.Consts;

public class OrganizeAddForm extends BaseForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4697316584595308091L;
	private Integer organizeId;
	private String name;
	
	public Integer getOrganizeId() {
		return organizeId;
	}
	public void setOrganizeId(Integer organizeId) {
		this.organizeId = organizeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void reset(ActionMapping mapping, HttpServletRequest request){
		super.setActionType(Consts.ACTION_TYPE_INIT);
		System.out.println("RESET");
	}
}
