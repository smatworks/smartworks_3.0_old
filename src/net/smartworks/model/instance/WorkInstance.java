package net.smartworks.model.instance;

import java.util.Date;

import net.smartworks.model.community.User;
import net.smartworks.model.work.Work;
import net.smartworks.util.LocalDate;

public class WorkInstance extends Instance {

	private TaskInstance currentTask;
	private User lastModifier;

	public TaskInstance getCurrentTask() {
		return currentTask;
	}

	public void setCurrentTask(TaskInstance currentTask) {
		this.currentTask = currentTask;
	}

	public User getLastModifier() {
		return lastModifier;
	}

	public void setLastModifier(User lastModifier) {
		this.lastModifier = lastModifier;
	}

	public WorkInstance() {
		super();
	}

	public WorkInstance(String id, String subject, Work work, User owner,
			LocalDate lastModifiedDate) {
		super(id, subject, Instance.INSTANCE_TYPE_WORK, owner, lastModifiedDate);
		super.setWork(work);
		this.lastModifier = owner;
	}

}
