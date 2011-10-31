package net.smartworks.server.service.impl;

import net.smartworks.model.work.SmartWork;
import net.smartworks.model.work.WorkCategory;
import net.smartworks.server.service.IWorkListService;
import net.smartworks.util.SmartTest;

public class WorkListServiceImpl implements IWorkListService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getMyFavoriteWorks(java.lang.
	 * String)
	 */
	@Override
	public SmartWork[] getMyFavoriteWorks(String userId) throws Exception {

		return new SmartWork[] { SmartTest.getSmartWork1(), SmartTest.getSmartWork2(), SmartTest.getSmartWork3() };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getMyWorkCategories(java.lang
	 * .String)
	 */
	@Override
	public WorkCategory[] getMyWorkCategories(String userId) throws Exception {

		return new WorkCategory[] { SmartTest.getWorkCategory1(), SmartTest.getWorkCategory2() };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getMyAllWorksByCategoryId(java
	 * .lang.String, java.lang.String)
	 */
	@Override
	public SmartWork[] getMyAllWorksByCategoryId(String userId, String categoryId) throws Exception {

		SmartWork[] smartWorks = new SmartWork[] { SmartTest.getSmartWork1(), SmartTest.getSmartWork2(), SmartTest.getSmartWork3(), SmartTest.getSmartWork4(),
				SmartTest.getSmartWork5(), SmartTest.getSmartWork6() };

		int count = 0;
		for (SmartWork smartWork : smartWorks) {
			if (smartWork.getMyCategory().getId().equals(categoryId)) {
				count++;
			}
		}
		SmartWork[] resultWorks = new SmartWork[count];
		count = 0;
		for (SmartWork smartWork : smartWorks) {
			if (smartWork.getMyCategory().getId().equals(categoryId)) {
				resultWorks[count++] = smartWork;
			}
		}
		return resultWorks;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getMyAllWorksByGroupId(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public SmartWork[] getMyAllWorksByGroupId(String userId, String groupId) throws Exception {

		return new SmartWork[] { SmartTest.getSmartWork7(), SmartTest.getSmartWork8(), SmartTest.getSmartWork9() };
	}

}
