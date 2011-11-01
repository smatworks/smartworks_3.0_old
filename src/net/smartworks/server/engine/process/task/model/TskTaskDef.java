package net.smartworks.server.engine.process.task.model;

import net.smartworks.server.engine.common.model.BaseObject;
import net.smartworks.server.engine.common.model.MisObject;
import net.smartworks.server.util.CommonUtil;
import net.smartworks.server.util.XmlUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TskTaskDef extends MisObject {
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(TskTaskDef.class);
	protected static final String PREFIX = "Tsk";
	
	private static final String NAME = CommonUtil.toName(TskTaskDef.class, PREFIX);
	public static final String A_CORRELATION = "correlation";
	public static final String A_TYPE = "type";
	public static final String A_PROCESSINSTID = "processInstId";
	public static final String A_TITLE = "title";
	public static final String A_PRIORITY = "priority";
	public static final String A_DOCUMENT = "document";
	public static final String A_ASSIGNER = "assigner";
	public static final String A_ASSIGNEE = "assignee";
	public static final String A_ASSIGNMENTDATE = "assignmentDate";
	public static final String A_DUEDATE = "dueDate";
	public static final String A_FORM = "form";
	public static final String A_MULTIINSTORDERING = "multiInstOrdering";
	public static final String A_MULTIINSTFLOWCONDITION = "multiInstFlowCondition";
	public static final String A_SUBFLOWTARGETID = "subFlowTargetId";
	public static final String A_SUBFLOWTARGETVERSION = "subFlowTargetVersion"; 
	public static final String A_SUBFLOWEXECUTION = "subFlowExecution";
	public static final String A_SERVICETARGETID = "serviceTargetId";
	
	private String correlation;
	
	private String type;
	private String processInstId;
	private String title;
	private String priority;
	private String document;
	private String assigner;
	private String assignee;
	private String assignmentDate;
	private String dueDate;
	private String form;
	private String multiInstOrdering;
	private String multiInstFlowCondition;
	private String subFlowTargetId;
	private String subFlowTargetVersion;
	private String subFlowExecution;
	private String serviceTargetId;
	
	public TskTaskDef() {
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
		appendAttributeString(A_ASSIGNMENTDATE, assignmentDate, buf);
		appendAttributeString(A_DUEDATE, dueDate, buf);
		appendAttributeString(A_MULTIINSTORDERING, multiInstOrdering, buf);
		appendAttributeString(A_MULTIINSTFLOWCONDITION, multiInstFlowCondition, buf);
		appendAttributeString(A_SUBFLOWTARGETID, subFlowTargetId, buf);
		appendAttributeString(A_SUBFLOWTARGETVERSION, subFlowTargetVersion, buf);
		appendAttributeString(A_SUBFLOWEXECUTION, subFlowExecution, buf);
		appendAttributeString(A_SERVICETARGETID, serviceTargetId, buf);
		return buf.toString();
	}
	public String toElementsString(String tab) {
		StringBuffer buf = new StringBuffer();
		buf.append(super.toElementsString(tab));
		appendElementString(A_TITLE, getTitle(), tab, buf);
		appendElementString(A_DOCUMENT, getDocument(), tab, true, buf);
		appendElementString(A_FORM, getForm(), tab, true, buf);
		return buf.toString();
	}
	
	public static BaseObject toObject(Node node, BaseObject baseObj) throws Exception {
		if (node == null)
			return null;
		
		TskTaskDef obj = null;
		if (baseObj == null || !(baseObj instanceof TskTaskDef))
			obj = new TskTaskDef();
		else
			obj = (TskTaskDef)baseObj;
		
		MisObject.toObject(node, obj);
		
		NamedNodeMap attrMap = node.getAttributes();
		if (attrMap != null) {
			Node correlation = attrMap.getNamedItem(A_CORRELATION);
			Node type = attrMap.getNamedItem(A_TYPE);
			Node processInstId = attrMap.getNamedItem(A_PROCESSINSTID);
			Node priority = attrMap.getNamedItem(A_PRIORITY);
			Node assigner = attrMap.getNamedItem(A_ASSIGNER);
			Node assignee = attrMap.getNamedItem(A_ASSIGNEE);
			Node assignmentDate = attrMap.getNamedItem(A_ASSIGNMENTDATE);
			Node dueDate = attrMap.getNamedItem(A_DUEDATE);
			Node multiInstOrdering = attrMap.getNamedItem(A_MULTIINSTORDERING);
			Node multiInstFlowCondition = attrMap.getNamedItem(A_MULTIINSTFLOWCONDITION);
			Node subFlowTargetId = attrMap.getNamedItem(A_SUBFLOWTARGETID);
			Node subFlowTargetVersion = attrMap.getNamedItem(A_SUBFLOWTARGETVERSION);
			Node subFlowExecution = attrMap.getNamedItem(A_SUBFLOWEXECUTION);
			Node serviceTargetId = attrMap.getNamedItem(A_SERVICETARGETID);
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
			if (assignmentDate != null)
				obj.setAssignmentDate(assignmentDate.getNodeValue());
			if (dueDate != null)
				obj.setDueDate(dueDate.getNodeValue());
			if (multiInstOrdering != null)
				obj.setMultiInstOrdering(multiInstOrdering.getNodeValue());
			if (multiInstFlowCondition != null)
				obj.setMultiInstFlowCondition(multiInstFlowCondition.getNodeValue());
			if (subFlowTargetId != null)
				obj.setSubFlowTargetId(subFlowTargetId.getNodeValue());
			if (subFlowTargetVersion != null)
				obj.setSubFlowTargetVersion(subFlowTargetVersion.getNodeValue());
			if (subFlowExecution != null)
				obj.setSubFlowExecution(subFlowExecution.getNodeValue());
			if (serviceTargetId != null)
				obj.setServiceTargetId(serviceTargetId.getNodeValue());
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
			} else if (childNode.getNodeName().equals(A_FORM)) {
				obj.setForm(getNodeValue(childNode, true));
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
	
	public static TskTaskDef[] add(TskTaskDef[] objs, TskTaskDef obj) {
		if (obj == null)
			return objs;
		int size = 0;
		if (objs != null)
			size = objs.length;
		TskTaskDef[] newObjs = new TskTaskDef[size+1];
		int i;
		for (i=0; i<size; i++)
			newObjs[i] = objs[i];
		newObjs[i] = obj;
		return newObjs;
	}
	public static TskTaskDef[] remove(TskTaskDef[] objs, TskTaskDef obj) {
		if (obj == null)
			return objs;
		int size = 0;
		if (objs != null)
			size = objs.length;
		if (size == 0)
			return objs;
		TskTaskDef[] newObjs = new TskTaskDef[size-1];
		int i;
		int j = 0;
		for (i=0; i<size; i++) {
			if (objs[i].equals(obj))
				continue;
			newObjs[j++] = objs[i];
		}
		return newObjs;
	}
	public static TskTaskDef[] left(TskTaskDef[] objs, TskTaskDef obj) {
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
		TskTaskDef[] newObjs = new TskTaskDef[objs.length];
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
	public static TskTaskDef[] right(TskTaskDef[] objs, TskTaskDef obj) {
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
		TskTaskDef[] newObjs = new TskTaskDef[objs.length];
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
	
	public String getAssignmentDate() {
		return assignmentDate;
	}
	public void setAssignmentDate(String assignmentDate) {
		this.assignmentDate = assignmentDate;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
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
	public String getMultiInstFlowCondition() {
		return multiInstFlowCondition;
	}
	public void setMultiInstFlowCondition(String multiInstFlowCondition) {
		this.multiInstFlowCondition = multiInstFlowCondition;
	}
	public String getMultiInstOrdering() {
		return multiInstOrdering;
	}
	public void setMultiInstOrdering(String multiInstOrdering) {
		this.multiInstOrdering = multiInstOrdering;
	}
	public String getSubFlowTargetId() {
		return subFlowTargetId;
	}
	public void setSubFlowTargetId(String subFlowTargetId) {
		this.subFlowTargetId = subFlowTargetId;
	}
	public String getSubFlowTargetVersion() {
		return subFlowTargetVersion;
	}
	public void setSubFlowTargetVersion(String subFlowTargetVersion) {
		this.subFlowTargetVersion = subFlowTargetVersion;
	}
	public String getSubFlowExecution() {
		return subFlowExecution;
	}
	public void setSubFlowExecution(String subFlowExecution) {
		this.subFlowExecution = subFlowExecution;
	}
	public String getServiceTargetId() {
		return serviceTargetId;
	}
	public void setServiceTargetId(String serviceTargetId) {
		this.serviceTargetId = serviceTargetId;
	}
}
