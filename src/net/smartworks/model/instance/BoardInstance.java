package net.smartworks.model.instance;

import net.smartworks.model.community.User;
import net.smartworks.model.work.Work;
import net.smartworks.util.LocalDate;

public class BoardInstance extends WorkInstance {
		
	private String content;
	private String attachment;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	
	public BoardInstance(){
		super();
		super.setInstanceType(Instance.TYPE_EVENT);
	}

	public BoardInstance(String id, String subject, Work work, User owner, LocalDate lastModifiedDate){
			super(id, subject, work, owner, lastModifiedDate);
			super.setInstanceType(Instance.TYPE_BOARD);
	}
}