package net.smartworks.server.engine.common.model;

import net.smartworks.server.util.CommonUtil;
import net.smartworks.server.util.XmlUtil;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class SmartObjectCond extends Cond {
	private static final long serialVersionUID = 1L;

	private static final String NAME = CommonUtil.toName(SmartObjectCond.class, PREFIX);
	private static final String A_COMPANYID = "companyId";
	
	private String companyId;
	public SmartObjectCond() {
		super();
	}
	public static BaseObject toObject(Node node, BaseObject baseObj) throws Exception {
		if (node == null)
			return null;
		
		SmartObjectCond obj = null;
		if (baseObj == null || !(baseObj instanceof SmartObjectCond))
			obj = new SmartObjectCond();
		else
			obj = (SmartObjectCond)baseObj;
		
		Cond.toObject(node, obj);
		
		NamedNodeMap attrMap = node.getAttributes();
		if (attrMap != null) {
			Node companyId = attrMap.getNamedItem(A_COMPANYID);
			if (companyId != null)
				obj.setCompanyId(companyId.getNodeValue());
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
	public String toAttributesString() {
		StringBuffer buf = new StringBuffer();
		buf.append(super.toAttributesString());
		appendAttributeString(A_COMPANYID, companyId, buf);
		return buf.toString();
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
}
