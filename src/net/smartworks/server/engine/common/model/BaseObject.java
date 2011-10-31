package net.smartworks.server.engine.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import net.smartworks.server.util.CommonUtil;
import net.smartworks.server.util.DateUtil;
import net.smartworks.server.util.ListMap;
import net.smartworks.server.util.XmlUtil;

public class BaseObject implements Serializable {
	private static final long serialVersionUID = 1L;

	protected static final String EMPTY = CommonUtil.EMPTY;
	protected static final String SPACE = CommonUtil.SPACE;
	protected static final String EQUAL = CommonUtil.EQUAL;
	protected static final String COLON = CommonUtil.COLON;
	protected static final String RN = CommonUtil.RN;
	protected static final String TAB = CommonUtil.TAB;
	protected static final String ENCODING_UTF8 = XmlUtil.ENCODING_UTF8;
	protected static final String XMLNS = XmlUtil.XMLNS;
	protected static final String CDATA_OPEN = XmlUtil.CDATA_OPEN;
	protected static final String CDATA_CLOSE = XmlUtil.CDATA_CLOSE;
	protected static final String PREFIX = EMPTY;
	private static final String EXT = "ext_";
	
	private static final String NAME = CommonUtil.toName(BaseObject.class, "Base");
	
	private boolean extAttrsFlag = false;
	private Property[] extendedAttributes;
	private ListMap extAttrMap = new ListMap();
	
	public String NAME() {
		return NAME;
	}
	public static BaseObject toObject(Node node, BaseObject baseObj) throws Exception {
		if (node == null)
			return null;
		
		BaseObject obj = null;
		if (baseObj == null)
			obj = new BaseObject();
		else
			obj = baseObj;
		
		NamedNodeMap attrMap = node.getAttributes();
		if (attrMap != null) {
			for (int i=0; i<attrMap.getLength(); i++) {
				Node extAttr = attrMap.item(i);
				String extAttrName = extAttr.getNodeName();
				if (!extAttrName.startsWith(EXT))
					continue;
				extAttrName = StringUtils.replace(extAttrName, EXT, EMPTY);
				obj.setExtendedAttributeValue(extAttrName, extAttr.getNodeValue());
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
	public Node toNode() throws Exception {
		return toNode(null, false);
	}
	public Node toNode(String name, boolean lite) throws Exception {
		return XmlUtil.toDocument(toString(name, null, lite)).getDocumentElement();
	}
	public int hashCode() {
		int res = super.hashCode();
		return res;
	}
	public String toString() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + this.toString(null, null);
	}
	public String toLiteString() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + this.toLiteString(null, null);
	}
	public String toString(String name, String tab) {
		return toString(name, tab, false);
	}
	public String toLiteString(String name, String tab) {
		return toString(name, tab, true);
	}
	protected String toString(String name, String tab, boolean lite) {
		return toString (null, name, null, tab, lite);
	}
	protected String toString(String ns, String name, Map nsMap, String tab, boolean lite) {
		if (name == null || name.trim().length() == 0)
			name = NAME();
		if (tab == null)
			tab = EMPTY;
		String attrStr = toAttributesString();
		String tab2 = tab + TAB;
		String nsElemStr = toElementsString(tab2, lite, nsMap);
		String elemStrLite = toElementsString(tab2, lite);
		String elemStr = toElementsString(tab2);
		boolean isAttrEmpty = attrStr == null || attrStr.trim().length() == 0;
		boolean isNsElemEmpty = nsElemStr == null || nsElemStr.trim().length() == 0;
		boolean isElemLiteEmpty = elemStrLite == null || elemStrLite.trim().length() == 0;
		boolean isElemEmpty = elemStr == null || elemStr.trim().length() == 0;
		StringBuffer buf = new StringBuffer();
		buf.append(tab).append("<");
		String prefix = appendNamespacePrefix(ns, nsMap, buf);
		buf.append(name);
		if (!isAttrEmpty)
			buf.append(attrStr);
		appendNamespace(ns, prefix, nsMap, buf);
		if (isNsElemEmpty && isElemLiteEmpty && isElemEmpty) {
			buf.append("/>");
			return buf.toString();
		}
		buf.append(">");
		if (!isNsElemEmpty) {
			if (nsElemStr.startsWith("<") && !nsElemStr.startsWith(CDATA_OPEN))
				buf.append(RN).append(tab2);
			buf.append(nsElemStr);
			if (isElemEmpty && isElemLiteEmpty && nsElemStr.endsWith(">") && !nsElemStr.endsWith(CDATA_CLOSE))
				buf.append(RN).append(tab);
		}
		if (!isElemLiteEmpty) {
			if (elemStrLite.startsWith("<") && !elemStrLite.startsWith(CDATA_OPEN))
				buf.append(RN).append(tab2);
			buf.append(elemStrLite);
			if (isElemEmpty && elemStrLite.endsWith(">") && !elemStrLite.endsWith(CDATA_CLOSE))
				buf.append(RN).append(tab);
		}
		if (!isElemEmpty) {
			if (elemStr.startsWith("<") && !elemStr.startsWith(CDATA_OPEN))
				buf.append(RN).append(tab2);
			buf.append(elemStr);
			if (elemStr.endsWith(">") && !elemStr.endsWith(CDATA_CLOSE))
				buf.append(RN).append(tab);
		}
		buf.append("</").append(name).append(">");;
		return buf.toString();
	}
	protected String toAttributesString() {
		return toAttributesString(this.getExtendedAttributes(), EXT);
	}
	protected static String toAttributesString(Property[] attrs, String prefix) {
		if (CommonUtil.isEmpty(attrs))
			return EMPTY;
		StringBuffer buf = new StringBuffer();
		appendAttributesString(attrs, prefix, buf);
		return buf.toString();
	}
	protected static void appendAttributesString(Property[] attrs, String prefix, StringBuffer buf) {
		if (buf == null || CommonUtil.isEmpty(attrs))
			return;
		for (int i=0; i<attrs.length; i++) {
			Property attr = attrs[i];
			String name = attr.getName();
			String value = attr.getValue();
			if (name == null || value == null)
				continue;
			name = name.trim();
			if (name.length() == 0)
				continue;
			if (!CommonUtil.isEmpty(prefix))
				name = prefix + name;
			appendAttributeString(name, value, true, buf);
		}
	}
	protected String toElementsString(String tab) {
		return EMPTY;
	}
	protected String toElementsString(String tab, boolean lite) {
		return EMPTY;
	}
	protected String toElementsString(String tab, boolean lite, Map nsMap) {
		return EMPTY;
	}
	protected static String getNodeValue(Node node) {
		return getNodeValue(node, false);
	}
	protected static String getNodeValue(Node node, boolean hasXml) {
		short nodeType = node.getNodeType();
		if (nodeType == Node.TEXT_NODE || nodeType == Node.CDATA_SECTION_NODE)
			return toNodeValue(node, hasXml);
		
		NodeList elemList = node.getChildNodes();
		if (elemList == null || elemList.getLength() == 0)
			return EMPTY;
		if (elemList != null && elemList.getLength() != 0) {
			Node elem;
			short elemNodeType;
			for (int j=0; j<elemList.getLength(); j++) {
				elem = elemList.item(j);
				elemNodeType = elem.getNodeType();
				if (elemNodeType != Node.TEXT_NODE && elemNodeType != Node.CDATA_SECTION_NODE)
					continue;
				return toNodeValue(elem, hasXml);
			}
		}
		return EMPTY;
	}
	private static String toNodeValue(Node node, boolean hasXml) {
		String value = node.getNodeValue();
		if (hasXml && value.indexOf("&gt;") != -1)
			value = StringUtils.replace(value, "&gt;", ">");
		return value;
	}
	protected static Node[] getNodes(Node node) {
		NodeList elemList = node.getChildNodes();
		if (elemList == null || elemList.getLength() == 0)
			return null;
		List list = null;
		for (int j=0; j<elemList.getLength(); j++) {
			Node elem = elemList.item(j);
			if (elem.getNodeType() != Node.ELEMENT_NODE)
				continue;
			if (list == null)
				list = new ArrayList();
			list.add(elem);
		}
		if (list == null || list.isEmpty())
			return null;
		Node[] nodes = new Node[list.size()];
		list.toArray(nodes);
		return nodes;
	}
	protected static String toAttributeString(String name, String value) {
		return toAttributeString(name, value, false);
	}
	protected static String toAttributeString(String name, String value, boolean isPcdata) {
		if (name == null || value == null)
			return EMPTY;
		StringBuffer buf = new StringBuffer();
		appendAttributeString(name, value, isPcdata, buf);
		return buf.toString();
	}
	protected static void appendAttributeString(String name, String value, StringBuffer buf) {
		appendAttributeString(name, value, false, buf);
	}
	protected static void appendAttributeString(String name, int value, StringBuffer buf) {
		appendAttributeString(name, value+EMPTY, false, buf);
	}
	protected static void appendAttributeString(String name, long value, StringBuffer buf) {
		appendAttributeString(name, value+EMPTY, false, buf);
	}
	protected static void appendAttributeString(String name, Long value, StringBuffer buf) {
		if (value == null)
			return;
		appendAttributeString(name, CommonUtil.toLong(value), buf);
	}
	protected static void appendAttributeString(String name, double value, StringBuffer buf) {
		appendAttributeString(name, value+EMPTY, false, buf);
	}
	protected static void appendAttributeString(String name, boolean value, StringBuffer buf) {
		appendAttributeString(name, value+EMPTY, false, buf);
	}
	protected static void appendAttributeString(String name, Date value, StringBuffer buf) {
		appendAttributeString(name, DateUtil.toXsdDateTimeString(value), false, buf);
	}
	protected static void appendAttributeString(String name, String value, boolean isPcdata, StringBuffer buf) {
		if (buf == null || name == null || value == null)
			return;
		if (isPcdata)
			value = XmlUtil.encode(value);
		buf.append(SPACE).append(name).append("=\"").append(value).append("\"");
	}
	protected static String toOpeningElementString(String name, Property[] attrs, String tab) {
		StringBuffer buf = new StringBuffer();
		appendOpeningElementString(name, attrs, tab, buf);
		return buf.toString();
	}
	protected static void appendOpeningElementString(String name, Property[] attrs, String tab, StringBuffer buf) {
		if (buf == null || CommonUtil.isEmpty(name))
			return;
		buf.append(RN).append(tab).append("<").append(name);
		appendAttributesString(attrs, null, buf);
		buf.append(">");
	}
	protected static String toClosingElementString(String name, String tab) {
		StringBuffer buf = new StringBuffer();
		appendClosingElementString(name, tab, buf);
		return buf.toString();
	}
	protected static void appendClosingElementString(String name, String tab, StringBuffer buf) {
		if (buf == null || CommonUtil.isEmpty(name))
			return;
		buf.append(RN).append(tab).append("</").append(name).append(">");
	}
	protected static String toElementString(String name, Property[] attrs, String tab) {
		StringBuffer buf = new StringBuffer();
		appendElementString(name, attrs, tab, buf);
		return buf.toString();
	}
	protected static String toElementString(String name, BaseObject value, String tab) {
		if (value == null)
			return EMPTY;
		StringBuffer buf = new StringBuffer();
		appendElementString(name, value, tab, buf);
		return buf.toString();
	}
	protected static String toElementString(String name, String value, String tab) {
		return toElementString(name, value, tab, false);
	}
	protected static String toElementString(String name, String value, String tab, boolean hasXml) {
		return toElementString(name, value, null, tab, hasXml);
	}
	protected static String toElementString(String name, String value, Property[] attrs, String tab) {
		return toElementString(name, value, attrs, tab, false);
	}
	protected static String toElementString(String name, String value, Property[] attrs, String tab, boolean hasXml) {
		StringBuffer buf = new StringBuffer();
		appendElementString(name, value, attrs, tab, hasXml, buf);
		return buf.toString();
	}
	protected static String toElementString(String name, byte[] value, String tab) {
		return toElementString(name, value, tab, false);
	}
	protected static String toElementString(String name, byte[] value, String tab, boolean hasXml) {
		StringBuffer buf = new StringBuffer();
		appendElementString(name, value, tab, hasXml, buf);
		return buf.toString();
	}
	public static String toElementString(String name, Object value, String tab) {
		if (value == null)
			return EMPTY;
		if (value instanceof String)
			return toElementString(name, (String)value, tab);
		if (value instanceof BaseObject)
			return toElementString(name, (BaseObject) value, tab);
		StringBuffer buf = new StringBuffer();
		buf.append(RN).append(tab).append("<").append(name).append(">").append(value).append("</").append(name).append(">");
		return buf.toString();
	}
	public static String toElementsString(String names, String name, BaseObject[] values, String tab) {
		return toElementsString(names, name, values, tab, false);
	}
	protected static String toElementsString(String names, String name, BaseObject[] values, String tab, boolean lite) {
		StringBuffer buf = new StringBuffer();
		appendElementsString(names, name, values, tab, lite, buf);
		return buf.toString();
	}
	public static String toElementsString(String names, String name, Object[] values, String tab) {
		StringBuffer buf = new StringBuffer();
		appendElementsString(names, name, values, tab, buf);
		return buf.toString();
	}
	protected static void appendElementString(String name, Property[] attrs, String tab, StringBuffer buf) {
		if (buf == null || CommonUtil.isEmpty(name))
			return;
		buf.append(RN).append(tab).append("<").append(name);
		appendAttributesString(attrs, null, buf);
		buf.append("/>");
	}
	protected static void appendElementString(String name, BaseObject value, String tab, StringBuffer buf) {
		if (buf == null || value == null)
			return;
		buf.append(RN).append(value.toString(name, tab));
	}
	protected static void appendElementString(String name, Node value, String tab, StringBuffer buf) {
		if (buf == null || value == null)
			return;
		try {
			String nodeName = value.getNodeName();
			String nodeStr = XmlUtil.toXmlString(value).trim();
			if (!CommonUtil.isEmpty(name) && !CommonUtil.isEmpty(nodeName) && !name.equals(nodeName)) {
				nodeStr = nodeStr.replaceFirst(nodeName, name);
				if (!nodeStr.endsWith("/>")) {
					int idx = nodeStr.lastIndexOf(nodeName);
					if (idx != -1) {
						nodeStr = nodeStr.substring(0, idx) + name + ">";
					}
				}
			}
			buf.append(RN).append(nodeStr);
		} catch (Exception e) {
		}
	}
	protected static void appendElementString(String name, String value, String tab, StringBuffer buf) {
		appendElementString(null, name, value, null, null, tab, false, buf);
	}
	protected static void appendElementString(String name, String value, String tab, boolean hasXml, StringBuffer buf) {
		appendElementString(null, name, value, null, null, tab, hasXml, buf);
	}
	protected static void appendElementString(String name, String value, Property[] attrs, String tab, StringBuffer buf) {
		appendElementString(null, name, value, attrs, null, tab, false, buf);
	}
	protected static void appendElementString(String name, String value, Property[] attrs, String tab, boolean hasXml, StringBuffer buf) {
		appendElementString(null, name, value, attrs, null, tab, hasXml, buf);
	}
	protected static void appendElementString(String ns, String name, String value, Property[] attrs, Map nsMap, 
			String tab, boolean hasXml, StringBuffer buf) {
		if (buf == null || (CommonUtil.isEmpty(value) && CommonUtil.isEmpty(attrs)))
			return;
		if (name == null) {
			buf.append(XmlUtil.toCDataString(value, hasXml));
			return;
		}
		if (tab == null)
			tab = EMPTY;
		buf.append(RN).append(tab).append("<");
		String prefix = appendNamespacePrefix(ns, nsMap, buf);
		buf.append(name);
		appendAttributesString(attrs, null, buf);
		appendNamespace(ns, prefix, nsMap, buf);
		if (CommonUtil.isEmpty(value)) {
			buf.append("/>").toString();
			return;
		}
		buf.append(">");
		buf.append(XmlUtil.toCDataString(value, hasXml));
		buf.append("</").append(name).append(">");
	}
	private static String appendNamespacePrefix(String ns, Map nsMap, StringBuffer buf) {
		if (CommonUtil.isEmpty(ns))
			return null;
		String prefix = null;
		if (nsMap == null) {
			prefix = "ns";
		} else if (nsMap.containsKey(ns)) {
			prefix = (String)nsMap.get(ns);
		} else {
			prefix = "ns";
			if (nsMap.containsKey("namespacePrefixIndex")) {
				String nsPrefixNum = CommonUtil.toInt(nsMap.get("namespacePrefixIndex")) + 1 + EMPTY;
				prefix += nsPrefixNum;
			}
		}
		buf.append(prefix).append(COLON);
		return prefix;
	}
	private static void appendNamespace(String ns, String prefix, Map nsMap, StringBuffer buf) {
		if (prefix == null || CommonUtil.isEmpty(ns) || (nsMap != null && nsMap.containsKey(ns)))
			return;
		buf.append(" xmlns:").append(prefix).append("=\"").append(ns).append("\"");
		if (nsMap == null)
			return;
		nsMap.put(ns, prefix);
	}
	protected static void appendElementString(String name, byte[] value, String tab, StringBuffer buf) {
		appendElementString(name, value, tab, false, buf);
	}
	protected static void appendElementString(String name, byte[] value, String tab, boolean hasXml, StringBuffer buf) {
		if (buf == null || value == null)
			return;
		if (name == null) {
			try {
				buf.append(XmlUtil.toCDataString(new String(value, ENCODING_UTF8), hasXml));
				return;
			} catch (Exception e) {
				buf.append("Threw Exception: ").append(e.getMessage());
				return;
			}
		}
		if (tab == null)
			tab = EMPTY;
		try {
			String valueStr = new String(value, ENCODING_UTF8);
			buf.append(RN).append(tab).append("<").append(name).append(">");
			if (valueStr != null) {
				buf.append(XmlUtil.toCDataString(valueStr, hasXml));
			}
			buf.append("</").append(name).append(">");
		} catch (Exception e) {
			buf.append("Threw Exception: ").append(e.getMessage());
			return;
		}
	}
	public static void appendElementString(String name, Object value, String tab, StringBuffer buf) {
		if (buf == null || value == null)
			return;
		if (value instanceof String) {
			appendElementString(name, (String)value, tab, buf);
			return;
		} else if (value instanceof BaseObject) {
			appendElementString(name, (BaseObject)value, tab, buf);
			return;
		} else if (value instanceof Node) {
			appendElementString(name, (Node)value, tab, buf);
			return;
		}
		buf.append(RN).append(tab).append("<").append(name).append(">").append(value).append("</").append(name).append(">");
	}
	public static void appendElementsString(String names, String name, BaseObject[] values, String tab, StringBuffer buf) {
		appendElementsString(names, name, values, tab, false, buf);
	}
	protected static void appendElementsString(String names, String name, BaseObject[] values, String tab, boolean lite, StringBuffer buf) {
		appendElementsString(null, names, name, values, null, tab, lite, buf);
	}
	protected static void appendElementsString(String ns, String names, String name, BaseObject[] values, 
			Map nsMap, String tab, boolean lite, StringBuffer buf) {
		if (buf == null || values == null)
			return;
		String tab2 = tab;
		boolean hasNames = names != null && names.trim().length() != 0;
		if (hasNames) {
			buf.append(RN).append(tab).append("<");
			String prefix = appendNamespacePrefix(ns, nsMap, buf);
			buf.append(names);
			appendNamespace(ns, prefix, nsMap, buf);
			buf.append(">");
			tab2 += TAB;
		}
		if (values.length != 0) {
			for (int i=0; i<values.length; i++) {
				if (values[i] == null)
					continue;
				buf.append(RN).append(values[i].toString(ns, name, nsMap, tab2, lite));
			}
			if (hasNames)
				buf.append(RN).append(tab);
		}
		if (hasNames)
			buf.append("</").append(names).append(">");
	}
	protected static void appendElementsString(String names, String name, Object[] values, String tab, StringBuffer buf) {
		if (buf == null || values == null)
			return;
		String tab2 = tab;
		boolean hasNames = names != null && names.trim().length() != 0;
		if (hasNames) {
			buf.append(RN).append(tab).append("<").append(names).append(">");
			tab2 += TAB;
		}
		if (values.length != 0) {
			for (int i=0; i<values.length; i++)
				buf.append(toElementString(name, values[i], tab2));
			if (hasNames)
				buf.append(RN).append(tab);
		}
		if (hasNames)
			buf.append("</").append(names).append(">");
	}
	public static boolean equals(Object obj, Object obj1) {
		if (obj == null) {
			if (obj1 != null)
				return false;
		} else {
			if (obj1 == null || !obj.equals(obj1))
				return false;
		}
		return true;
	}
	public Property[] getExtendedAttributes() {
		if (!extAttrsFlag)
			return extendedAttributes;
		if (extAttrMap.isEmpty())
			return null;
		List extAttrList = extAttrMap.valueList();
		Property[] extAttrs = new Property[extAttrList.size()];
		extAttrList.toArray(extAttrs);
		this.extendedAttributes = extAttrs;
		extAttrsFlag = false;
		return extAttrs;
	}
	public void setExtendedAttributes(Property[] extAttrs) {
		this.extendedAttributes = extAttrs;
		this.extAttrMap.clear();
		if (extAttrs == null || extAttrs.length == 0)
			return;
		for (int i=0; i<extAttrs.length; i++) {
			Property extAttr = extAttrs[i];
			String name = extAttr.getName();
			extAttrMap.put(name, extAttr);
		}
		extAttrsFlag = false;
	}
	public String getExtendedAttributeValue(String name) {
		if (!extAttrMap.containsKey(name))
			return null;
		Property extProp = (Property)extAttrMap.get(name);
		String value = extProp.getValue();
		return value;
	}
	public void setExtendedAttributeValue(String name, String value) {
		this.extAttrsFlag = true;
		if (name == null)
			return;
		if (value == null && extAttrMap.containsKey(name)) {
			extAttrMap.remove(name);
			return;
		}
		extAttrMap.put(name, new Property(name, value));
	}
}
