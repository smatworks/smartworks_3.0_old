package net.smartworks.model.community;

public class User extends WorkSpace {

	private String 	position;
	private String 	locale;
	private String 	timeZone;
	private String 	department;
	private String 	company;
	private String 	picturePath = null;
	private String 	orgPictureName = null;
	private String 	midPictureName = null;
	private String 	minPictureName = null;
	
	public String getLongName() {
		return position + " " + super.getName();
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPicturePath() {
		return "images/";
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public String getOrgPictureName() {
		return 		super.getId() + ".jpg";
	}
	public void setOrgPictureName(String orgPictureName) {
		this.orgPictureName = orgPictureName;
	}
	public String getMidPictureName() {
		return super.getId() + "_mid.gif";
	}
	public void setMidPictureName(String midPictureName) {
		this.midPictureName = midPictureName;
	}
	public String getMinPictureName() {
		return super.getId() + "_min.gif";
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

	public User(){
		super();
	}
	public User(String id, String name){
		super(id, name);
	}
}
