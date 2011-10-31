package net.smartworks.server.engine.common.model;

import java.util.Date;

import net.smartworks.server.util.CommonUtil;
import net.smartworks.server.util.DateUtil;
import net.smartworks.server.util.XmlUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class SmartObject extends BaseObject {
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(SmartObject.class);
	
	private static final String NAME = CommonUtil.toName(SmartObject.class, PREFIX);
	public static final String A_COMPANYID = "companyId";
	public static final String A_EXPECTSTARTDATE = "expectStartDate";
	public static final String A_EXPECTENDDATE = "expectEndDate";
	public static final String A_REALSTARTDATE = "realStartDate";
	public static final String A_REALENDDATE = "realEndDate";
	
	private String companyId;
	private Date expectStartDate;
	private Date expectEndDate;
	private Date realStartDate;
	private Date realEndDate;

	public SmartObject() {
		super();
	}
	
	public Object clone() throws CloneNotSupportedException {
		try {
			return toObject(this.toString());
		} catch (Exception e) {
			logger.warn(e, e);
			return null;
		}
	}
	public SmartObject cloneNew() {
		SmartObject obj = null;
		try {
			obj = (SmartObject)this.clone();
			obj.setCompanyId(null);
		} catch (CloneNotSupportedException e) {
			logger.warn(e, e);
		}
		return obj;
	}
	
	public static BaseObject toObject(Node node, BaseObject baseObj) throws Exception {
		if (node == null)
			return null;
		
		SmartObject obj = null;
		if (baseObj == null || !(baseObj instanceof SmartObject))
			obj = new SmartObject();
		else
			obj = (SmartObject)baseObj;
		
		BaseObject.toObject(node, obj);
		
		NamedNodeMap attrMap = node.getAttributes();
		if (attrMap != null) {
			Node companyId = attrMap.getNamedItem(A_COMPANYID);
			Node expectStartDate = attrMap.getNamedItem(A_EXPECTSTARTDATE);
			Node expectEndDate = attrMap.getNamedItem(A_EXPECTENDDATE);
			Node realStartDate = attrMap.getNamedItem(A_REALSTARTDATE);
			Node realEndDate = attrMap.getNamedItem(A_REALENDDATE);
			
			if (companyId != null)
				obj.setCompanyId(companyId.getNodeValue());
			if (expectStartDate != null)
				obj.setExpectStartDate(DateUtil.toDate(expectStartDate.getNodeValue()));
			if (expectEndDate != null)
				obj.setExpectEndDate(DateUtil.toDate(expectEndDate.getNodeValue()));
			if (realStartDate != null)
				obj.setRealStartDate(DateUtil.toDate(realStartDate.getNodeValue()));
			if (realEndDate != null)
				obj.setRealEndDate(DateUtil.toDate(realEndDate.getNodeValue()));
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
		appendAttributeString(A_COMPANYID, companyId, buf);
		appendAttributeString(A_EXPECTSTARTDATE, expectStartDate, buf);
		appendAttributeString(A_EXPECTENDDATE, expectEndDate, buf);
		appendAttributeString(A_REALSTARTDATE, realStartDate, buf);
		appendAttributeString(A_REALENDDATE, realEndDate, buf);
		return buf.toString();
	}

	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public Date getExpectStartDate() {
		return expectStartDate;
	}
	public void setExpectStartDate(Date expectStartDate) {
		this.expectStartDate = expectStartDate;
	}
	public Date getExpectEndDate() {
		return expectEndDate;
	}
	public void setExpectEndDate(Date expectEndDate) {
		this.expectEndDate = expectEndDate;
	}
	public Date getRealStartDate() {
		return realStartDate;
	}
	public void setRealStartDate(Date realStartDate) {
		this.realStartDate = realStartDate;
	}
	public Date getRealEndDate() {
		return realEndDate;
	}
	public void setRealEndDate(Date realEndDate) {
		this.realEndDate = realEndDate;
	}
}
