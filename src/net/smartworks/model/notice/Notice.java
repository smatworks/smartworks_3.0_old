package net.smartworks.model.notice;

public class Notice {
	
	public static int 	NUMBER_OF_NOTICES = 6;
	
	public static int	NOTICE_TYPE_INVALID 		= -1;
	public static int	NOTICE_TYPE_NOTIFICATION 	= 0;
	public static int	NOTICE_TYPE_MESSAGE 		= 1;
	public static int	NOTICE_TYPE_COMMENTS 		= 2;
	public static int	NOTICE_TYPE_ASSIGNED 		= 3;
	public static int	NOTICE_TYPE_MAILBOX 		= 4;
	public static int	NOTICE_TYPE_SAVEDBOX 		= 5;

	private int 	noticeType;
	private int	   	noticeLength;

	public int getNoticeType() {
		return noticeType;
	}
	public void setNoticeType(int noticeType) {
		this.noticeType = noticeType;
	}
	public int getNoticeLength() {
		return noticeLength;
	}
	public void setNoticeLength(int noticeLength) {
		this.noticeLength = noticeLength;
	}
	
	public Notice(){
		super();
	}
	public Notice(int noticeType, int noticeLength){
		this.noticeType = noticeType;
		this.noticeLength = noticeLength;
	}
}
