package net.smartworks.model.work;

public class SocialWork extends SmartWork {
	public static int	TYPE_ATTACHMENT= 21;
	public static int   TYPE_MENO		= 22;
	public static int	TYPE_EVENT		= 23;
	public static int	TYPE_BOARD		= 24;	
	public static int	TYPE_FILE		= 25;
	public static int	TYPE_PICTURE	= 26;
	public static int	TYPE_MOVIE		= 27;
	
	public SocialWork(){
		super();
	}
	public SocialWork(String id, String name){
		super(id, name);
	}
}
