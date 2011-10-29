package net.smartworks.model.instance;

import net.smartworks.model.community.User;
import net.smartworks.model.community.WorkSpace;
import net.smartworks.model.work.Work;
import net.smartworks.util.*;

public class Instance {

	public static int INSTANCE_TYPE_WORK = 1;
	public static int INSTANCE_TYPE_TASK = 2;
	public static int INSTANCE_TYPE_ASYNC_MESSAGE = 3;
	public static int INSTANCE_TYPE_NOTIFICATION = 4;
	public static int INSTANCE_TYPE_COMMENTS = 5;
	public static int INSTANCE_TYPE_MAIL = 6;
	public static int INSTANCE_TYPE_EVENT = 7;
	public static int INSTANCE_TYPE_FILE = 8;
	public static int INSTANCE_TYPE_IMAGE = 9;
	public static int INSTANCE_TYPE_MENO = 10;

	private String id;
	private String subject;
	private int instanceType;
	private Work work;
	private WorkSpace workSpace;
	private int status;
	private User owner;
	private LocalDate lastModifiedDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getInstanceType() {
		return instanceType;
	}

	public void setInstanceType(int instanceType) {
		this.instanceType = instanceType;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public WorkSpace getWorkSpace() {
		if (workSpace == null && owner != null)
			return owner;
		return workSpace;
	}

	public void setWorkSpace(WorkSpace workSpace) {
		this.workSpace = workSpace;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public LocalDate getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(LocalDate lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Instance() {
		super();
	}

	public Instance(String id, String subject, int instanceType, User owner,
			LocalDate lastModifiedDate) {
		super();
		this.id = id;
		this.subject = subject;
		this.instanceType = instanceType;
		this.workSpace = owner;
		this.owner = owner;
		this.lastModifiedDate = lastModifiedDate;
	}

}
