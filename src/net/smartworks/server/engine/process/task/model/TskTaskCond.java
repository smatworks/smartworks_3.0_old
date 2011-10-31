package net.smartworks.server.engine.process.task.model;

import java.util.Date;

import net.smartworks.server.engine.common.model.BaseObject;
import net.smartworks.server.engine.common.model.MisObjectCond;
import net.smartworks.server.util.CommonUtil;
import net.smartworks.server.util.DateUtil;
import net.smartworks.server.util.XmlUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TskTaskCond extends MisObjectCond{
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(TskTaskCond.class);
	
	private static final String NAME = CommonUtil.toName(TskTaskCond.class, PREFIX);
	public static final String A_CORRELATION = "correlation";
	public static final String A_TYPE = "type";
	public static final String A_PROCESSINSTID = "processInstId";
	public static final String A_TITLE = "title";
	public static final String A_TITLELIKE = "titleLike";
	public static final String A_PRIORITY = "priority";
	public static final String A_DOCUMENT = "document";
	public static final String A_ASSIGNER = "assigner";
	public static final String A_ASSIGNEE = "assignee";
	public static final String A_PERFORMER = "performer";
	public static final String A_STARTDATE = "startDate";
	public static final String A_ASSIGNMENTDATE = "assignmentDate";
	public static final String A_ASSIGNMENTDATEFROM = "assignmentDateFrom";
	public static final String A_ASSIGNMENTDATETO = "assignmentDateTo";
	public static final String A_EXECUTIONDATE = "executionDate";
	public static final String A_EXECUTIONDATEFROM = "executionDateFrom";
	public static final String A_EXECUTIONDATETO = "executionDateTo";
	public static final String A_DUEDATE = "dueDate";
	public static final String A_DUEDATEFROM = "dueDateFrom";
	public static final String A_DUEDATETO = "dueDateTo";
	public static final String A_DEF = "def";
	public static final String A_FORM = "form";
	public static final String A_MULTIINSTID = "multiInstId";
	public static final String A_MULTIINSTORDERING = "multiInstOrdering";
	public static final String A_MULTIINSTFLOWCONDITION = "multiInstFlowCondition";
	public static final String A_LOOPCOUNTER = "loopCounter";
	public static final String A_STEP = "step";
	public static final String A_TYPEIN = "typeIn";
	public static final String A_TYPEINS = "typeIns";
	public static final String A_TYPENOTIN = "typeNotIn";
	public static final String A_TYPENOTINS = "typeNotIns";
	public static final String A_PRIORITYIN = "priorityIn";
	public static final String A_PRIORITYINS = "priorityIns";
	public static final String A_PRIORITYNOTIN = "priorityNotIn";
	public static final String A_PRIORITYNOTINS = "priorityNotIns";
	public static final String A_FORMIN = "formIn";
	public static final String A_FORMINS = "formIns";
	
	private String creationUser;
	private String modificationUser;
	private String correlation;
	
	private String type;
	private String processInstId;
	private String title;
	private String titleLike;
	private String priority;
	private String document;
	private String assigner;
	private String assignee;
	private String performer;
	private Date startDate;
	private Date assignmentDate;
	private Date assignmentDateFrom;
	private Date assignmentDateTo;
	private Date executionDate;
	private Date executionDateFrom;
	private Date executionDateTo;
	private Date dueDate;
	private Date dueDateFrom;
	private Date dueDateTo;
	private String def;
	private String form;
	private String multiInstId;
	private String multiInstOrdering;
	private String multiInstFlowCondition;
	private int loopCounter = -1;
	private int step = -1;
	
	private String[] typeIns;
	private String[] typeNotIns;
	private String[] priorityIns;
	private String[] priorityNotIns;
	private String[] formIns;
	
	private String searchKey;

	public TskTaskCond() {
		super();
	}
	public String toString(String name, String tab) {
		if (name == null || name.trim().length() == 0)
			name = NAME;
		return super.toString(name, tab);
	}
	public String toAttributesString() {
		StringBuffer buf = new StringBuffer();
		buf.append(super.toAttributesString());
		appendAttributeString(A_CORRELATION, correlation, buf);
		appendAttributeString(A_TYPE, type, buf);
		appendAttributeString(A_PROCESSINSTID, processInstId, buf);
		appendAttributeString(A_PRIORITY, priority, buf);
		appendAttributeString(A_ASSIGNER, assigner, buf);
		appendAttributeString(A_ASSIGNEE, assignee, buf);
		appendAttributeString(A_PERFORMER, performer, buf);
		appendAttributeString(A_ASSIGNMENTDATE, assignmentDate, buf);
		appendAttributeString(A_ASSIGNMENTDATEFROM, assignmentDateFrom, buf);
		appendAttributeString(A_ASSIGNMENTDATETO, assignmentDateTo, buf);
		appendAttributeString(A_STARTDATE, startDate, buf);
		appendAttributeString(A_EXECUTIONDATE, executionDate, buf);
		appendAttributeString(A_EXECUTIONDATEFROM, executionDateFrom, buf);
		appendAttributeString(A_EXECUTIONDATETO, executionDateTo, buf);
		appendAttributeString(A_DUEDATE, dueDate, buf);
		appendAttributeString(A_DUEDATEFROM, dueDateFrom, buf);
		appendAttributeString(A_DUEDATETO, dueDateTo, buf);
		appendAttributeString(A_MULTIINSTID, multiInstId, buf);
		appendAttributeString(A_MULTIINSTORDERING, multiInstOrdering, buf);
		appendAttributeString(A_MULTIINSTFLOWCONDITION, multiInstFlowCondition, buf);
		appendAttributeString(A_LOOPCOUNTER, loopCounter, buf);
		appendAttributeString(A_STEP, step, buf);
		return buf.toString();
	}
	public String toElementsString(String tab) {
		StringBuffer buf = new StringBuffer();
		buf.append(super.toElementsString(tab));
		appendElementString(A_TITLE, getTitle(), tab, buf);
		appendElementString(A_TITLELIKE, getTitleLike(), tab, buf);
		appendElementString(A_DOCUMENT, getDocument(), tab, true, buf);
		appendElementString(A_DEF, getDef(), tab, true, buf);
		appendElementString(A_FORM, getForm(), tab, true, buf);
		appendElementsString(A_TYPEINS, A_TYPEIN, getTypeIns(), tab, buf);
		appendElementsString(A_TYPENOTINS, A_TYPENOTIN, getTypeNotIns(), tab, buf);
		appendElementsString(A_PRIORITYINS, A_PRIORITYIN, getPriorityIns(), tab, buf);
		appendElementsString(A_PRIORITYNOTINS, A_PRIORITYNOTIN, getPriorityNotIns(), tab, buf);
		appendElementsString(A_FORMINS, A_FORMIN, getFormIns(), tab, buf);
		return buf.toString();
	}
	public static BaseObject toObject(Node node, BaseObject baseObj) throws Exception {
		if (node == null)
			return null;
		
		TskTaskCond obj = null;
		if (baseObj == null || !(baseObj instanceof TskTaskCond))
			obj = new TskTaskCond();
		else
			obj = (TskTaskCond)baseObj;
		
		MisObjectCond.toObject(node, obj);
		
		NamedNodeMap attrMap = node.getAttributes();
		if (attrMap != null) {
			Node correlation = attrMap.getNamedItem(A_CORRELATION);
			Node type = attrMap.getNamedItem(A_TYPE);
			Node processInstId = attrMap.getNamedItem(A_PROCESSINSTID);
			Node priority = attrMap.getNamedItem(A_PRIORITY);
			Node assigner = attrMap.getNamedItem(A_ASSIGNER);
			Node assignee = attrMap.getNamedItem(A_ASSIGNEE);
			Node performer = attrMap.getNamedItem(A_PERFORMER);
			Node startDate = attrMap.getNamedItem(A_STARTDATE);
			Node assignmentDate = attrMap.getNamedItem(A_ASSIGNMENTDATE);
			Node assignmentDateFrom = attrMap.getNamedItem(A_ASSIGNMENTDATEFROM);
			Node assignmentDateTo = attrMap.getNamedItem(A_ASSIGNMENTDATETO);
			Node executionDate = attrMap.getNamedItem(A_EXECUTIONDATE);
			Node executionDateFrom = attrMap.getNamedItem(A_EXECUTIONDATEFROM);
			Node executionDateTo = attrMap.getNamedItem(A_EXECUTIONDATETO);
			Node dueDate = attrMap.getNamedItem(A_DUEDATE);
			Node dueDateFrom = attrMap.getNamedItem(A_DUEDATEFROM);
			Node dueDateTo = attrMap.getNamedItem(A_DUEDATETO);
			Node multiInstId = attrMap.getNamedItem(A_MULTIINSTID);
			Node multiInstOrdering = attrMap.getNamedItem(A_MULTIINSTORDERING);
			Node multiInstFlowCondition = attrMap.getNamedItem(A_MULTIINSTFLOWCONDITION);
			Node loopCounter = attrMap.getNamedItem(A_LOOPCOUNTER);
			Node step = attrMap.getNamedItem(A_STEP);
			if (correlation != null)
				obj.setCorrelation(correlation.getNodeValue());
			if (type != null)
				obj.setType(type.getNodeValue());
			if (processInstId != null)
				obj.setProcessInstId(processInstId.getNodeValue());
			if (priority != null)
				obj.setPriority(priority.getNodeValue());
			if (assigner != null)
				obj.setAssigner(assigner.getNodeValue());
			if (assignee != null)
				obj.setAssignee(assignee.getNodeValue());
			if (performer != null)
				obj.setPerformer(performer.getNodeValue());
			if (startDate != null)
				obj.setStartDate(DateUtil.toDate(startDate.getNodeValue()));
			if (assignmentDate != null)
				obj.setAssignmentDate(DateUtil.toDate(assignmentDate.getNodeValue()));
			if (assignmentDateFrom != null)
				obj.setAssignmentDateFrom(DateUtil.toDate(assignmentDateFrom.getNodeValue()));
			if (assignmentDateTo != null)
				obj.setAssignmentDateTo(DateUtil.toDate(assignmentDateTo.getNodeValue()));
			if (executionDate != null)
				obj.setExecutionDate(DateUtil.toDate(executionDate.getNodeValue()));
			if (executionDateFrom != null)
				obj.setExecutionDateFrom(DateUtil.toDate(executionDateFrom.getNodeValue()));
			if (executionDateTo != null)
				obj.setExecutionDateTo(DateUtil.toDate(executionDateTo.getNodeValue()));
			if (dueDate != null)
				obj.setDueDate(DateUtil.toDate(dueDate.getNodeValue()));
			if (dueDateFrom != null)
				obj.setDueDateFrom(DateUtil.toDate(dueDateFrom.getNodeValue()));
			if (dueDateTo != null)
				obj.setDueDateTo(DateUtil.toDate(dueDateTo.getNodeValue()));
			if (multiInstId != null)
				obj.setMultiInstId(multiInstId.getNodeValue());
			if (multiInstOrdering != null)
				obj.setMultiInstOrdering(multiInstOrdering.getNodeValue());
			if (multiInstFlowCondition != null)
				obj.setMultiInstFlowCondition(multiInstFlowCondition.getNodeValue());
			if (loopCounter != null)
				obj.setLoopCounter(Integer.parseInt(loopCounter.getNodeValue()));
			if (step != null)
				obj.setStep(Integer.parseInt(step.getNodeValue()));
		}
		
		NodeList childNodeList = node.getChildNodes();
		if (childNodeList == null || childNodeList.getLength() == 0)
			return obj;
		for (int i=0; i<childNodeList.getLength(); i++) {
			Node childNode = childNodeList.item(i);
			if (childNode.getNodeType() != Node.ELEMENT_NODE || childNode.getNodeName() == null)
				continue;
			if (childNode.getNodeName().equals(A_TITLE)) {
				obj.setTitle(getNodeValue(childNode));
			} else if (childNode.getNodeName().equals(A_TITLELIKE)) {
				obj.setTitleLike(getNodeValue(childNode));
			} else if (childNode.getNodeName().equals(A_DOCUMENT)) {
				obj.setDocument(getNodeValue(childNode, true));
			} else if (childNode.getNodeName().equals(A_DEF)) {
				obj.setDef(getNodeValue(childNode, true));
			} else if (childNode.getNodeName().equals(A_FORM)) {
				obj.setForm(getNodeValue(childNode, true));
			} else if (childNode.getNodeName().equals(A_TYPEINS)) {
				Node[] nodes = getNodes(childNode);
				if (nodes == null || nodes.length == 0)
					continue;
				String[] objs = new String[nodes.length];
				for (int j=0; j<nodes.length; j++)
					objs[j] = nodes[j].getNodeValue();
				obj.setTypeIns(objs);
			} else if (childNode.getNodeName().equals(A_TYPENOTINS)) {
				Node[] nodes = getNodes(childNode);
				if (nodes == null || nodes.length == 0)
					continue;
				String[] objs = new String[nodes.length];
				for (int j=0; j<nodes.length; j++)
					objs[j] = nodes[j].getNodeValue();
				obj.setTypeNotIns(objs);
			} else if (childNode.getNodeName().equals(A_PRIORITYINS)) {
				Node[] nodes = getNodes(childNode);
				if (nodes == null || nodes.length == 0)
					continue;
				String[] objs = new String[nodes.length];
				for (int j=0; j<nodes.length; j++)
					objs[j] = nodes[j].getNodeValue();
				obj.setPriorityIns(objs);
			} else if (childNode.getNodeName().equals(A_PRIORITYNOTINS)) {
				Node[] nodes = getNodes(childNode);
				if (nodes == null || nodes.length == 0)
					continue;
				String[] objs = new String[nodes.length];
				for (int j=0; j<nodes.length; j++)
					objs[j] = nodes[j].getNodeValue();
				obj.setPriorityNotIns(objs);
			} else if (childNode.getNodeName().equals(A_FORMINS)) {
				Node[] nodes = getNodes(childNode);
				if (nodes == null || nodes.length == 0)
					continue;
				String[] objs = new String[nodes.length];
				for (int j=0; j<nodes.length; j++)
					objs[j] = nodes[j].getNodeValue();
				obj.setFormIns(objs);
			}
		}
		return obj;
	}
	public static BaseObject toObject(String str) throws Exception {
		if (str == null)
			return null;
		Document doc = XmlUtil.toDocument(str);
		if (doc == null)
			return null;
		return toObject(doc.getDocumentElement(), null);
	}
	public static TskTaskCond[] add(TskTaskCond[] objs, TskTaskCond obj) {
		if (obj == null)
			return objs;
		int size = 0;
		if (objs != null)
			size = objs.length;
		TskTaskCond[] newObjs = new TskTaskCond[size+1];
		int i;
		for (i=0; i<size; i++)
			newObjs[i] = objs[i];
		newObjs[i] = obj;
		return newObjs;
	}
	public static TskTaskCond[] remove(TskTaskCond[] objs, TskTaskCond obj) {
		if (obj == null)
			return objs;
		int size = 0;
		if (objs != null)
			size = objs.length;
		if (size == 0)
			return objs;
		TskTaskCond[] newObjs = new TskTaskCond[size-1];
		int i;
		int j = 0;
		for (i=0; i<size; i++) {
			if (objs[i].equals(obj))
				continue;
			newObjs[j++] = objs[i];
		}
		return newObjs;
	}
	public static TskTaskCond[] left(TskTaskCond[] objs, TskTaskCond obj) {
		if (objs == null || objs.length == 0 || obj == null)
			return objs;
		int idx = -1;
		for (int i=0; i<objs.length; i++) {
			if (!objs[i].equals(obj))
				continue;
			idx = i;
			break;
		}
		if (idx < 1)
			return objs;
		TskTaskCond[] newObjs = new TskTaskCond[objs.length];
		for (int i=0; i<objs.length; i++) {
			if (i == idx) {
				newObjs[i] = objs[idx-1];
				continue;
			} else if (i == idx-1) {
				newObjs[i] = objs[idx];
				continue;
			}
			newObjs[i] = objs[i];
		}
		return newObjs;
	}
	public static TskTaskCond[] right(TskTaskCond[] objs, TskTaskCond obj) {
		if (objs == null || objs.length == 0 || obj == null)
			return objs;
		int idx = -1;
		for (int i=0; i<objs.length; i++) {
			if (!objs[i].equals(obj))
				continue;
			idx = i;
			break;
		}
		if (idx == -1 || idx+1 == objs.length)
			return objs;
		TskTaskCond[] newObjs = new TskTaskCond[objs.length];
		for (int i=0; i<objs.length; i++) {
			if (i == idx) {
				newObjs[i] = objs[idx+1];
				continue;
			} else if (i == idx+1) {
				newObjs[i] = objs[idx];
				continue;
			}
			newObjs[i] = objs[i];
		}
		return newObjs;
	}
	public Object clone() throws CloneNotSupportedException {
		try {
			return toObject(this.toString());
		} catch (Exception e) {
			logger.warn(e, e);
			return null;
		}
	}
	
	public Date getAssignmentDate() {
		return assignmentDate;
	}
	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}
	public String getCreationUser() {
		return creationUser;
	}
	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getExecutionDate() {
		return executionDate;
	}
	public void setExecutionDate(Date executionDate) {
		this.executionDate = executionDate;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public String getModificationUser() {
		return modificationUser;
	}
	public void setModificationUser(String modificationUser) {
		this.modificationUser = modificationUser;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getProcessInstId() {
		return processInstId;
	}
	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitleLike() {
		return titleLike;
	}
	public void setTitleLike(String titleLike) {
		this.titleLike = titleLike;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getAssigner() {
		return assigner;
	}
	public void setAssigner(String assigner) {
		this.assigner = assigner;
	}
	public String getCorrelation() {
		return correlation;
	}
	public void setCorrelation(String correlation) {
		this.correlation = correlation;
	}
	public String getDef() {
		return def;
	}
	public void setDef(String def) {
		this.def = def;
	}
	public String[] getTypeIns() {
		return typeIns;
	}
	public void setTypeIns(String[] typeIns) {
		this.typeIns = typeIns;
	}
	public String[] getTypeNotIns() {
		return typeNotIns;
	}
	public void setTypeNotIns(String[] typeNotIns) {
		this.typeNotIns = typeNotIns;
	}
	public String[] getFormIns() {
		return formIns;
	}
	public void setFormIns(String[] formIns) {
		this.formIns = formIns;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public int getLoopCounter() {
		return loopCounter;
	}
	public void setLoopCounter(int loopCounter) {
		this.loopCounter = loopCounter;
	}
	public String getMultiInstFlowCondition() {
		return multiInstFlowCondition;
	}
	public void setMultiInstFlowCondition(String multiInstFlowCondition) {
		this.multiInstFlowCondition = multiInstFlowCondition;
	}
	public String getMultiInstId() {
		return multiInstId;
	}
	public void setMultiInstId(String multiInstId) {
		this.multiInstId = multiInstId;
	}
	public String getMultiInstOrdering() {
		return multiInstOrdering;
	}
	public void setMultiInstOrdering(String multiInstOrdering) {
		this.multiInstOrdering = multiInstOrdering;
	}
	public String[] getPriorityIns() {
		return priorityIns;
	}
	public void setPriorityIns(String[] priorityIns) {
		this.priorityIns = priorityIns;
	}
	public String[] getPriorityNotIns() {
		return priorityNotIns;
	}
	public void setPriorityNotIns(String[] priorityNotIns) {
		this.priorityNotIns = priorityNotIns;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getPerformer() {
		return performer;
	}
	public void setPerformer(String performer) {
		this.performer = performer;
	}
	public Date getAssignmentDateFrom() {
		return assignmentDateFrom;
	}
	public void setAssignmentDateFrom(Date assignmentDateFrom) {
		this.assignmentDateFrom = assignmentDateFrom;
	}
	public Date getAssignmentDateTo() {
		return assignmentDateTo;
	}
	public void setAssignmentDateTo(Date assignmentDateTo) {
		this.assignmentDateTo = assignmentDateTo;
	}
	public Date getDueDateFrom() {
		return dueDateFrom;
	}
	public void setDueDateFrom(Date dueDateFrom) {
		this.dueDateFrom = dueDateFrom;
	}
	public Date getDueDateTo() {
		return dueDateTo;
	}
	public void setDueDateTo(Date dueDateTo) {
		this.dueDateTo = dueDateTo;
	}
	public Date getExecutionDateFrom() {
		return executionDateFrom;
	}
	public void setExecutionDateFrom(Date executionDateFrom) {
		this.executionDateFrom = executionDateFrom;
	}
	public Date getExecutionDateTo() {
		return executionDateTo;
	}
	public void setExecutionDateTo(Date executionDateTo) {
		this.executionDateTo = executionDateTo;
	}
}
