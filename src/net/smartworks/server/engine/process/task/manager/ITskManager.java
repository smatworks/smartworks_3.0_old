package net.smartworks.server.engine.process.task.manager;

import net.smartworks.server.engine.common.manager.IManager;
import net.smartworks.server.engine.process.task.exception.TskException;
import net.smartworks.server.engine.process.task.model.TskTask;
import net.smartworks.server.engine.process.task.model.TskTaskCond;
import net.smartworks.server.engine.process.task.model.TskTaskDef;
import net.smartworks.server.engine.process.task.model.TskTaskDefCond;

public interface ITskManager extends IManager {
	public TskTask getTask(String user, String id, String level) throws TskException;
	public TskTask setTask(String user, TskTask obj, String level) throws TskException;
	public void removeTask(String user, String id) throws TskException;
	public long getTaskSize(String user, TskTaskCond cond) throws TskException;
	public TskTask[] getTasks(String user, TskTaskCond cond, String level) throws TskException;
	public void startTask(String user, String id) throws TskException;
	public void executeTask(String user, TskTask obj, String action) throws TskException;
	
	public TskTaskDef getTaskDef(String user, String id, String level) throws TskException;
	public void setTaskDef(String user, TskTaskDef obj, String level) throws TskException;
	public void removeTaskDef(String user, String id) throws TskException;
	public long getTaskDefSize(String user, TskTaskDefCond cond) throws TskException;
	public TskTaskDef[] getTaskDefs(String user, TskTaskDefCond cond, String level) throws TskException;
}
