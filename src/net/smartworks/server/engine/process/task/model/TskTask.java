package net.smartworks.server.engine.process.task.model;

import java.util.Date;

import net.smartworks.server.engine.common.model.BaseObject;
import net.smartworks.server.engine.common.model.ClassObject;
import net.smartworks.server.engine.common.model.MisObject;
import net.smartworks.server.util.CommonUtil;
import net.smartworks.server.util.DateUtil;
import net.smartworks.server.util.XmlUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TskTask extends MisObject {
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(TskTask.class);
	protected static final String PREFIX = "Tsk";
	
	private static final String NAME = CommonUtil.toName(TskTask.class, PREFIX);
	public static final String A_CORRELATION = "correlation";
	public static final String A_TYPE = "type";
	public static final String A_PROCESSINSTID = "processInstId";
	public static final String A_TITLE = "title";
	public static final String A_PRIORITY = "priority";
	public static final String A_DOCUMENT = "document";
	public static final String A_ASSIGNER = "assigner";
	public static final String A_ASSIGNEE = "assignee";
	public static final String A_PERFORMER = "performer";
	public static final String A_ASSIGNMENTDATE = "assignmentDate";
	public static final String A_STARTDATE = "startDate";
	public static final String A_EXECUTIONDATE = "executionDate";
	public static final String A_DUEDATE = "dueDate";
	public static final String A_DEF = "def";
	public static final String A_FORM = "form";
	public static final String A_MULTIINSTID = "multiInstId";
	public static final String A_MULTIINSTORDERING = "multiInstOrdering";
	public static final String A_MULTIINSTFLOWCONDITION = "multiInstFlowCondition";
	public static final String A_LOOPCOUNTER = "loopCounter";
	public static final String A_STEP = "step";
	public static final String A_INSTVARIABLE = "instVariable";
	
	private String correlation;
	
	private String type;
	private String processInstId;
	private String title;
	private String priority;
	private String document;
	private String assigner;
	private String assignee;
	private String performer;
	private Date assignmentDate;
	private Date startDate;
	private Date executionDate;
	private Date dueDate;
	private String def;
	private String form;
	private String multiInstId;
	private String multiInstOrdering;
	private String multiInstFlowCondition;
	private Integer loopCounterInteger;
	private Integer stepInteger;
	private String instVariable;
	
	public TskTask() {
		super();
	}
	public String toString(String name, String tab){
		if(name == null || name.trim().length() == 0)
			name = NAME;
		return super.toString(name, tab);
	}
	public String toAttributesString(){
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
		appendAttributeString(A_STARTDATE, startDate, buf);
		appendAttributeString(A_EXECUTIONDATE, executionDate, buf);
		appendAttributeString(A_DUEDATE, dueDate, buf);
		appendAttributeString(A_MULTIINSTID, multiInstId, buf);
		appendAttributeString(A_MULTIINSTORDERING, multiInstOrdering, buf);
		appendAttributeString(A_MULTIINSTFLOWCONDITION, multiInstFlowCondition, buf);
		appendAttributeString(A_LOOPCOUNTER, getLoopCounter(), buf);
		appendAttributeString(A_STEP, getStep(), buf);
		return buf.toString();
	}
	public String toElementsString(String tab) {
		StringBuffer buf = new StringBuffer();
		buf.append(super.toElementsString(tab));
		appendElementString(A_TITLE, getTitle(), tab, buf);
		appendElementString(A_DOCUMENT, getDocument(), tab, true, buf);
		appendElementString(A_DEF, getDef(), tab, true, buf);
		appendElementString(A_FORM, getForm(), tab, true, buf);
		appendElementString(A_INSTVARIABLE, getInstVariable(), tab, true, buf);
		return buf.toString();
	}
	public static BaseObject toObject(Node node, BaseObject baseObj) throws Exception {
		if (node == null)
			return null;
		
		TskTask obj = null;
		if (baseObj == null || !(baseObj instanceof TskTask))
			obj = new TskTask();
		else
			obj = (TskTask)baseObj;
		
		MisObject.toObject(node, obj);
		
		NamedNodeMap attrMap = node.getAttributes();
		if (attrMap != null) {
			Node correlation = attrMap.getNamedItem(A_CORRELATION);
			Node type = attrMap.getNamedItem(A_TYPE);
			Node processInstId = attrMap.getNamedItem(A_PROCESSINSTID);
			Node priority = attrMap.getNamedItem(A_PRIORITY);
			Node assigner = attrMap.getNamedItem(A_ASSIGNER);
			Node assignee = attrMap.getNamedItem(A_ASSIGNEE);
			Node performer = attrMap.getNamedItem(A_PERFORMER);
			Node assignmentDate = attrMap.getNamedItem(A_ASSIGNMENTDATE);
			Node startDate = attrMap.getNamedItem(A_STARTDATE);
			Node executionDate = attrMap.getNamedItem(A_EXECUTIONDATE);
			Node dueDate = attrMap.getNamedItem(A_DUEDATE);
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
			if (assignmentDate != null)
				obj.setAssignmentDate(DateUtil.toDate(assignmentDate.getNodeValue()));
			if (startDate != null)
				obj.setStartDate(DateUtil.toDate(startDate.getNodeValue()));
			if (executionDate != null)
				obj.setExecutionDate(DateUtil.toDate(executionDate.getNodeValue()));
			if (dueDate != null)
				obj.setDueDate(DateUtil.toDate(dueDate.getNodeValue()));
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
			} else if (childNode.getNodeName().equals(A_DOCUMENT)) {
				obj.setDocument(getNodeValue(childNode, true));
			} else if (childNode.getNodeName().equals(A_DEF)) {
				obj.setDef(getNodeValue(childNode, true));
			} else if (childNode.getNodeName().equals(A_FORM)) {
				obj.setForm(getNodeValue(childNode, true));
			} else if (childNode.getNodeName().equals(A_INSTVARIABLE)) {
				obj.setInstVariable(getNodeValue(childNode, true));
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
	
	public static TskTask[] add(TskTask[] objs, TskTask obj) {
		if (obj == null)
			return objs;
		int size = 0;
		if (objs != null)
			size = objs.length;
		TskTask[] newObjs = new TskTask[size+1];
		int i;
		for (i=0; i<size; i++)
			newObjs[i] = objs[i];
		newObjs[i] = obj;
		return newObjs;
	}
	public static TskTask[] remove(TskTask[] objs, TskTask obj) {
		if (obj == null)
			return objs;
		int size = 0;
		if (objs != null)
			size = objs.length;
		if (size == 0)
			return objs;
		TskTask[] newObjs = new TskTask[size-1];
		int i;
		int j = 0;
		for (i=0; i<size; i++) {
			if (objs[i].equals(obj))
				continue;
			newObjs[j++] = objs[i];
		}
		return newObjs;
	}
	public static TskTask[] left(TskTask[] objs, TskTask obj) {
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
		TskTask[] newObjs = new TskTask[objs.length];
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
	public static TskTask[] right(TskTask[] objs, TskTask obj) {
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
		TskTask[] newObjs = new TskTask[objs.length];
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
	public ClassObject cloneNew() {
		TskTask obj = null;
		try {
			obj = (TskTask)this.clone();
			obj.setObjId(null);
			obj.setStatus(null);
			obj.setCreationDate(null);
			obj.setModificationDate(null);
			obj.setStartDate(null);
			obj.setMultiInstId(null);
		} catch (CloneNotSupportedException e) {
			logger.warn(e, e);
		}
		return obj;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getAssignmentDate() {
		return assignmentDate;
	}
	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
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
	public int getLoopCounter() {
		if (loopCounterInteger == null)
			return 0;
		return loopCounterInteger.intValue();
	}
	public void setLoopCounter(int loopCounter) {
		if (loopCounter == 0) {
			if (this.loopCounterInteger != null)
				this.loopCounterInteger = null;
			return;
		}
		this.loopCounterInteger = new Integer(loopCounter);
	}
	public Integer getLoopCounterInteger() {
		return loopCounterInteger;
	}
	public void setLoopCounterInteger(Integer loopCounterInteger) {
		this.loopCounterInteger = loopCounterInteger;
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
	public Integer getStepInteger() {
		return stepInteger;
	}
	public void setStepInteger(Integer stepInteger) {
		this.stepInteger = stepInteger;
	}
	public int getStep() {
		if (stepInteger == null)
			return 0;
		return stepInteger.intValue();
	}
	public void setStep(int step) {
		if (step == 0) {
			if (this.stepInteger != null)
				this.stepInteger = null;
			return;
		}
		this.stepInteger = new Integer(step);
	}
	public String getPerformer() {
		return performer;
	}
	public void setPerformer(String performer) {
		this.performer = performer;
	}
	public String getInstVariable() {
		return instVariable;
	}
	public void setInstVariable(String instVariable) {
		this.instVariable = instVariable;
	}
}
