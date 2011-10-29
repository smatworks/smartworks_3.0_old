package net.smartworks.model.community;

import net.smartworks.model.BaseObject;

public class Group extends WorkSpace{

	private String	desc = null;
	private boolean	isPublic = false;
	private User	leader = null;
	private User[] 	members = null;
	private User	owner = null;
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
	public boolean isPublic() {
		return isPublic;
	}
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	public User getLeader() {
		return leader;
	}
	public void setLeader(User leader) {
		this.leader = leader;
	}
	public User[] getMembers() {
		return members;
	}
	public void setMembers(User[] members) {
		this.members = members;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
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

	public Group(){
		super();
	}
	public Group(String id, String name){
		super(id, name);
	}
	public Group(String id, String name, User[] members){
		
		super(id, name);
		this.members = members;
	}
	public Group(String id, String name, User[] members, User leader){
		
		super(id, name);
		this.members = members;
		this.leader = leader;
	}
}
