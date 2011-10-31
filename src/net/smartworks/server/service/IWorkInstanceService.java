package net.smartworks.server.service;

import net.smartworks.model.instance.BoardInstance;
import net.smartworks.model.instance.WorkInstance;
import net.smartworks.util.LocalDate;

public interface IWorkInstanceService {

	BoardInstance[] getBoardInstances(LocalDate fromDate, LocalDate toDate) throws Exception;

	WorkInstance[] getMyRecentInstances(String userId) throws Exception;

	BoardInstance[] getBoardInstances(LocalDate fromDate, int days) throws Exception;

}
