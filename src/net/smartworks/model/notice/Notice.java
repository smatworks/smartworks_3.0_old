package net.smartworks.model.notice;

public class Notice {
	
	public static int 	NUMBER_OF_NOTICES = 6;
	
	public static int	TYPE_INVALID 		= -1;
	public static int	TYPE_NOTIFICATION 	= 0;
	public static int	TYPE_MESSAGE 		= 1;
	public static int	TYPE_COMMENTS 		= 2;
	public static int	TYPE_ASSIGNED 		= 3;
	public static int	TYPE_MAILBOX 		= 4;
	public static int	TYPE_SAVEDBOX 		= 5;

	private int 	type;
	private int	   	length;

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
	public Notice(){
		super();
	}
	public Notice(int type, int length){
		this.type = type;
		this.length = length;
	}
}
