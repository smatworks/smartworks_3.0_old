package net.smartworks.model.notice;

import java.util.Date;

import net.smartworks.model.BaseObject;
import net.smartworks.model.community.Group;
import net.smartworks.model.community.User;
import net.smartworks.model.instance.EventInstance;
import net.smartworks.model.instance.Instance;
import net.smartworks.model.work.SmartForm;
import net.smartworks.model.work.Work;
import net.smartworks.util.LocalDate;

public class NoticeMessage{

	public static int	TYPE_SYSTEM_NOTICE 	= 1;
	public static int	TYPE_EVENT_ALARM 		= 2;
	public static int	TYPE_TASK_DELAYED 		= 3;
	public static int	TYPE_JOIN_REQUEST 		= 4;
	public static int	TYPE_INSTANCE_CREATED 	= 5;
	
	private String			id;
	private int				type;
	private User			issuer;
	private LocalDate		issuedDate;
	private Instance 		instance;
	private EventInstance	event;
	private Group			group;	
	private String			message;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public User getIssuer() {
		return issuer;
	}
	public void setIssuer(User issuer) {
		this.issuer = issuer;
	}
	public LocalDate getIssuedDate() {
		return issuedDate;
	}
	public void setIssuedDate(LocalDate issuedDate) {
		this.issuedDate = issuedDate;
	}
	public Instance getInstance() {
		return instance;
	}
	public void setInstance(Instance instance) {
		this.instance = instance;
	}
	public EventInstance getEvent() {
		return event;
	}
	public void setEvent(EventInstance event) {
		this.event = event;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public NoticeMessage(){
		super();
	}
	public NoticeMessage(String id, int type, User issuer, LocalDate issuedDate){
		this.type = type;
		this.issuer = issuer;
		this.issuedDate = issuedDate;
	}
}
