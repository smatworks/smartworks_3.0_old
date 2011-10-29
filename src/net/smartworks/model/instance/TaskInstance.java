package net.smartworks.model.instance;

import java.util.Date;

import net.smartworks.model.community.User;
import net.smartworks.model.community.WorkSpace;
import net.smartworks.model.work.SmartForm;
import net.smartworks.util.LocalDate;

public class TaskInstance extends Instance {

	public static int TASK_TYPE_INFORMATIONWORK_TASK_CREATED = 11;
	public static int TASK_TYPE_INFORMATIONWORK_TASK_UDATED = 12;
	public static int TASK_TYPE_INFORMATIONWORK_TASK_DELETED = 13;
	public static int TASK_TYPE_INFORMATIONWORK_TASK_ASSIGNED = 14;
	public static int TASK_TYPE_INFORMATIONWORK_TASK_FORWARDED = 15;

	public static int TASK_TYPE_PROCESSWORK_TASK_ASSIGNED = 21;
	public static int TASK_TYPE_PROCESSWORK_TASK_FORWARDED = 22;

	public static int TASK_TYPE_SCHEDULEWORK_TASK_ASSIGNED = 31;
	public static int TASK_TYPE_SCHEDULEWORK_TASK_FORWARDED = 32;

	public static int TASK_TYPE_APPROVALWORK_TASK_ASSIGNED = 41;

	private String name;
	private int taskType;
	private WorkInstance workInstance;
	private User assignee;
	private User performer;
	private SmartForm smartForm;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTaskType() {
		return taskType;
	}

	public void setTaskType(int taskType) {
		this.taskType = taskType;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	public WorkInstance getWorkInstance() {
		return workInstance;
	}

	public void setWorkInstance(WorkInstance workInstance) {
		this.workInstance = workInstance;
	}

	public User getPerformer() {
		return performer;
	}

	public void setPerformer(User performer) {
		this.performer = performer;
	}

	public SmartForm getSmartForm() {
		return smartForm;
	}

	public void setSmartForm(SmartForm smartForm) {
		this.smartForm = smartForm;
	}

	public TaskInstance() {
		super();
	}

	public TaskInstance(String id, String name, int taskType, User owner,
			LocalDate lastModifiedDate) {
		super(id, name, Instance.INSTANCE_TYPE_TASK, owner, lastModifiedDate);
		this.name = name;
		this.taskType = taskType;
	}
}
