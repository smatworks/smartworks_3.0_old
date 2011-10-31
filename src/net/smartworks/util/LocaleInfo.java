package net.smartworks.util;

public class LocaleInfo {

	public static String LOCALE_KOREAN = "ko";
	public static String LOCALE_ENGLISH = "en";
	public static String LOCALE_DEFAULT = "ko";
	public static String LOCALE_GENERAL = "en";
	
	private static String[] supportingLocales = new String[] { "ko", "en"};
	
	public static boolean isSupportingLocale(String locale){
		for(String str : supportingLocales)
			if(str.equals(locale)) return true;
		return false;
	}
}
