package net.smartworks.model.work;

import net.smartworks.model.BaseObject;

public class WorkCategory extends BaseObject {
	
	private String 	desc = null;
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public WorkCategory(String id, String name){
		super(id, name);
	}	
}
