package net.smartworks.server.service;

import net.smartworks.model.work.SmartWork;
import net.smartworks.model.work.WorkCategory;

public interface IWorkListService {

	SmartWork[] getMyAllWorksByCategoryId(String userId, String categoryId) throws Exception;

	SmartWork[] getMyFavoriteWorks(String userId) throws Exception;

	WorkCategory[] getMyWorkCategories(String userId) throws Exception;

	SmartWork[] getMyAllWorksByGroupId(String userId, String groupId) throws Exception;

}
