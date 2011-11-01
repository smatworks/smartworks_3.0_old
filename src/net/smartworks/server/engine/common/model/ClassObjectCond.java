package net.smartworks.server.engine.common.model;

import net.smartworks.server.util.CommonUtil;
import net.smartworks.server.util.XmlUtil;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class ClassObjectCond extends SmartObjectCond {
	private static final long serialVersionUID = 1L;

	private static final String NAME = CommonUtil.toName(ClassObjectCond.class, PREFIX);
	private static final String A_OBJID = "objId";
	
	private String objId;
	public ClassObjectCond() {
		super();
	}
	public static BaseObject toObject(Node node, BaseObject baseObj) throws Exception {
		if (node == null)
			return null;
		
		ClassObjectCond obj = null;
		if (baseObj == null || !(baseObj instanceof ClassObjectCond))
			obj = new ClassObjectCond();
		else
			obj = (ClassObjectCond)baseObj;
		
		SmartObjectCond.toObject(node, obj);
		
		NamedNodeMap attrMap = node.getAttributes();
		if (attrMap != null) {
			Node objId = attrMap.getNamedItem(A_OBJID);
			if (objId != null)
				obj.setObjId(objId.getNodeValue());
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
		appendAttributeString(A_OBJID, objId, buf);
		return buf.toString();
	}
	public String getObjId() {
		return objId;
	}
	public void setObjId(String modeId) {
		this.objId = modeId;
	}
}
