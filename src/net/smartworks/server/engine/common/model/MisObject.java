package net.smartworks.server.engine.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.smartworks.server.util.CommonUtil;
import net.smartworks.server.util.DateUtil;
import net.smartworks.server.util.ListMap;
import net.smartworks.server.util.XmlUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MisObject extends ClassObject {
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(MisObject.class);

	protected static final String PREFIX = "Mis";
	private static final String NAME = CommonUtil.toName(MisObject.class, PREFIX);
	public static final String A_NAME = "name";
	public static final String A_CREATIONUSER = "creationUser";
	public static final String A_CREATIONDATE = "creationDate";
	public static final String A_MODIFICATIONUSER = "modificationUser";
	public static final String A_MODIFICATIONDATE = "modificationDate";
	public static final String A_EXTENDEDPROPERTY = "extendedProperty";
	public static final String A_EXTENDEDPROPERTIES = "extendedProperties";
	public static final String A_DESCRIPTION = "description";
	public static final String A_STATUS = "status";

	private String name;
	private String creationUser;
	private Date creationDate;
	private String modificationUser;
	private Date modificationDate;
	private String description;
	private String status;
	
	private boolean extPropsFlag = false;
	private Property[] extendedProperties;
	private ListMap extPropMap = new ListMap();
	
	public MisObject() {
		super();
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
		appendAttributeString(A_NAME, name, true, buf);
		appendAttributeString(A_CREATIONUSER, creationUser, buf);
		appendAttributeString(A_CREATIONDATE, creationDate, buf);
		appendAttributeString(A_MODIFICATIONUSER, modificationUser, buf);
		appendAttributeString(A_MODIFICATIONDATE, modificationDate, buf);
		appendAttributeString(A_STATUS, status, buf);
		return buf.toString();
	}
	public String toElementsString(String tab, boolean lite) {
		StringBuffer buf = new StringBuffer();
		buf.append(super.toElementsString(tab, lite));
		appendElementString(A_DESCRIPTION, getDescription(), tab, true, buf);
		if (lite) {
			appendElementsString(null, A_EXTENDEDPROPERTY, getExtendedProperties(), tab, lite, buf);
		} else {
			appendElementsString(A_EXTENDEDPROPERTIES, A_EXTENDEDPROPERTY, getExtendedProperties(), tab, lite, buf);
		}
		return buf.toString();
	}
	public static BaseObject toObject(Node node, BaseObject baseObj) throws Exception {
		if (node == null)
			return null;
		
		MisObject obj = null;
		if (baseObj == null || !(baseObj instanceof MisObject))
			obj = new MisObject();
		else
			obj = (MisObject)baseObj;
		
		ClassObject.toObject(node, obj);
		
		NamedNodeMap attrMap = node.getAttributes();
		if (attrMap != null) {
			Node name = attrMap.getNamedItem(A_NAME);
			Node creationUser = attrMap.getNamedItem(A_CREATIONUSER);
			Node creationDate = attrMap.getNamedItem(A_CREATIONDATE);
			Node modificationUser = attrMap.getNamedItem(A_MODIFICATIONUSER);
			Node modificationDate = attrMap.getNamedItem(A_MODIFICATIONDATE);
			Node status = attrMap.getNamedItem(A_STATUS);
			if (name != null)
				obj.setName(name.getNodeValue());
			if (creationUser != null)
				obj.setCreationUser(creationUser.getNodeValue());
			if (creationDate != null)
				obj.setCreationDate(DateUtil.toDate(creationDate.getNodeValue()));
			if (modificationUser != null)
				obj.setModificationUser(modificationUser.getNodeValue());
			if (modificationDate != null)
				obj.setModificationDate(DateUtil.toDate(modificationDate.getNodeValue()));
			if (status != null)
				obj.setStatus(status.getNodeValue());
		}
		
		NodeList childNodeList = node.getChildNodes();
		if (childNodeList == null || childNodeList.getLength() == 0)
			return obj;
		for (int i=0; i<childNodeList.getLength(); i++) {
			Node childNode = childNodeList.item(i);
			if (childNode.getNodeType() != Node.ELEMENT_NODE || childNode.getNodeName() == null)
				continue;
			if (childNode.getNodeName().equals(A_NAME)) {
				obj.setName(getNodeValue(childNode));
			} else if (childNode.getNodeName().equals(A_DESCRIPTION)) {
				obj.setDescription(getNodeValue(childNode, true));
			} else if (childNode.getNodeName().equals(A_EXTENDEDPROPERTIES)) {
				Node[] nodes = getNodes(childNode);
				if (nodes == null || nodes.length == 0)
					continue;
				Property[] objs = new Property[nodes.length];
				for (int j=0; j<nodes.length; j++)
					objs[j] = (Property)Property.toObject(nodes[j], null);
				obj.setExtendedProperties(objs);
			} else if (childNode.getNodeName().equals(A_EXTENDEDPROPERTY)) {
				obj.addExtendedProperty((Property)Property.toObject(childNode, null));
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
	public static MisObject[] add(MisObject[] objs, MisObject obj) {
		if (obj == null)
			return objs;
		int size = 0;
		if (objs != null)
			size = objs.length;
		MisObject[] newObjs = new MisObject[size+1];
		int i;
		for (i=0; i<size; i++)
			newObjs[i] = objs[i];
		newObjs[i] = obj;
		return newObjs;
	}
	public static MisObject[] remove(MisObject[] objs, MisObject obj) {
		if (obj == null)
			return objs;
		int size = 0;
		if (objs != null)
			size = objs.length;
		if (size == 0)
			return objs;
		MisObject[] newObjs = new MisObject[size-1];
		int i;
		int j = 0;
		for (i=0; i<size; i++) {
			if (objs[i].equals(obj))
				continue;
			newObjs[j++] = objs[i];
		}
		return newObjs;
	}
	public static MisObject[] left(MisObject[] objs, MisObject obj) {
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
		MisObject[] newObjs = new MisObject[objs.length];
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
	public static MisObject[] right(MisObject[] objs, MisObject obj) {
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
		MisObject[] newObjs = new MisObject[objs.length];
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getCreationUser() {
		return creationUser;
	}
	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}
	public Date getModificationDate() {
		return modificationDate;
	}
	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}
	public String getModificationUser() {
		return modificationUser;
	}
	public void setModificationUser(String modificationUser) {
		this.modificationUser = modificationUser;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Property[] getExtendedProperties() {
		if (!extPropsFlag)
			return extendedProperties;
		if (extPropMap.isEmpty())
			return null;
		List extPropList = new ArrayList();
		List keyList = extPropMap.keyList();
		for (Iterator keyItr = keyList.iterator(); keyItr.hasNext();) {
			Object key = keyItr.next();
			Object value = extPropMap.get(key);
			if (value instanceof Property) {
				extPropList.add(value);
			} else if (value instanceof List) {
				List valueList = (List)value;
				for (Iterator valueItr = valueList.iterator(); valueItr.hasNext();) {
					Object extProp = valueItr.next();
					extPropList.add(extProp);
				}
			}
		}
		Property[] extProps = new Property[extPropList.size()];
		extPropList.toArray(extProps);
		this.extendedProperties = extProps;
		extPropsFlag = false;
		return extProps;
	}
	public void setExtendedProperties(Property[] extProps) {
		this.extendedProperties = extProps;
		this.extPropMap.clear();
		if (extProps == null || extProps.length == 0)
			return;
		for (int i=0; i<extProps.length; i++) {
			Property extendedProperty = extProps[i];
			String name = extendedProperty.getName();
			if (extPropMap.containsKey(name)) {
				Object extPropObj = extPropMap.get(name);
				if (extPropObj instanceof Property) {
					List extPropList = new ArrayList();
					extPropList.add(extPropObj);
					extPropList.add(extendedProperty);
					extPropMap.put(name, extPropList);
				} else if (extPropObj instanceof List) {
					List extPropList = (List)extPropObj;
					extPropList.add(extendedProperty);
				}
			} else {
				extPropMap.put(name, extendedProperty);
			}
		}
		extPropsFlag = false;
	}
	public void addExtendedProperty(Property extProp) {
		if (extProp == null)
			return;
		Property[] extProps = Property.add(this.getExtendedProperties(), extProp);
		this.setExtendedProperties(extProps);
	}
	public String getExtendedPropertyValue(String name) {
		if (!extPropMap.containsKey(name))
			return null;
		
		Object extPropObj = extPropMap.get(name);
		String value = null;
		if (extPropObj instanceof Property) {
			Property extProp = (Property)extPropObj;
			value = extProp.getValue();
		} else if (extPropObj instanceof List) {
			List extPropList = (List)extPropObj;
			if (!extPropList.isEmpty()) {
				Property extProp = (Property)extPropList.get(0);
				value = extProp.getValue();
			}
		}
		return value;
	}
	public void setExtendedPropertyValue(String name, String value) {
		this.extPropsFlag = true;
		if (name == null)
			return;
		if (CommonUtil.isEmpty(value)) {
			if (extPropMap.containsKey(name))
				extPropMap.remove(name);
			return;
		}
		extPropMap.put(name, new Property(name, value));
	}
	public String[] getExtendedPropertyValues(String name) {
		if (extPropMap.containsKey(name)) {
			Object extPropObj = extPropMap.get(name);
			String[] values = null;
			if (extPropObj instanceof Property) {
				Property extProp = (Property)extPropObj;
				values = new String[] {extProp.getValue()};
			} else if (extPropObj instanceof List) {
				List extPropList = (List)extPropObj;
				if (!extPropList.isEmpty()) {
					values = new String[extPropList.size()];
					Iterator extPropItr = extPropList.iterator();
					for (int i=0; extPropItr.hasNext(); i++) {
						Property extProp = (Property)extPropItr.next();
						values[i] = extProp.getValue();
					}
				}
			}
			return values;
		}
		return null;
	}
	public void setExtendedPropertyValues(String name, String[] values) {
		this.extPropsFlag = true;
		if (values == null || values.length == 0) {
			this.setExtendedPropertyValue(name, null);
			return;
		}
		
		if (values.length == 1) {
			this.setExtendedPropertyValue(name, values[0]);
			return;
		}
		
		List extPropList = new ArrayList();
		for (int i=0; i<values.length; i++) {
			String value = values[i];
			Property extProp = new Property(name, value);
			extPropList.add(extProp);
		}
		extPropMap.put(name, extPropList);
	}
}
