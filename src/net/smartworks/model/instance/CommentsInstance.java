package net.smartworks.model.instance;

import java.util.Date;

import net.smartworks.model.community.User;
import net.smartworks.model.work.Work;
import net.smartworks.util.LocalDate;

public class CommentsInstance extends Instance {

	public static int COMMENTS_TYPE_ON_WORK_DESC = 1;
	public static int COMMENTS_TYPE_ON_WORK_MANUAL = 2;
	public static int COMMENTS_TYPE_ON_WORK_INSTANCE = 3;
	public static int COMMENTS_TYPE_ON_TASK_INSTANCE = 4;

	private int commentsType;
	private Work work;
	private WorkInstance workInstance;
	private TaskInstance taskInstance;
	private User commentor;
	private String comments;

	public int getCommentsType() {
		return commentsType;
	}

	public void setCommentsType(int commentsType) {
		this.commentsType = commentsType;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public WorkInstance getWorkInstance() {
		return workInstance;
	}

	public void setWorkInstance(WorkInstance workInstance) {
		this.workInstance = workInstance;
	}

	public TaskInstance getTaskInstance() {
		return taskInstance;
	}

	public void setTaskInstance(TaskInstance taskInstance) {
		this.taskInstance = taskInstance;
	}

	public User getCommentor() {
		return commentor;
	}

	public void setCommentor(User commentor) {
		this.commentor = commentor;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public CommentsInstance() {
		super();
	}

	public CommentsInstance(String id, int commentsType, String comments,
			User commentor, LocalDate lastModifiedDate) {
		super(id, "", Instance.INSTANCE_TYPE_COMMENTS, commentor,
				lastModifiedDate);
		this.commentsType = commentsType;
		this.commentor = commentor;
		this.comments = comments;
	}
}
