package net.smartworks.model.notice;

import java.util.Date;

import net.smartworks.model.instance.Instance;
import net.smartworks.util.LocalDate;

public class NoticeBox {
	
	private int 			noticeType;
	private NoticeMessage[]	noticeMessages;
	private LocalDate		dateOfLastNotice;
	private int				remainingLength;	
	public int getNoticeType() {
		return noticeType;
	}
	public void setNoticeType(int noticeType) {
		this.noticeType = noticeType;
	}
	public NoticeMessage[] getNoticeMessages() {
		return noticeMessages;
	}
	public void setNoticeMessages(NoticeMessage[] noticeMessages) {
		this.noticeMessages = noticeMessages;
	}
	public LocalDate getDateOfLastNotice() {
		return dateOfLastNotice;
	}
	public void setDateOfLastNotice(LocalDate dateOfLastNotice) {
		this.dateOfLastNotice = dateOfLastNotice;
	}
	public int getRemainingLength() {
		return remainingLength;
	}
	public void setRemainingLength(int remainingLength) {
		this.remainingLength = remainingLength;
	}
	
	public NoticeBox(){
		super();
	}
	public NoticeBox(int noticeType, NoticeMessage[] noticeMessages, LocalDate dateOfLastNotice, int remainingLength){
		this.noticeType = noticeType;
		this.noticeMessages = noticeMessages;
		this.dateOfLastNotice = dateOfLastNotice;
		this.remainingLength = remainingLength;
	}
}
