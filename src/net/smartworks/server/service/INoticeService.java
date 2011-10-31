/*	
 * $Id$
 * created by    : SHIN HYUN SEONG
 * creation-date : 2011. 10. 31.
 * =========================================================
 * Copyright (c) 2011 ManinSoft, Inc. All rights reserved.
 */

package net.smartworks.server.service;

import net.smartworks.model.notice.Notice;
import net.smartworks.model.notice.NoticeBox;
import net.smartworks.util.LocalDate;

public interface INoticeService {
	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getNoticesForMe(java.lang.String)
	 */
	public Notice[] getNoticesForMe(String userId) throws Exception;

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getNoticeBoxForMe10(int, net.smartworks.util.LocalDate)
	 */
	public NoticeBox getNoticeBoxForMe10(int noticeType,
			LocalDate lastNotice) throws Exception;

	public String[] getBroadcastingMessages() throws Exception;
}
