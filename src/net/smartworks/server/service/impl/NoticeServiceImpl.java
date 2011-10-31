/*	
 * $Id$
 * created by    : SHIN HYUN SEONG
 * creation-date : 2011. 10. 31.
 * =========================================================
 * Copyright (c) 2011 ManinSoft, Inc. All rights reserved.
 */

package net.smartworks.server.service.impl;

import net.smartworks.model.instance.AsyncMessageInstance;
import net.smartworks.model.instance.CommentsInstance;
import net.smartworks.model.instance.MailInstance;
import net.smartworks.model.instance.TaskInstance;
import net.smartworks.model.notice.Notice;
import net.smartworks.model.notice.NoticeBox;
import net.smartworks.model.notice.NoticeMessage;
import net.smartworks.server.service.INoticeService;
import net.smartworks.util.LocalDate;
import net.smartworks.util.SmartTest;
import net.smartworks.util.SmartUtil;

import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements INoticeService {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getNoticesForMe(java.lang.String)
	 */
	@Override
	public Notice[] getNoticesForMe(String userId) throws Exception {
		return new Notice[] { new Notice(Notice.TYPE_NOTIFICATION, 1), new Notice(Notice.TYPE_MESSAGE, 0), new Notice(Notice.TYPE_COMMENTS, 29),
				new Notice(Notice.TYPE_ASSIGNED, 0), new Notice(Notice.TYPE_MAILBOX, 420), new Notice(Notice.TYPE_SAVEDBOX, 7) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.smartworks.service.impl.ISmartWorks#getNoticeBoxForMe10(int,
	 * net.smartworks.util.LocalDate)
	 */
	@Override
	public NoticeBox getNoticeBoxForMe10(int noticeType, LocalDate lastNotice) throws Exception {
		if (noticeType == Notice.TYPE_NOTIFICATION) {
			NoticeBox noticeBox = new NoticeBox();
			noticeBox.setNoticeMessages(getNotificationMessages());
			noticeBox.setNoticeType(Notice.TYPE_NOTIFICATION);
			noticeBox.setDateOfLastNotice(new LocalDate());
			noticeBox.setRemainingLength(48);
			return noticeBox;
		}

		if (noticeType == Notice.TYPE_MESSAGE) {
			NoticeBox noticeBox = new NoticeBox();
			noticeBox.setNoticeMessages(getMessageMessages());
			noticeBox.setNoticeType(Notice.TYPE_MESSAGE);
			noticeBox.setDateOfLastNotice(new LocalDate());
			noticeBox.setRemainingLength(48);
			return noticeBox;
		}

		if (noticeType == Notice.TYPE_COMMENTS) {
			NoticeBox noticeBox = new NoticeBox();
			noticeBox.setNoticeMessages(getCommentsMessages());
			noticeBox.setNoticeType(Notice.TYPE_COMMENTS);
			noticeBox.setDateOfLastNotice(new LocalDate());
			noticeBox.setRemainingLength(48);
			return noticeBox;
		}

		if (noticeType == Notice.TYPE_ASSIGNED) {
			NoticeBox noticeBox = new NoticeBox();
			noticeBox.setNoticeMessages(getAssignedMessages());
			noticeBox.setNoticeType(Notice.TYPE_ASSIGNED);
			noticeBox.setDateOfLastNotice(new LocalDate());
			noticeBox.setRemainingLength(48);
			return noticeBox;
		}

		if (noticeType == Notice.TYPE_MAILBOX) {
			NoticeBox noticeBox = new NoticeBox();
			noticeBox.setNoticeMessages(getMailboxMessages());
			noticeBox.setNoticeType(Notice.TYPE_MAILBOX);
			noticeBox.setDateOfLastNotice(new LocalDate());
			noticeBox.setRemainingLength(48);
			return noticeBox;
		}

		if (noticeType == Notice.TYPE_SAVEDBOX) {
			NoticeBox noticeBox = new NoticeBox();
			noticeBox.setNoticeMessages(getSavedboxMessages());
			noticeBox.setNoticeType(Notice.TYPE_SAVEDBOX);
			noticeBox.setDateOfLastNotice(new LocalDate());
			noticeBox.setRemainingLength(48);
			return noticeBox;
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.smartworks.service.impl.ISmartWorks#getBroadcastingMessages()
	 */
	@Override
	public String[] getBroadcastingMessages() throws Exception {
		return new String[] { "오늘 시스템 작업예정으로 오후 5시부터 한시간 동안 시스템을 사용할 수 없으니, 업무진행에 착오없으시길 바랍니다. -- 기술연구소 ---",
				"금일 전체회식에 전원참석하여 좋은 친목의 시간이 되기를 바랍니다. --- 경영 기획팀 ----" };
	}

	// For Test...
	private NoticeMessage[] getNotificationMessages() throws Exception {

		NoticeMessage notice1, notice2, notice3, notice4, notice5;
		notice1 = new NoticeMessage("notice1", NoticeMessage.TYPE_SYSTEM_NOTICE, SmartTest.getUser1(), new LocalDate());
		notice1.setMessage("금주 주말(토요일, 일요일)에 시스템 정기점검을 실시하는 관계를 시스템을 1시간 가량 사용할 수 없으니 이점 양해 바랍니다.");
		notice2 = new NoticeMessage("notic2", NoticeMessage.TYPE_EVENT_ALARM, SmartTest.getUser2(), new LocalDate());
		notice2.setEvent(SmartTest.getEventInstance1());
		notice3 = new NoticeMessage("notice3", NoticeMessage.TYPE_TASK_DELAYED, SmartUtil.getCurrentUser(), new LocalDate());
		notice3.setInstance(SmartTest.getTaskInstance1());
		notice4 = new NoticeMessage("notice4", NoticeMessage.TYPE_JOIN_REQUEST, SmartTest.getUser1(), new LocalDate());
		notice4.setGroup(SmartTest.getGroup1());
		notice4.setMessage("님이 커뮤너티에 가입을 신청하셨습니다.");
		notice5 = new NoticeMessage("notice5", NoticeMessage.TYPE_INSTANCE_CREATED, SmartTest.getUser1(), new LocalDate());
		notice5.setInstance(SmartTest.getWorkInstance1());
		notice5.setMessage("새로운 업무를 등록하였습니다..");
		return new NoticeMessage[] { notice1, notice2, notice3, notice4, notice5 };

	}

	private NoticeMessage[] getMessageMessages() throws Exception {

		NoticeMessage notice1, notice2, notice3, notice4, notice5;
		AsyncMessageInstance messageInstance1 = new AsyncMessageInstance("message1", SmartTest.getUser1(), new LocalDate(), "안녕하세요?  잘지내시죠??? ㅎㅎㅎ");
		notice1 = new NoticeMessage("notice21", 0, SmartTest.getUser1(), new LocalDate());
		notice1.setInstance(messageInstance1);

		AsyncMessageInstance messageInstance2 = new AsyncMessageInstance("message2", SmartTest.getUser2(), new LocalDate(),
				"일간 한번 찾아뵙겠습니다. 그동안 몇번 연락드렸었는데, 연락이 안되던데요???");
		notice2 = new NoticeMessage("notice22", 0, SmartTest.getUser1(), new LocalDate());
		notice2.setInstance(messageInstance2);

		AsyncMessageInstance messageInstance3 = new AsyncMessageInstance("message3", SmartTest.getUser3(), new LocalDate(), "누구시더라????ㅠ");
		notice3 = new NoticeMessage("notice23", 0, SmartTest.getUser1(), new LocalDate());
		notice3.setInstance(messageInstance3);

		return new NoticeMessage[] { notice1, notice2, notice3 };

	}

	private NoticeMessage[] getCommentsMessages() throws Exception {

		NoticeMessage notice1, notice2, notice3, notice4, notice5;
		CommentsInstance commentsInstance1 = new CommentsInstance("comments1", CommentsInstance.COMMENTS_TYPE_ON_WORK_DESC, "조금더 보강해야 될것 같은데요????",
				SmartTest.getUser3(), new LocalDate());
		commentsInstance1.setWork(SmartTest.getSmartWork1());
		notice1 = new NoticeMessage("notice21", 0, SmartTest.getUser3(), new LocalDate());
		notice1.setInstance(commentsInstance1);

		CommentsInstance commentsInstance2 = new CommentsInstance("comments2", CommentsInstance.COMMENTS_TYPE_ON_WORK_MANUAL, "잘모르겠습니다. ㅠㅠ",
				SmartTest.getUser1(), new LocalDate());
		commentsInstance2.setWork(SmartTest.getSmartWork3());
		notice2 = new NoticeMessage("notice22", 0, SmartTest.getUser1(), new LocalDate());
		notice2.setInstance(commentsInstance2);

		CommentsInstance commentsInstance3 = new CommentsInstance("comments3", CommentsInstance.COMMENTS_TYPE_ON_WORK_INSTANCE, "휴가잘다녀오세요!!!",
				SmartTest.getUser2(), new LocalDate());
		commentsInstance3.setWorkInstance(SmartTest.getWorkInstance1());
		notice3 = new NoticeMessage("notice23", 0, SmartTest.getUser2(), new LocalDate());
		notice3.setInstance(commentsInstance3);

		CommentsInstance commentsInstance4 = new CommentsInstance("comments4", CommentsInstance.COMMENTS_TYPE_ON_TASK_INSTANCE, "재 기안해 주시기 바랍니다...",
				SmartTest.getUser3(), new LocalDate());
		commentsInstance4.setTaskInstance(SmartTest.getTaskInstance1());
		notice4 = new NoticeMessage("notice24", 0, SmartTest.getUser3(), new LocalDate());
		notice4.setInstance(commentsInstance4);

		return new NoticeMessage[] { notice1, notice2, notice3, notice4 };

	}

	private NoticeMessage[] getAssignedMessages() throws Exception {

		NoticeMessage notice1, notice2, notice3, notice4, notice5;
		TaskInstance assignedInstance1 = new TaskInstance("assignedtask1", "대표이사 승인", TaskInstance.TASK_TYPE_PROCESSWORK_TASK_ASSIGNED, SmartTest.getUser3(),
				new LocalDate());
		assignedInstance1.setWorkInstance(SmartTest.getWorkInstance1());
		notice1 = new NoticeMessage("notice31", 0, SmartTest.getUser1(), new LocalDate());
		notice1.setInstance(assignedInstance1);

		TaskInstance assignedInstance2 = new TaskInstance("assignedtask2", "구매기안 기안제출", TaskInstance.TASK_TYPE_PROCESSWORK_TASK_FORWARDED,
				SmartTest.getUser3(), new LocalDate());
		notice2 = new NoticeMessage("notice32", 0, SmartTest.getUser2(), new LocalDate());
		assignedInstance2.setWorkInstance(SmartTest.getWorkInstance3());
		notice2.setInstance(assignedInstance2);

		TaskInstance assignedInstance3 = new TaskInstance("assignedtask3", "반도체회사 제안서 공유합니다.", TaskInstance.TASK_TYPE_INFORMATIONWORK_TASK_FORWARDED,
				SmartTest.getUser3(), new LocalDate());
		notice3 = new NoticeMessage("notice33", 0, SmartTest.getUser3(), new LocalDate());
		assignedInstance3.setWorkInstance(SmartTest.getWorkInstance2());
		notice3.setInstance(assignedInstance3);

		TaskInstance assignedInstance4 = new TaskInstance("assignedtask4", "검토자 결재", TaskInstance.TASK_TYPE_APPROVALWORK_TASK_ASSIGNED, SmartTest.getUser3(),
				new LocalDate());
		notice4 = new NoticeMessage("notice34", 0, SmartUtil.getCurrentUser(), new LocalDate());
		assignedInstance4.setWorkInstance(SmartTest.getWorkInstance4());
		notice4.setInstance(assignedInstance4);

		TaskInstance assignedInstance5 = new TaskInstance("assignedtask5", "일일보고 입니다.", TaskInstance.TASK_TYPE_INFORMATIONWORK_TASK_ASSIGNED,
				SmartTest.getUser3(), new LocalDate());
		notice5 = new NoticeMessage("notice35", 0, SmartUtil.getCurrentUser(), new LocalDate());
		assignedInstance5.setWorkInstance(SmartTest.getWorkInstance5());
		notice5.setInstance(assignedInstance5);

		return new NoticeMessage[] { notice1, notice2, notice3, notice4, notice5 };
	}

	private NoticeMessage[] getMailboxMessages() throws Exception {

		NoticeMessage notice1, notice2, notice3, notice4, notice5;
		MailInstance mailInstance1 = new MailInstance("mailinst1", "하이닉스프로젝트관련 회의록입니다.", SmartTest.getUser3(), new LocalDate());
		mailInstance1.setMailCategory(SmartTest.getWorkCategory1());
		mailInstance1.setMailGroup(SmartTest.getSmartWork6());
		notice1 = new NoticeMessage("notice41", 0, SmartTest.getUser3(), new LocalDate());
		notice1.setInstance(mailInstance1);

		MailInstance mailInstance2 = new MailInstance("mailinst2", "내용검토하시고 의견주시기 바랍니다.", SmartTest.getUser1(), new LocalDate());
		mailInstance2.setMailCategory(SmartTest.getWorkCategory1());
		notice2 = new NoticeMessage("notice42", 0, SmartTest.getUser3(), new LocalDate());
		notice2.setInstance(mailInstance2);

		MailInstance mailInstance3 = new MailInstance("mailinst3", "연락처입니다.", SmartTest.getUser3(), new LocalDate());
		mailInstance3.setMailCategory(SmartTest.getWorkCategory1());
		mailInstance3.setMailGroup(SmartTest.getSmartWork6());
		notice3 = new NoticeMessage("notice43", 0, SmartTest.getUser3(), new LocalDate());
		notice3.setInstance(mailInstance3);

		return new NoticeMessage[] { notice1, notice2, notice3 };
	}

	private NoticeMessage[] getSavedboxMessages() throws Exception {

		NoticeMessage[] mailboxNotices = getMailboxMessages();

		return mailboxNotices;
	}

}
