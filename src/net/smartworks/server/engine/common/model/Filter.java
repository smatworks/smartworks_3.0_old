package net.smartworks.server.engine.common.model;

import net.smartworks.server.util.CommonUtil;
import net.smartworks.server.util.XmlUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Filter extends BaseObject {
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(Filters.class);
	
	private static final String NAME = CommonUtil.toName(Filters.class, PREFIX);

	public static final String A_OPERATOR = "operator";
	public static final String A_LEFTOPERAND = "leftOperand";
	public static final String A_RIGHTOPERAND = "rightOperand";
	public static final String A_OPERANDTYPE = "type";
	
	public static final String OPERANDTYPE_FIELD = "field";
	public static final String OPERANDTYPE_STRING = "string";
	public static final String OPERANDTYPE_INT = "int";
	public static final String OPERANDTYPE_LONG = "long";
	public static final String OPERANDTYPE_FLOAT = "float";
	public static final String OPERANDTYPE_DOUBLE = "double";
	public static final String OPERANDTYPE_DATE = "date";
	
	private String operator;
	
	private String leftOperandType = OPERANDTYPE_FIELD;
	private String leftOperandValue;
	private String rightOperandType;
	private String rightOperandValue;
	public Filter() {
		super();
	}
	public Filter(String operator, String leftOperandValue, String rightOperandValue) {
		super();
		this.operator = operator;
		this.leftOperandValue = leftOperandValue;
		this.rightOperandValue = rightOperandValue;
	}
	public Filter(String operator, String leftOperandValue, String rightOperandType, String rightOperandValue) {
		super();
		this.operator = operator;
		this.leftOperandValue = leftOperandValue;
		this.rightOperandType = rightOperandType;
		this.rightOperandValue = rightOperandValue;
	}
	public Filter(String operator, String leftOperandType, String leftOperandValue, String rightOperandType, String rightOperandValue) {
		super();
		this.operator = operator;
		this.leftOperandType = leftOperandType;
		this.leftOperandValue = leftOperandValue;
		this.rightOperandType = rightOperandType;
		this.rightOperandValue = rightOperandValue;
	}
	private static void populateLeftOperandObject(Node node, Filter obj) throws Exception {
		if (node == null || obj == null)
			return;
		
		NamedNodeMap attrMap = node.getAttributes();
		if (attrMap != null) {
			Node leftOperandType = attrMap.getNamedItem(A_OPERANDTYPE);
			if (leftOperandType != null)
				obj.setLeftOperandType(leftOperandType.getNodeValue());
		}
		
		// elements �� ����
		NodeList childNodeList = node.getChildNodes();
		if (childNodeList == null || childNodeList.getLength() == 0)
			return;
		for (int i=0; i<childNodeList.getLength(); i++) {
			Node childNode = childNodeList.item(i);
			if (childNode.getNodeType() != Node.TEXT_NODE && childNode.getNodeType() != Node.CDATA_SECTION_NODE)
				continue;
			obj.setLeftOperandValue(getNodeValue(childNode));
			break;
		}
	}
	private static void populateRightOperandObject(Node node, Filter obj) throws Exception {
		if (node == null || obj == null)
			return;
		
		NamedNodeMap attrMap = node.getAttributes();
		if (attrMap != null) {
			Node rightOperandType = attrMap.getNamedItem(A_OPERANDTYPE);
			if (rightOperandType != null)
				obj.setRightOperandType(rightOperandType.getNodeValue());
		}
		
		// elements �� ����
		NodeList childNodeList = node.getChildNodes();
		if (childNodeList == null || childNodeList.getLength() == 0)
			return;
		for (int i=0; i<childNodeList.getLength(); i++) {
			Node childNode = childNodeList.item(i);
			if (childNode.getNodeType() != Node.TEXT_NODE && childNode.getNodeType() != Node.CDATA_SECTION_NODE)
				continue;
			obj.setRightOperandValue(getNodeValue(childNode));
			break;
		}
	}
	public String toString(String name, String tab) {
		if (name == null || name.trim().length() == 0)
			name = NAME;
		return super.toString(name, tab);
	}
	public String toLiteString(String name, String tab) {
		if (name == null || name.trim().length() == 0)
			name = NAME;
		return super.toLiteString(name, tab);
	}
	public String toAttributesString() {
		StringBuffer buf = new StringBuffer();
		buf.append(super.toAttributesString());
		appendAttributeString(A_OPERATOR, operator, true, buf);
		return buf.toString();
	}
	public String toElementsString(String tab, boolean lite) {
		StringBuffer buf = new StringBuffer();
		buf.append(super.toElementsString(tab));
		appendElementString(A_LEFTOPERAND, leftOperandValue, new Property[] {new Property(A_OPERANDTYPE, leftOperandType)}, tab, true, buf);
		appendElementString(A_RIGHTOPERAND, rightOperandValue, new Property[] {new Property(A_OPERANDTYPE, rightOperandType)}, tab, true, buf);
		return buf.toString();
	}
	public static BaseObject toObject(Node node, BaseObject baseObj) throws Exception {
		if (node == null)
			return null;
		
		Filter obj = null;
		if (baseObj == null || !(baseObj instanceof Filter))
			obj = new Filter();
		else
			obj = (Filter)baseObj;
		
		BaseObject.toObject(node, obj);
		
		NamedNodeMap attrMap = node.getAttributes();
		if (attrMap != null) {
			Node operator = attrMap.getNamedItem(A_OPERATOR);
			if (operator != null)
				obj.setOperator(operator.getNodeValue());
		}
		
		NodeList childNodeList = node.getChildNodes();
		if (childNodeList == null || childNodeList.getLength() == 0)
			return obj;
		for (int i=0; i<childNodeList.getLength(); i++) {
			Node childNode = childNodeList.item(i);
			if (childNode.getNodeType() != Node.ELEMENT_NODE || childNode.getNodeName() == null)
				continue;
			if (childNode.getNodeName().equals(A_LEFTOPERAND)) {
				populateLeftOperandObject(childNode, obj);
			} else if (childNode.getNodeName().equals(A_RIGHTOPERAND)) {
				populateRightOperandObject(childNode, obj);
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
	public static Filter[] add(Filter[] objs, Filter obj) {
		if (obj == null)
			return objs;
		int size = 0;
		if (objs != null)
			size = objs.length;
		Filter[] newObjs = new Filter[size+1];
		int i;
		for (i=0; i<size; i++)
			newObjs[i] = objs[i];
		newObjs[i] = obj;
		return newObjs;
	}
	public static Filter[] remove(Filter[] objs, Filter obj) {
		if (obj == null)
			return objs;
		int size = 0;
		if (objs != null)
			size = objs.length;
		if (size == 0)
			return objs;
		Filter[] newObjs = new Filter[size-1];
		int i;
		int j = 0;
		for (i=0; i<size; i++) {
			if (objs[i].equals(obj))
				continue;
			newObjs[j++] = objs[i];
		}
		return newObjs;
	}
	public static Filter[] left(Filter[] objs, Filter obj) {
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
		Filter[] newObjs = new Filter[objs.length];
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
	public static Filter[] right(Filter[] objs, Filter obj) {
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
		Filter[] newObjs = new Filter[objs.length];
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
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getLeftOperandType() {
		return leftOperandType;
	}
	public void setLeftOperandType(String leftOperandType) {
		this.leftOperandType = leftOperandType;
	}
	public String getLeftOperandValue() {
		return leftOperandValue;
	}
	public void setLeftOperandValue(String leftOperandValue) {
		this.leftOperandValue = leftOperandValue;
	}
	public String getRightOperandType() {
		return rightOperandType;
	}
	public void setRightOperandType(String rightOperandType) {
		this.rightOperandType = rightOperandType;
	}
	public String getRightOperandValue() {
		return rightOperandValue;
	}
	public void setRightOperandValue(String rightOperandValue) {
		this.rightOperandValue = rightOperandValue;
	}
}
