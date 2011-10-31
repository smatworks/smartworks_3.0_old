package net.smartworks.server.engine.process.task.manager.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.smartworks.server.engine.common.manager.AbstractManager;
import net.smartworks.server.engine.common.model.ClassObject;
import net.smartworks.server.engine.common.model.Filter;
import net.smartworks.server.engine.common.model.MisObject;
import net.smartworks.server.engine.common.model.Property;
import net.smartworks.server.engine.process.task.exception.TskException;
import net.smartworks.server.engine.process.task.manager.ITskManager;
import net.smartworks.server.engine.process.task.model.TskTask;
import net.smartworks.server.engine.process.task.model.TskTaskCond;
import net.smartworks.server.engine.process.task.model.TskTaskDef;
import net.smartworks.server.engine.process.task.model.TskTaskDefCond;
import net.smartworks.server.util.CommonUtil;
import net.smartworks.server.util.DateUtil;

import org.hibernate.Query;

public class TskManagerImpl extends AbstractManager implements ITskManager{
	public TskManagerImpl() {
		super();
		if (logger.isInfoEnabled())
			logger.info(this.getClass().getName() + " created");
	}

	public TskTask getTask(String user, String id, String level) throws TskException {
		try {
			if (level == null)
				level = LEVEL_ALL;
			if (level.equals(LEVEL_ALL)) {
				TskTask obj = (TskTask)this.get(TskTask.class, id);
				return obj;
			} else {
				TskTaskCond cond = new TskTaskCond();
				cond.setObjId(id);
				TskTask[] objs = this.getTasks(user, cond, level);
				if (objs == null || objs.length == 0)
					return null;
				return objs[0];
			}
		} catch (TskException e) {
			throw e;
		} catch (Exception e) {
			throw new TskException(e);
		}
	}
	public TskTask setTask(String user, TskTask obj, String level) throws TskException {
		if (level == null)
			level = LEVEL_ALL;
		try {
			if (level.equals(LEVEL_ALL)) {
				fill(user, obj);
				set(obj);
			} else {
				StringBuffer buf = new StringBuffer();
				buf.append("update TskTask set");
				buf.append(" name=:name, creationUser=:creationUser, creationDate=:creationDate");
				buf.append(", modificationUser=:modificationUser, modificationDate=:modificationDate");
				buf.append(", description=:description");
				buf.append(", status=:status, correlation=:correlation, type=:type");
				buf.append(", processInstId=:processInstId, title=:title, priority=:priority");
				buf.append(", document=:document, assigner=:assigner, assignee=:assignee, performer=:performer");
				buf.append(", startDate=:startDate, assignmentDate=:assignmentDate, executionDate=:executionDate, dueDate=:dueDate");
				buf.append(", expectStartDate=:expectStartDate, expectEndDate=:expectEndDate");
				buf.append(", realStartDate=:realStartDate, realEndDate=:realEndDate");
				buf.append(", def=:def, form=:form");
				buf.append(", multiInstId=:multiInstId, multiInstOrdering=:multiInstOrdering");
				buf.append(", multiInstFlowCondition=:multiInstFlowCondition, loopCounterInteger=:loopCounterInteger");
				buf.append(", stepInteger=:stepInteger, instVariable=:instVariable where objId=:objId");
				Query query = this.getSession().createQuery(buf.toString());
				query.setString(MisObject.A_NAME, obj.getName());
				query.setString(MisObject.A_CREATIONUSER, obj.getCreationUser());
				query.setTimestamp(MisObject.A_CREATIONDATE, obj.getCreationDate());
				query.setString(MisObject.A_MODIFICATIONUSER, obj.getModificationUser());
				query.setTimestamp(MisObject.A_MODIFICATIONDATE, obj.getModificationDate());
				query.setString(MisObject.A_DESCRIPTION, obj.getDescription());
				query.setString(MisObject.A_STATUS, obj.getStatus());
				query.setString(TskTask.A_CORRELATION, obj.getCorrelation());
				query.setString(TskTask.A_TYPE, obj.getType());
				query.setString(TskTask.A_PROCESSINSTID, obj.getProcessInstId());
				query.setString(TskTask.A_TITLE, obj.getTitle());
				query.setString(TskTask.A_PRIORITY, obj.getPriority());
				query.setString(TskTask.A_DOCUMENT, obj.getDocument());
				query.setString(TskTask.A_ASSIGNER, obj.getAssigner());
				query.setString(TskTask.A_ASSIGNEE, obj.getAssignee());
				query.setString("performer", obj.getPerformer());
				query.setTimestamp(TskTask.A_STARTDATE, obj.getStartDate());
				query.setTimestamp(TskTask.A_ASSIGNMENTDATE, obj.getAssignmentDate());
				query.setTimestamp(TskTask.A_EXECUTIONDATE, obj.getExecutionDate());
				query.setTimestamp(TskTask.A_DUEDATE, obj.getDueDate());
				query.setTimestamp(TskTask.A_EXPECTSTARTDATE, obj.getExpectStartDate());
				query.setTimestamp(TskTask.A_EXPECTENDDATE, obj.getExpectEndDate());
				query.setTimestamp(TskTask.A_REALSTARTDATE, obj.getRealStartDate());
				query.setTimestamp(TskTask.A_REALENDDATE, obj.getRealEndDate());
				query.setString(TskTask.A_DEF, obj.getDef());
				query.setString(TskTask.A_FORM, obj.getForm());
				query.setString(TskTask.A_MULTIINSTID, obj.getMultiInstId());
				query.setString(TskTask.A_MULTIINSTORDERING, obj.getMultiInstOrdering());
				query.setString(TskTask.A_MULTIINSTFLOWCONDITION, obj.getMultiInstFlowCondition());
				query.setInteger("loopCounterInteger", CommonUtil.toInt(obj.getLoopCounterInteger()));
				query.setInteger("stepInteger", CommonUtil.toInt(obj.getStepInteger()));
				query.setString("instVariable", obj.getInstVariable());
				query.setString(ClassObject.A_OBJID, obj.getObjId());
			}
			return obj;
		} catch (TskException e) {
			throw e;
		} catch (Exception e) {
			throw new TskException(e);
		}
	}
	public void removeTask(String user, String id) throws TskException {
		try {
			TskTask obj = this.getTask(user, id, LEVEL_ALL);
			this.getHibernateTemplate().delete(obj);
			this.getHibernateTemplate().flush();
		} catch (TskException e) {
			throw e;
		} catch (Exception e) {
			throw new TskException(e);
		}
	}
	private Query appendQuery(StringBuffer buf, TskTaskCond cond) throws Exception{
		String objId = null;
		String creationUser = null;
		Date creationDateFrom = null;
		Date creationDateTo = null;
		String modificationUser = null;
		Date modificationDateFrom = null;
		Date modificationDateTo = null;
		Date assignmentDateFrom = null;
		Date assignmentDateTo = null;
		Date executionDateFrom = null;
		Date executionDateTo = null;
		Date dueDateFrom = null;
		Date dueDateTo = null;
		String assignee = null;
		String assigner = null;
		String status = null;
		String correlation = null;
		String type = null;
		String processInstId = null;
		String name = null;
		String title = null;
		String titleLike = null;
		String description = null;
		String descriptionLike = null;
		String priority = null;
		String def = null;
		String form = null;
		String multiInstId = null;
		String multiInstOrdering = null;
		String multiInstFlowCondition = null;
		int loopCounter = -1;
		int step = -1;
		Property[] extProps = null;
		String[] statusIns = null;
		String[] statusNotIns = null;
		String[] typeIns = null;
		String[] typeNotIns = null;
		String[] priorityIns = null;
		String[] priorityNotIns = null;
		String[] formIns = null;
		String searchKey = null;
		Filter[] filters = null;
		String logicalOperator = null;
		if (cond != null) {
			objId = cond.getObjId();
			creationUser = cond.getCreationUser();
			creationDateFrom = cond.getCreationDateFrom();
			creationDateTo = cond.getCreationDateTo();
			modificationUser = cond.getModificationUser();
			modificationDateFrom = cond.getModificationDateFrom();
			modificationDateTo = cond.getModificationDateTo();
			assignmentDateFrom = cond.getAssignmentDateFrom();
			assignmentDateTo = cond.getAssignmentDateTo();
			executionDateFrom = cond.getExecutionDateFrom();
			executionDateTo = cond.getExecutionDateTo();
			assignee = cond.getAssignee();
			assigner = cond.getAssigner();
			correlation = cond.getCorrelation();
			status = cond.getStatus();
			type = cond.getType();
			processInstId = cond.getProcessInstId();
			name = cond.getName();
			title = cond.getTitle();
			titleLike = cond.getTitleLike();
			description = cond.getDescription();
			descriptionLike = cond.getDescriptionLike();
			priority = cond.getPriority();
			def = cond.getDef();
			form = cond.getForm();
			extProps = cond.getExtendedProperties();
			statusIns = cond.getStatusIns();
			statusNotIns = cond.getStatusNotIns();
			typeIns = cond.getTypeIns();
			typeNotIns = cond.getTypeNotIns();
			priorityIns = cond.getPriorityIns();
			priorityNotIns = cond.getPriorityNotIns();
			formIns = cond.getFormIns();
			searchKey = cond.getSearchKey();
			multiInstId = cond.getMultiInstId();
			multiInstOrdering = cond.getMultiInstOrdering();
			multiInstFlowCondition = cond.getMultiInstFlowCondition();
			loopCounter = cond.getLoopCounter();
			step = cond.getStep();
			filters = cond.getFilter();
			logicalOperator = cond.getOperator();
		}

		buf.append(" from TskTask obj");
		if (extProps != null && extProps.length != 0) {
			for (int i=0; i<extProps.length; i++) {
				buf.append(" left join obj.extendedProperties as extProp").append(i);
			}
		}
		buf.append(" where obj.objId is not null");
		Map filterMap = new HashMap();
		if (cond != null) {
			if (objId != null)
				buf.append(" and obj.objId = :objId");
			if (creationUser != null)
				buf.append(" and obj.creationUser = :creationUser");
			if (creationDateFrom != null)
				buf.append(" and obj.creationDate >= :creationDateFrom");
			if (creationDateTo != null)
				buf.append(" and obj.creationDate <= :creationDateTo");
			if (modificationUser != null)
				buf.append(" and obj.modificationUser = :modificationUser");
			if (modificationDateFrom != null)
				buf.append(" and obj.modificationDate >= :modificationDateFrom");
			if (modificationDateTo != null)
				buf.append(" and obj.modificationDate <= :modificationDateTo");
			if (assignmentDateFrom != null)
				buf.append(" and obj.assignmentDate >= :assignmentDateFrom");
			if (assignmentDateTo != null)
				buf.append(" and obj.assignmentDate <= :assignmentDateTo");
			if (executionDateFrom != null)
				buf.append(" and obj.executionDate >= :executionDateFrom");
			if (executionDateTo != null)
				buf.append(" and obj.creationDate <= :executionDateTo");
			if (dueDateFrom != null)
				buf.append(" and obj.dueDate >= :dueDateFrom");
			if (dueDateTo != null)
				buf.append(" and obj.dueDate <= :dueDateTo");
			if (assignee != null)
				buf.append(" and obj.assignee = :assignee");
			if (assigner != null)
				buf.append(" and obj.assigner = :assigner");
			if (status != null)
				buf.append(" and obj.status = :status");
			if (correlation != null)
				buf.append(" and obj.correlation = :correlation");
			if (type != null)
				buf.append(" and obj.type = :type");
			if (processInstId != null)
				buf.append(" and obj.processInstId = :processInstId");
			if (name != null)
				buf.append(" and obj.name = :name");
			if (title != null)
				buf.append(" and obj.title = :title");
			if (titleLike != null)
				buf.append(" and obj.title like :titleLike");
			if (description != null)
				buf.append(" and obj.description = :description");
			if (descriptionLike != null)
				buf.append(" and obj.description like :descriptionLike");
			if (priority != null)
				buf.append(" and obj.priority = :priority");
			if (def != null)
				buf.append(" and obj.def = :def");
			if (form != null)
				buf.append(" and obj.form = :form");
			if (multiInstId != null)
				buf.append(" and obj.multiInstId = :multiInstId");
			if (multiInstOrdering != null)
				buf.append(" and obj.multiInstOrdering = :multiInstOrdering");
			if (multiInstFlowCondition != null)
				buf.append(" and obj.multiInstFlowCondition = :multiInstFlowCondition");
			if (loopCounter > 0)
				buf.append(" and obj.loopCounter = :loopCounter");
			if (step > 0)
				buf.append(" and obj.step = :step");
			if (extProps != null && extProps.length != 0) {
				for (int i=0; i<extProps.length; i++) {
					Property extProp = extProps[i];
					String extName = extProp.getName();
					String extValue = extProp.getValue();
					if (extName != null)
						buf.append(" and extProp").append(i).append(".name = :extName").append(i);
					if (extValue != null)
						buf.append(" and extProp").append(i).append(".value = :extValue").append(i);
				}
			}
			if (statusIns != null && statusIns.length != 0) {
				buf.append(" and obj.status in (");
				for (int i=0; i<statusIns.length; i++) {
					if (i != 0)
						buf.append(", ");
					buf.append(":statusIn").append(i);
				}
				buf.append(")");
			}
			if (statusNotIns != null && statusNotIns.length != 0) {
				buf.append(" and obj.status not in (");
				for (int i=0; i<statusNotIns.length; i++) {
					if (i != 0)
						buf.append(", ");
					buf.append(":statusNotIn").append(i);
				}
				buf.append(")");
			}
			if (typeIns != null && typeIns.length != 0) {
				buf.append(" and obj.type in (");
				for (int i=0; i<typeIns.length; i++) {
					if (i != 0)
						buf.append(", ");
					buf.append(":typeIn").append(i);
				}
				buf.append(")");
			}
			if (typeNotIns != null && typeNotIns.length != 0) {
				buf.append(" and obj.type not in (");
				for (int i=0; i<typeNotIns.length; i++) {
					if (i != 0)
						buf.append(", ");
					buf.append(":typeNotIn").append(i);
				}
				buf.append(")");
			}
			if (priorityIns != null && priorityIns.length != 0) {
				buf.append(" and obj.priority in (");
				for (int i=0; i<priorityIns.length; i++) {
					if (i != 0)
						buf.append(", ");
					buf.append(":priorityIn").append(i);
				}
				buf.append(")");
			}
			if (priorityNotIns != null && priorityNotIns.length != 0) {
				buf.append(" and obj.priority not in (");
				for (int i=0; i<priorityNotIns.length; i++) {
					if (i != 0)
						buf.append(", ");
					buf.append(":priorityNotIn").append(i);
				}
				buf.append(")");
			}
			if (formIns != null && formIns.length != 0) {
				buf.append(" and obj.form in (");
				for (int i=0; i<formIns.length; i++) {
					if (i != 0)
						buf.append(", ");
					buf.append(":formIn").append(i);
				}
				buf.append(")");
			}
			if (searchKey != null)
				buf.append(" and (obj.name like :searchKey or obj.title like :searchKey or obj.description like :searchKey)");
			if (filters != null) {
				if (!CommonUtil.isEmpty(filters)) {
					if (CommonUtil.isEmpty(logicalOperator))
						logicalOperator = "and";
					String operator;
					String left;
					String right;
					String rightType;
					int i = 0;
					
					for (int j = 0; j < filters.length; j++) {
						Filter f = filters[j];
						operator = f.getOperator();
						left = f.getLeftOperandValue();
						right = f.getRightOperandValue();
						rightType = f.getRightOperandType();
						if (left == null)
							throw new Exception("left operand of filter condition is null.");
						if (operator == null) {
							operator = "=";
						} else {
							operator = operator.trim();
						}
						//left = CommonUtil.toDefault(fieldColumnMap.get(left), left);
						buf.append(CommonUtil.SPACE).append(logicalOperator);
						
						buf.append(CommonUtil.SPACE).append(left);
						if (right == null) {
							if (operator.equals("!=") || 
									(operator.indexOf("=") == -1 && !operator.equalsIgnoreCase("is"))) {
								buf.append(" is not null");
							} else {
								buf.append(" is null");
							}
						} else {
							if (rightType == null || !rightType.equalsIgnoreCase(Filter.OPERANDTYPE_FIELD)) {
								right = "a" + i++;
								filterMap.put(right, f);
							}
							buf.append(CommonUtil.SPACE).append(operator);
							buf.append(CommonUtil.SPACE).append(CommonUtil.COLON).append(right);
						}
					}
				}
			}
		}
		this.appendOrderQuery(buf, "obj", cond);
		
		Query query = this.createQuery(buf.toString(), cond);
		if (cond != null) {
			if (objId != null)
				query.setString("objId", objId);
			if (creationUser != null)
				query.setString("creationUser", creationUser);
			if (creationDateFrom != null)
				query.setTimestamp("creationDateFrom", creationDateFrom);
			if (creationDateTo != null)
				query.setTimestamp("creationDateTo", creationDateTo);
			if (modificationUser != null)
				query.setString("modificationUser", modificationUser);
			if (modificationDateFrom != null)
				query.setTimestamp("modificationDateFrom", modificationDateFrom);
			if (modificationDateTo != null)
				query.setTimestamp("modificationDateTo", modificationDateTo);
			if (assignmentDateFrom != null)
				query.setTimestamp("assignmentDateFrom", assignmentDateFrom);
			if (assignmentDateTo != null)
				query.setTimestamp("assignmentDateTo", assignmentDateTo);
			if (executionDateFrom != null)
				query.setTimestamp("executionDateFrom", executionDateFrom);
			if (executionDateTo != null)
				query.setTimestamp("executionDateTo", executionDateTo);
			if (dueDateFrom != null)
				query.setTimestamp("dueDateFrom", dueDateFrom);
			if (dueDateTo != null)
				query.setTimestamp("dueDateTo", dueDateTo);
			if (assignee != null)
				query.setString("assignee", assignee);
			if (assigner != null)
				query.setString("assigner", assigner);
			if (status != null)
				query.setString("status", status);
			if (correlation != null)
				query.setString("correlation", correlation);
			if (type != null)
				query.setString("type", type);
			if (processInstId != null)
				query.setString("processInstId", processInstId);
			if (name != null)
				query.setString("name", name);
			if (title != null)
				query.setString("title", title);
			if (titleLike != null)
				query.setString("titleLike", CommonUtil.toLikeString(titleLike));
			if (description != null)
				query.setString("description", description);
			if (descriptionLike != null)
				query.setString("descriptionLike", CommonUtil.toLikeString(descriptionLike));
			if (priority != null)
				query.setString("priority", priority);
			if (def != null)
				query.setString("def", def);
			if (form != null)
				query.setString("form", form);
			if (multiInstId != null)
				query.setString("multiInstId", multiInstId);
			if (multiInstOrdering != null)
				query.setString("multiInstOrdering", multiInstOrdering);
			if (multiInstFlowCondition != null)
				query.setString("multiInstFlowCondition", multiInstFlowCondition);
			if (loopCounter > 0)
				query.setInteger("loopCounter", loopCounter);
			if (step > 0)
				query.setInteger("step", step);
			if (extProps != null && extProps.length != 0) {
				for (int i=0; i<extProps.length; i++) {
					Property extProp = extProps[i];
					String extName = extProp.getName();
					String extValue = extProp.getValue();
					if (extName != null)
						query.setString("extName"+i, extName);
					if (extValue != null)
						query.setString("extValue"+i, extValue);
				}
			}
			if (statusIns != null && statusIns.length != 0) {
				for (int i=0; i<statusIns.length; i++) {
					query.setString("statusIn"+i, statusIns[i]);
				}
			}
			if (statusNotIns != null && statusNotIns.length != 0) {
				for (int i=0; i<statusNotIns.length; i++) {
					query.setString("statusNotIn"+i, statusNotIns[i]);
				}
			}
			if (typeIns != null && typeIns.length != 0) {
				for (int i=0; i<typeIns.length; i++) {
					query.setString("typeIn"+i, typeIns[i]);
				}
			}
			if (typeNotIns != null && typeNotIns.length != 0) {
				for (int i=0; i<typeNotIns.length; i++) {
					query.setString("typeNotIn"+i, typeNotIns[i]);
				}
			}
			if (priorityIns != null && priorityIns.length != 0) {
				for (int i=0; i<priorityIns.length; i++) {
					query.setString("priorityIn"+i, priorityIns[i]);
				}
			}
			if (priorityNotIns != null && priorityNotIns.length != 0) {
				for (int i=0; i<priorityNotIns.length; i++) {
					query.setString("priorityNotIn"+i, priorityNotIns[i]);
				}
			}
			if (formIns != null && formIns.length != 0) {
				for (int i=0; i<formIns.length; i++) {
					query.setString("formIn"+i, formIns[i]);
				}
			}
			if (searchKey != null)
				query.setString("searchKey", CommonUtil.toLikeString(searchKey));
			if (filters != null) {
				if (!CommonUtil.isEmpty(filterMap)) {
					Filter f;
					String operType;
					String operValue;
					String operator;
					
					Iterator keyItr = filterMap.keySet().iterator();
					String param = null;
					while (keyItr.hasNext()) {
						param = (String)keyItr.next();
						f = (Filter)filterMap.get(param);
						operType = f.getRightOperandType();
						operator = f.getOperator();
						if (operator.equalsIgnoreCase("like")) {
							operValue = CommonUtil.toLikeString(f.getRightOperandValue());
						} else {
							operValue = f.getRightOperandValue();
						}	
						if (operType == null || operType.equalsIgnoreCase(Filter.OPERANDTYPE_STRING)) {
							query.setString(param, operValue);
						} else if (operType.equalsIgnoreCase(Filter.OPERANDTYPE_INT)) {
							query.setInteger(param, CommonUtil.toInt(operValue));
						} else if (operType.equalsIgnoreCase(Filter.OPERANDTYPE_FLOAT)) {
							query.setFloat(param, CommonUtil.toFloat(operValue));
						} else if (operType.equalsIgnoreCase(Filter.OPERANDTYPE_DATE)) {
							query.setTimestamp(param, DateUtil.toDate(operValue));
						} else if (operType.equalsIgnoreCase("number")) {
							query.setDouble(param, Double.parseDouble(operValue));
						} else if (operType.equalsIgnoreCase("boolean")) {
							query.setBoolean(param, CommonUtil.toBoolean(operValue));
						} else {
							query.setParameter(param, operValue);
						}
					}
				}
			}
		}
		return query;
	}
	public long getTaskSize(String user, TskTaskCond cond) throws TskException {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("select");
			buf.append(" count(obj)");
			Query query = this.appendQuery(buf, cond);
			List list = query.list();
			long count =((Long)list.get(0)).longValue();
			return count;
		} catch (TskException e) {
			throw e;
		} catch (Exception e) {
			throw new TskException(e);
		}
	}
	public TskTask[] getTasks(String user, TskTaskCond cond, String level) throws TskException {
		try {
			if (level == null)
				level = LEVEL_LITE;
			StringBuffer buf = new StringBuffer();
			buf.append("select");
			if (level.equals(LEVEL_ALL)) {
				buf.append(" obj");
			} else {
				buf.append(" obj.objId, obj.name, obj.creationUser, obj.creationDate, obj.modificationUser");
				buf.append(", obj.modificationDate, obj.status, obj.correlation, obj.type, obj.processInstId, obj.title");
				buf.append(", obj.description, obj.priority, obj.document, obj.assigner, obj.assignee, obj.performer");
				buf.append(", obj.startDate, obj.assignmentDate, obj.executionDate, obj.dueDate, obj.def, obj.form");
				buf.append(", obj.expectStartDate, obj.expectEndDate, obj.realStartDate, obj.realEndDate");
				buf.append(", obj.multiInstId, obj.multiInstOrdering, obj.multiInstFlowCondition, obj.loopCounterInteger, obj.stepInteger");
				buf.append(", obj.instVariable");
			}
			Query query = this.appendQuery(buf, cond);
			List list = query.list();
			if (list == null || list.isEmpty())
				return null;
			if (!level.equals(LEVEL_ALL)) {
				List objList = new ArrayList();
				for (Iterator itr = list.iterator(); itr.hasNext();) {
					Object[] fields = (Object[]) itr.next();
					TskTask obj = new TskTask();
					int j = 0;
					obj.setObjId((String)fields[j++]);
					obj.setName((String)fields[j++]);
					obj.setCreationUser((String)fields[j++]);
					obj.setCreationDate(((Timestamp)fields[j++]));
					obj.setModificationUser(((String)fields[j++]));
					obj.setModificationDate(((Timestamp)fields[j++]));
					obj.setStatus(((String)fields[j++]));
					obj.setCorrelation(((String)fields[j++]));
					obj.setType(((String)fields[j++]));
					obj.setProcessInstId(((String)fields[j++]));
					obj.setTitle(((String)fields[j++]));
					obj.setDescription(((String)fields[j++]));
					obj.setPriority(((String)fields[j++]));
					obj.setDocument((String)fields[j++]);
					obj.setAssigner((String)fields[j++]);
					obj.setAssignee(((String)fields[j++]));
					obj.setPerformer(((String)fields[j++]));
					obj.setStartDate(((Timestamp)fields[j++]));
					obj.setAssignmentDate(((Timestamp)fields[j++]));
					obj.setExecutionDate(((Timestamp)fields[j++]));
					obj.setDueDate(((Timestamp)fields[j++]));
					obj.setDef(((String)fields[j++]));
					obj.setForm(((String)fields[j++]));
					obj.setExpectStartDate(((Timestamp)fields[j++]));
					obj.setExpectEndDate(((Timestamp)fields[j++]));
					obj.setRealStartDate(((Timestamp)fields[j++]));
					obj.setRealEndDate(((Timestamp)fields[j++]));
					obj.setMultiInstId(((String)fields[j++]));
					obj.setMultiInstOrdering(((String)fields[j++]));
					obj.setMultiInstFlowCondition(((String)fields[j++]));
					obj.setLoopCounterInteger(((Integer)fields[j++]));
					obj.setStepInteger(((Integer)fields[j++]));
					obj.setInstVariable(((String)fields[j++]));
					objList.add(obj);
				}
				list = objList;
			}
			TskTask[] objs = new TskTask[list.size()];
			list.toArray(objs);
			return objs;
		} catch (TskException e) {
			throw e;
		} catch (Exception e) {
			throw new TskException(e);
		}
	}
	public void startTask(String user, String id) throws TskException {
		TskTask obj = this.getTask(user, id, LEVEL_LITE);
		if (obj.getStartDate() != null)
			return;
		obj.setStartDate(new Date());
		setTask(user, obj, LEVEL_LITE);
	}
	public void executeTask(String user, TskTask obj, String action) throws TskException {
		Date date = new Date();
		if (obj.getStartDate() == null)
			obj.setStartDate(new Date(date.getTime() - 600000));
		obj.setPerformer(user);
		obj.setExecutionDate(date);
		this.setTask(user, obj, LEVEL_ALL);
	}

	public TskTaskDef getTaskDef(String user, String id, String level) throws TskException {
		try {
			if (CommonUtil.isEmpty(id))
				return null;
			if (level == null)
				level = LEVEL_ALL;
			if (level.equals(LEVEL_ALL)) {
				TskTaskDef obj = (TskTaskDef)this.get(TskTaskDef.class, id);
				return obj;
			} else {
				TskTaskDefCond cond = new TskTaskDefCond();
				cond.setObjId(id);
				TskTaskDef[] objs = this.getTaskDefs(user, cond, level);
				if (objs == null || objs.length == 0)
					return null;
				return objs[0];
			}
		} catch (TskException e) {
			throw e;
		} catch (Exception e) {
			throw new TskException(e);
		}
	}
	public void setTaskDef(String user, TskTaskDef obj, String level) throws TskException {
		if (level == null)
			level = LEVEL_ALL;
		try {
			if (level.equals(LEVEL_ALL)) {
				fill(user, obj);
				set(obj);
			} else {
				StringBuffer buf = new StringBuffer();
				buf.append("update TskTaskDef set");
				buf.append(" name=:name, creationUser=:creationUser, creationDate=:creationDate");
				buf.append(", modificationUser=:modificationUser, modificationDate=:modificationDate");
				buf.append(", description=:description");
				buf.append(", status=:status, correlation=:correlation, type=:type");
				buf.append(", processInstId=:processInstId, title=:title, priority=:priority");
				buf.append(", document=:document, assigner=:assigner, assignee=:assignee");
				buf.append(", assignmentDate=:assignmentDate, dueDate=:dueDate");
				buf.append(", form=:form");
				buf.append(", multiInstOrdering=:multiInstOrdering, multiInstFlowCondition=:multiInstFlowCondition");
				buf.append(", subFlowTargetId=:subFlowTargetId, subFlowTargetVersion=:subFlowTargetVersion, subFlowExecution=:subFlowExecution");
				buf.append(", serviceTargetId=:serviceTargetId");
				buf.append(" where objId=:objId");
				Query query = this.getSession().createQuery(buf.toString());
				query.setString(MisObject.A_NAME, obj.getName());
				query.setString(MisObject.A_CREATIONUSER, obj.getCreationUser());
				query.setTimestamp(MisObject.A_CREATIONDATE, obj.getCreationDate());
				query.setString(MisObject.A_MODIFICATIONUSER, obj.getModificationUser());
				query.setTimestamp(MisObject.A_MODIFICATIONDATE, obj.getModificationDate());
				query.setString(MisObject.A_DESCRIPTION, obj.getDescription());
				query.setString(MisObject.A_STATUS, obj.getStatus());
				query.setString(TskTaskDef.A_CORRELATION, obj.getCorrelation());
				query.setString(TskTaskDef.A_TYPE, obj.getType());
				query.setString(TskTaskDef.A_PROCESSINSTID, obj.getProcessInstId());
				query.setString(TskTaskDef.A_TITLE, obj.getTitle());
				query.setString(TskTaskDef.A_PRIORITY, obj.getPriority());
				query.setString(TskTaskDef.A_DOCUMENT, obj.getDocument());
				query.setString(TskTaskDef.A_ASSIGNER, obj.getAssigner());
				query.setString(TskTaskDef.A_ASSIGNEE, obj.getAssignee());
				query.setString(TskTaskDef.A_ASSIGNMENTDATE, obj.getAssignmentDate());
				query.setString(TskTaskDef.A_DUEDATE, obj.getDueDate());
				query.setString(TskTaskDef.A_FORM, obj.getForm());
				query.setString(TskTaskDef.A_MULTIINSTORDERING, obj.getMultiInstOrdering());
				query.setString(TskTaskDef.A_MULTIINSTFLOWCONDITION, obj.getMultiInstFlowCondition());
				query.setString(TskTaskDef.A_SUBFLOWTARGETID, obj.getSubFlowTargetId());
				query.setString(TskTaskDef.A_SUBFLOWTARGETVERSION, obj.getSubFlowTargetVersion());
				query.setString(TskTaskDef.A_SUBFLOWEXECUTION, obj.getSubFlowExecution());
				query.setString(TskTaskDef.A_SERVICETARGETID, obj.getServiceTargetId());
				query.setString(ClassObject.A_OBJID, obj.getObjId());
			}
		} catch (TskException e) {
			throw e;
		} catch (Exception e) {
			throw new TskException(e);
		}
	}
	public void removeTaskDef(String user, String id) throws TskException {
		try {
			TskTaskDef obj = this.getTaskDef(user, id, LEVEL_ALL);
			this.getHibernateTemplate().delete(obj);
			this.getHibernateTemplate().flush();
		} catch (TskException e) {
			throw e;
		} catch (Exception e) {
			throw new TskException(e);
		}
	}
	private Query appendQuery(StringBuffer buf, TskTaskDefCond cond) throws Exception{
		String objId = null;
		String creationUser = null;
		String modificationUser = null;
		String status = null;
		String correlation = null;
		String type = null;
		String priority = null;
		String processInstId = null;
		String title = null;
		String titleLike = null;
		String assigner = null;
		String assignee = null;
		String form = null;
		String multiInstOrdering = null;
		String multiInstFlowCondition = null;
		String subFlowTargetId = null;
		String subFlowTargetVersion = null;
		String subFlowExecution = null;
		Property[] extProps = null;
		if (cond != null) {
			objId = cond.getObjId();
			creationUser = cond.getCreationUser();
			modificationUser = cond.getModificationUser();
			status = cond.getStatus();
			correlation = cond.getCorrelation();
			type = cond.getType();
			priority = cond.getPriority();
			processInstId = cond.getProcessInstId();
			title = cond.getTitle();
			titleLike = cond.getTitleLike();
			assigner = cond.getAssigner();
			assignee = cond.getAssignee();
			form = cond.getForm();
			multiInstOrdering = cond.getMultiInstOrdering();
			multiInstFlowCondition = cond.getMultiInstFlowCondition();
			subFlowTargetId = cond.getSubFlowTargetId();
			subFlowTargetVersion = cond.getSubFlowTargetVersion();
			subFlowExecution = cond.getSubFlowExecution();
			extProps = cond.getExtendedProperties();
		}

		buf.append(" from TskTaskDef obj");
		if (extProps != null && extProps.length != 0) {
			for (int i=0; i<extProps.length; i++) {
				buf.append(" left join obj.extendedProperties as extProp").append(i);
			}
		}
		buf.append(" where obj.objId is not null");
		if (cond != null) {
			if (objId != null)
				buf.append(" and obj.objId = :objId");
			if (creationUser != null)
				buf.append(" and obj.creationUser = :creationUser");
			if (modificationUser != null)
				buf.append(" and obj.modificationUser = :modificationUser");
			if (status != null)
				buf.append(" and obj.status = :status");
			if (correlation != null)
				buf.append(" and obj.correlation = :correlation");
			if (type != null)
				buf.append(" and obj.type = :type");
			if (priority != null)
				buf.append(" and obj.priority = :priority");
			if (processInstId != null)
				buf.append(" and obj.processInstId = :processInstId");
			if (title != null)
				buf.append(" and obj.title = :title");
			if (titleLike != null)
				buf.append(" and obj.title like :titleLike");
			if (assigner != null)
				buf.append(" and obj.assigner = :assigner");
			if (assignee != null)
				buf.append(" and obj.assignee = :assignee");
			if (form != null)
				buf.append(" and obj.form = :form");
			if (multiInstOrdering != null)
				buf.append(" and obj.multiInstOrdering = :multiInstOrdering");
			if (multiInstFlowCondition != null)
				buf.append(" and obj.multiInstFlowCondition = :multiInstFlowCondition");
			if (subFlowTargetId != null)
				buf.append(" and obj.subFlowTargetId = :subFlowTargetId");
			if (subFlowTargetVersion != null)
				buf.append(" and obj.subFlowTargetVersion = :subFlowTargetVersion");
			if (subFlowExecution != null)
				buf.append(" and obj.subFlowExecution = :subFlowExecution");
			if (extProps != null && extProps.length != 0) {
				for (int i=0; i<extProps.length; i++) {
					Property extProp = extProps[i];
					String extName = extProp.getName();
					String extValue = extProp.getValue();
					if (extName != null)
						buf.append(" and extProp").append(i).append(".name = :extName").append(i);
					if (extValue != null)
						buf.append(" and extProp").append(i).append(".value = :extValue").append(i);
				}
			}

		}
		this.appendOrderQuery(buf, "obj", cond);
		
		Query query = this.createQuery(buf.toString(), cond);
		if (cond != null) {
			if (objId != null)
				query.setString("objId", objId);
			if (creationUser != null)
				query.setString("creationUser", creationUser);
			if (modificationUser != null)
				query.setString("modificationUser", modificationUser);
			if (status != null)
				query.setString("status", status);
			if (correlation != null)
				query.setString("correlation", correlation);
			if (type != null)
				query.setString("type", type);
			if (priority != null)
				query.setString("priority", priority);
			if (processInstId != null)
				query.setString("processInstId", processInstId);
			if (title != null)
				query.setString("title", title);
			if (titleLike != null)
				query.setString("titleLike", CommonUtil.toLikeString(titleLike));
			if (assigner != null)
				query.setString("assigner", assigner);
			if (assignee != null)
				query.setString("assignee", assignee);
			if (form != null)
				query.setString("form", form);
			if (multiInstOrdering != null)
				query.setString("multiInstOrdering", multiInstOrdering);
			if (multiInstFlowCondition != null)
				query.setString("multiInstFlowCondition", multiInstFlowCondition);
			if (subFlowTargetId != null)
				query.setString("subFlowTargetId", subFlowTargetId);
			if (subFlowTargetVersion != null)
				query.setString("subFlowTargetVersion", subFlowTargetVersion);
			if (subFlowExecution != null)
				query.setString("subFlowExecution", subFlowExecution);
			if (extProps != null && extProps.length != 0) {
				for (int i=0; i<extProps.length; i++) {
					Property extProp = extProps[i];
					String extName = extProp.getName();
					String extValue = extProp.getValue();
					if (extName != null)
						query.setString("extName"+i, extName);
					if (extValue != null)
						query.setString("extValue"+i, extValue);
				}
			}
		}
		return query;
	}
	public long getTaskDefSize(String user, TskTaskDefCond cond) throws TskException {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("select");
			buf.append(" count(obj)");
			Query query = this.appendQuery(buf, cond);
			List list = query.list();
			long count =((Long)list.get(0)).longValue();
			return count;
		} catch (TskException e) {
			throw e;
		} catch (Exception e) {
			throw new TskException(e);
		}
	}
	public TskTaskDef[] getTaskDefs(String user, TskTaskDefCond cond, String level) throws TskException {
		try {
			if (level == null)
			level = LEVEL_LITE;
			StringBuffer buf = new StringBuffer();
			buf.append("select");
			if (level.equals(LEVEL_ALL)) {
				buf.append(" obj");
			} else {
				buf.append(" obj.objId, obj.name, obj.creationUser, obj.creationDate, obj.modificationUser");
				buf.append(", obj.modificationDate, obj.status, obj.correlation, obj.type, obj.processInstId, obj.title");
				buf.append(", obj.description, obj.priority, obj.document, obj.assigner, obj.assignee");
				buf.append(", obj.assignmentDate, obj.dueDate, obj.form");
				buf.append(", obj.multiInstOrdering, obj.multiInstFlowCondition");
				buf.append(", obj.subFlowTargetId, obj.subFlowTargetVersion, obj.subFlowExecution");
				buf.append(", obj.serviceTargetId");
			}
			Query query = this.appendQuery(buf, cond);
			List list = query.list();
			if (list == null || list.isEmpty())
				return null;
			if (!level.equals(LEVEL_ALL)) {
				List objList = new ArrayList();
				for (Iterator itr = list.iterator(); itr.hasNext();) {
					Object[] fields = (Object[]) itr.next();
					TskTaskDef obj = new TskTaskDef();
					int j = 0;
					obj.setObjId((String)fields[j++]);
					obj.setName((String)fields[j++]);
					obj.setCreationUser((String)fields[j++]);
					obj.setCreationDate(((Timestamp)fields[j++]));
					obj.setModificationUser(((String)fields[j++]));
					obj.setModificationDate(((Timestamp)fields[j++]));
					obj.setStatus(((String)fields[j++]));
					obj.setCorrelation(((String)fields[j++]));
					obj.setType(((String)fields[j++]));
					obj.setProcessInstId(((String)fields[j++]));
					obj.setTitle(((String)fields[j++]));
					obj.setDescription(((String)fields[j++]));
					obj.setPriority(((String)fields[j++]));
					obj.setDocument((String)fields[j++]);
					obj.setAssigner((String)fields[j++]);
					obj.setAssignee(((String)fields[j++]));
					obj.setAssignmentDate(((String)fields[j++]));
					obj.setDueDate(((String)fields[j++]));
					obj.setForm(((String)fields[j++]));
					obj.setMultiInstOrdering(((String)fields[j++]));
					obj.setMultiInstFlowCondition(((String)fields[j++]));
					obj.setSubFlowTargetId(((String)fields[j++]));
					obj.setSubFlowTargetVersion(((String)fields[j++]));
					obj.setSubFlowExecution(((String)fields[j++]));
					obj.setServiceTargetId(((String)fields[j++]));
					objList.add(obj);
				}
				list = objList;
			}
			TskTaskDef[] objs = new TskTaskDef[list.size()];
			list.toArray(objs);
			return objs;
		} catch (TskException e) {
			throw e;
		} catch (Exception e) {
			throw new TskException(e);
		}
	}
}
