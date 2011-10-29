package net.smartworks.model.community;

import net.smartworks.model.BaseObject;

public class Department extends WorkSpace {
	
	private String 	desc = null;
	private Department parent = null;
	private User	head = null;
	private User[]	members = null;
	private Department[] children = null;
	private String 	picturePath = null;
	private String 	orgPictureName = null;
	private String 	midPictureName = null;
	private String 	minPictureName = null;

	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Department getParent() {
		return parent;
	}
	public void setParent(Department parent) {
		this.parent = parent;
	}
	public User getHead() {
		return head;
	}
	public void setHead(User head) {
		this.head = head;
	}
	public User[] getMembers() {
		return members;
	}
	public void setMembers(User[] members) {
		this.members = members;
	}
	public Department[] getChildren() {
		return children;
	}
	public void setChildren(Department[] children) {
		this.children = children;
	}
	
	public String getPicturePath() {
		return "images/";
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public String getOrgPictureName() {
		return "pic_personal_info.jpg";
	}
	public void setOrgPictureName(String orgPictureName) {
		this.orgPictureName = orgPictureName;
	}
	public String getMidPictureName() {
		return "ico_group.gif";
	}
	public void setMidPictureName(String midPictureName) {
		this.midPictureName = midPictureName;
	}
	public String getMinPictureName() {
		return "ico_group.gif";
	}
	public void setMinPictureName(String minPictureName) {
		this.minPictureName = minPictureName;
	}
	public String getOrgPicture(){
		return getPicturePath() + getOrgPictureName();
	}
	public String getMidPicture(){
		return getPicturePath() + getMidPictureName();
	}
	public String getMinPicture(){
		return getPicturePath() + getMinPictureName();
	}

	public Department(){
		super();
	}
	
	public Department(String id, String name){
		super(id, name);
	}
	
	public Department(String id, String name, User[] members){

		super(id, name);
		this.members = members;
	}

	public Department(String id, String name, User[] members, User head){
		
		super(id, name);
		this.members = members;
		this.head = head;
	}
}
