/*	
 * $Id: SmartConfUtil.java,v 1.1 2011/10/20 04:06:06 ysjung Exp $
 * created by    : SHIN HYUN SEONG
 * creation-date : 2011. 10. 15.
 * =========================================================
 * Copyright (c) 2011 ManinSoft, Inc. All rights reserved.
 */

package net.smartworks.util;

public class SmartConfUtil {

	private static SmartConfUtil util;

	private String fileDirectory;

	public static SmartConfUtil getInstance() {
		if(util == null)
			util = new SmartConfUtil();
		return util;
	}

	public static synchronized SmartConfUtil createInstance() {
		if(util == null)
			util = new SmartConfUtil();
		return util;
	}

	/**
	 * @return the fileDirectory
	 */
	public String getFileDirectory() {
		return fileDirectory;
	}

	/**
	 * @param fileDirectory the fileDirectory to set
	 */
	public void setFileDirectory(String fileDirectory) {
		this.fileDirectory = fileDirectory;
	}

}