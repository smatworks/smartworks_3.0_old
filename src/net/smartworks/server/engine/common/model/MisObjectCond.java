package net.smartworks.server.engine.common.model;

import java.util.Date;

import net.smartworks.server.util.CommonUtil;
import net.smartworks.server.util.DateUtil;
import net.smartworks.server.util.XmlUtil;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MisObjectCond extends ClassObjectCond{
	private static final long serialVersionUID = 1L;
	
	protected static final String PREFIX = "Mis";
	private static final String NAME = CommonUtil.toName(MisObjectCond.class, PREFIX);

	public static final String A_NAME = "name";
	public static final String A_NAMELIKE = "nameLike";
	public static final String A_CREATIONUSER = "creationUser";
	public static final String A_CREATIONDATE = "creationDate";
	public static final String A_CREATIONDATEFROM = "creationDateFrom";
	public static final String A_CREATIONDATETO = "creationDateTo";
	public static final String A_MODIFICATIONUSER = "modificationUser";
	public static final String A_MODIFICATIONDATE = "modificationDate";
	public static final String A_MODIFICATIONDATEFROM = "modificationDateFrom";
	public static final String A_MODIFICATIONDATETO = "modificationDateTo";
	public static final String A_DESCRIPTION = "description";
	public static final String A_DESCRIPTIONLIKE = "descriptionLike";
	public static final String A_STATUS = "status";
	public static final String A_STATUSIN = "statusIn";
	public static final String A_STATUSINS = "statusIns";
	public static final String A_STATUSNOTIN = "statusNotIn";
	public static final String A_STATUSNOTINS = "statusNotIns";
	public static final String A_EXTENDEDPROPERTY = "extendedProperty";
	public static final String A_EXTENDEDPROPERTIES = "extendedProperties";
	
	private String name = null;
	private String nameLike = null;
	private String creationUser = null;
	private Date creationDate = null;
	private Date creationDateFrom = null;
	private Date creationDateTo = null;
	private String modificationUser = null;
	private Date modificationDate = null;
	private Date modificationDateFrom = null;
	private Date modificationDateTo = null;
	private String description;
	private String descriptionLike;
	private String status;
	
	private String[] statusIns;
	private String[] statusNotIns;
	private Property[] extendedProperties;
	
	public MisObjectCond() {
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
		appendAttributeString(A_CREATIONUSER, creationUser, buf);
		appendAttributeString(A_CREATIONDATE, creationDate, buf);
		appendAttributeString(A_CREATIONDATEFROM, creationDateFrom, buf);
		appendAttributeString(A_CREATIONDATETO, creationDateTo, buf);
		appendAttributeString(A_MODIFICATIONUSER, modificationUser, buf);
		appendAttributeString(A_MODIFICATIONDATE, modificationDate, buf);
		appendAttributeString(A_MODIFICATIONDATEFROM, modificationDateFrom, buf);
		appendAttributeString(A_MODIFICATIONDATETO, modificationDateTo, buf);
		appendAttributeString(A_STATUS, status, buf);
		return buf.toString();
	}
	public String toElementsString(String tab) {
		StringBuffer buf = new StringBuffer();
		buf.append(super.toElementsString(tab));
		appendElementString(A_NAME, getName(), tab, buf);
		appendElementString(A_NAMELIKE, getNameLike(), tab, buf);
		appendElementString(A_DESCRIPTION, getDescription(), tab, true, buf);
		appendElementString(A_DESCRIPTIONLIKE, getDescriptionLike(), tab, true, buf);
		appendElementsString(A_STATUSINS, A_STATUSIN, getStatusIns(), tab, buf);
		appendElementsString(A_STATUSNOTINS, A_STATUSNOTIN, getStatusNotIns(), tab, buf);
		appendElementsString(A_EXTENDEDPROPERTIES, A_EXTENDEDPROPERTY, extendedProperties, tab, buf);
		return buf.toString();
	}
	public static BaseObject toObject(Node node, BaseObject baseObj) throws Exception {
		if (node == null)
			return null;
		
		MisObjectCond obj = null;
		if (baseObj == null || !(baseObj instanceof MisObjectCond))
			obj = new MisObjectCond();
		else
			obj = (MisObjectCond)baseObj;
		
		// �θ� attributes, elements �� ����
		ClassObjectCond.toObject(node, obj);
		
		// attribute �� ����
		NamedNodeMap attrMap = node.getAttributes();
		if (attrMap != null) {
			Node creationUser = attrMap.getNamedItem(A_CREATIONUSER);
			Node creationDate = attrMap.getNamedItem(A_CREATIONDATE);
			Node creationDateFrom = attrMap.getNamedItem(A_CREATIONDATEFROM);
			Node creationDateTo = attrMap.getNamedItem(A_CREATIONDATETO);
			Node modificationUser = attrMap.getNamedItem(A_MODIFICATIONUSER);
			Node modificationDate = attrMap.getNamedItem(A_MODIFICATIONDATE);
			Node modificationDateFrom = attrMap.getNamedItem(A_MODIFICATIONDATEFROM);
			Node modificationDateTo = attrMap.getNamedItem(A_MODIFICATIONDATETO);
			Node status = attrMap.getNamedItem(A_STATUS);
			if (creationUser != null)
				obj.setCreationUser(creationUser.getNodeValue());
			if (creationDate != null)
				obj.setCreationDate(DateUtil.toDate(creationDate.getNodeValue()));
			if (creationDateFrom != null)
				obj.setCreationDateFrom(DateUtil.toDate(creationDateFrom.getNodeValue()));
			if (creationDateTo != null)
				obj.setCreationDateTo(DateUtil.toDate(creationDateTo.getNodeValue()));
			if (modificationUser != null)
				obj.setModificationUser(modificationUser.getNodeValue());
			if (modificationDate != null)
				obj.setModificationDate(DateUtil.toDate(modificationDate.getNodeValue()));
			if (modificationDateFrom != null)
				obj.setModificationDateFrom(DateUtil.toDate(modificationDateFrom.getNodeValue()));
			if (modificationDateTo != null)
				obj.setModificationDateTo(DateUtil.toDate(modificationDateTo.getNodeValue()));
			if (status != null)
				obj.setStatus(status.getNodeValue());
		}
		
		// elements �� ����
		NodeList childNodeList = node.getChildNodes();
		if (childNodeList == null || childNodeList.getLength() == 0)
			return obj;
		for (int i=0; i<childNodeList.getLength(); i++) {
			Node childNode = childNodeList.item(i);
			if (childNode.getNodeType() != Node.ELEMENT_NODE || childNode.getNodeName() == null)
				continue;
			if (childNode.getNodeName().equals(A_NAME)) {
				obj.setName(getNodeValue(childNode));
			} else if (childNode.getNodeName().equals(A_NAMELIKE)) {
				obj.setNameLike(getNodeValue(childNode));
			} else if (childNode.getNodeName().equals(A_DESCRIPTION)) {
				obj.setDescription(getNodeValue(childNode, true));
			} else if (childNode.getNodeName().equals(A_DESCRIPTIONLIKE)) {
				obj.setDescriptionLike(getNodeValue(childNode, true));
			} else if (childNode.getNodeName().equals(A_STATUSINS)) {
				Node[] nodes = getNodes(childNode);
				if (nodes == null || nodes.length == 0)
					continue;
				String[] objs = new String[nodes.length];
				for (int j=0; j<nodes.length; j++)
					objs[j] = nodes[j].getNodeValue();
				obj.setStatusIns(objs);
			} else if (childNode.getNodeName().equals(A_STATUSNOTINS)) {
				Node[] nodes = getNodes(childNode);
				if (nodes == null || nodes.length == 0)
					continue;
				String[] objs = new String[nodes.length];
				for (int j=0; j<nodes.length; j++)
					objs[j] = nodes[j].getNodeValue();
				obj.setStatusNotIns(objs);
			} else if (childNode.getNodeName().equals(A_EXTENDEDPROPERTIES)) {
				Node[] nodes = getNodes(childNode);
				if (nodes == null || nodes.length == 0)
					continue;
				Property[] objs = new Property[nodes.length];
				for (int j=0; j<nodes.length; j++)
					objs[j] = (Property)Property.toObject(nodes[j], null);
				obj.setExtendedProperties(objs);
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameLike() {
		return nameLike;
	}
	public void setNameLike(String nameLike) {
		this.nameLike = nameLike;
	}
	public Property[] getExtendedProperties() {
		return extendedProperties;
	}
	public void setExtendedProperties(Property[] extendedProperties) {
		this.extendedProperties = extendedProperties;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescriptionLike() {
		return descriptionLike;
	}
	public void setDescriptionLike(String descriptionLike) {
		this.descriptionLike = descriptionLike;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String[] getStatusIns() {
		return statusIns;
	}
	public void setStatusIns(String[] statusIns) {
		this.statusIns = statusIns;
	}
	public String[] getStatusNotIns() {
		return statusNotIns;
	}
	public void setStatusNotIns(String[] statusNotIns) {
		this.statusNotIns = statusNotIns;
	}
	public Date getCreationDateFrom() {
		return creationDateFrom;
	}
	public void setCreationDateFrom(Date creationDateFrom) {
		this.creationDateFrom = creationDateFrom;
	}
	public Date getCreationDateTo() {
		return creationDateTo;
	}
	public void setCreationDateTo(Date creationDateTo) {
		this.creationDateTo = creationDateTo;
	}
	public Date getModificationDateFrom() {
		return modificationDateFrom;
	}
	public void setModificationDateFrom(Date modificationDateFrom) {
		this.modificationDateFrom = modificationDateFrom;
	}
	public Date getModificationDateTo() {
		return modificationDateTo;
	}
	public void setModificationDateTo(Date modificationDateTo) {
		this.modificationDateTo = modificationDateTo;
	}
}
