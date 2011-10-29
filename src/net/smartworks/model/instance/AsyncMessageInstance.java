package net.smartworks.model.instance;

import net.smartworks.model.community.User;
import net.smartworks.util.LocalDate;

public class AsyncMessageInstance extends Instance {

	private User sender;
	private LocalDate sendDate;
	private String message;

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public LocalDate getSendDate() {
		return sendDate;
	}

	public void setSendDate(LocalDate sendDate) {
		this.sendDate = sendDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AsyncMessageInstance() {
		super();
	}

	public AsyncMessageInstance(String id, User sender, LocalDate sendDate,
			String message) {
		super(id, "", Instance.INSTANCE_TYPE_ASYNC_MESSAGE, sender, sendDate);
		this.sender = sender;
		this.sendDate = sendDate;
		this.message = message;
	}

}
