package com.jsict.platform.form;


import com.jsict.base.form.BaseListForm;

public class OrganizeSearchForm extends BaseListForm{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8947103319617830205L;

	private String name;
	
	private   Integer[]   select=new   Integer[0]; 
	
	private Integer selectAll;
	
	private Integer editItem;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer[] getSelect() {
		return select;
	}

	public void setSelect(Integer[] select) {
		this.select = select;
	}

	public Integer getSelectAll() {
		return selectAll;
	}

	public void setSelectAll(Integer selectAll) {
		this.selectAll = selectAll;
	}

	public Integer getEditItem() {
		return editItem;
	}

	public void setEditItem(Integer editItem) {
		this.editItem = editItem;
	}
}
