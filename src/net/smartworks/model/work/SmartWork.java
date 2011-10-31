package net.smartworks.model.work;

public class SmartWork extends Work {

	public static int 	TYPE_GROUP 		= 11;
	public static int 	TYPE_INFORMATION 	= 12;
	public static int 	TYPE_PROCESS 		= 13;
	public static int 	TYPE_SCHEDULE 		= 14;
	
	private String 	fullpathName = null;
	private Work   	myGroup = null;
	private WorkCategory myCategory = null;

	public String getFullpathName() {
		return this.myCategory.getName() + ( (this.myGroup != null && this.myGroup.getId()!= null) ? ">" + this.myGroup.getName() : "") + ">" + super.getName();
	}
	public void setFullpathName(String fullpathName) {
		this.fullpathName = fullpathName;
	}
	public Work getMyGroup() {
		return myGroup;
	}
	public void setMyGroup(Work myGroup) {
		this.myGroup = myGroup;
	}
	public WorkCategory getMyCategory() {
		return myCategory;
	}
	public void setMyCategory(WorkCategory myCategory) {
		this.myCategory = myCategory;
	}

	public SmartWork(){
		super();
	}
	public SmartWork(String id, String name){
		super(id, name);
	}
	public SmartWork(String id, String name, int type, String desc, WorkCategory myCategory){
		super(id, name, type, desc);
		this.myCategory = myCategory;
	}

}
