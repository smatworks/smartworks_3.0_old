package net.smartworks.server.engine.common.model;

import net.smartworks.server.util.CommonUtil;
import net.smartworks.server.util.XmlUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class ClassObject extends SmartObject {
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(ClassObject.class);
	
	private static final String NAME = CommonUtil.toName(ClassObject.class, PREFIX);
	public static final String A_OBJID = "objId";
	
	private String objId;

	public ClassObject() {
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
	public ClassObject cloneNew() {
		ClassObject obj = null;
		try {
			obj = (ClassObject)this.clone();
			obj.setObjId(null);
		} catch (CloneNotSupportedException e) {
			logger.warn(e, e);
		}
		return obj;
	}
	
	public static BaseObject toObject(Node node, BaseObject baseObj) throws Exception {
		if (node == null)
			return null;
		
		ClassObject obj = null;
		if (baseObj == null || !(baseObj instanceof ClassObject))
			obj = new ClassObject();
		else
			obj = (ClassObject)baseObj;
		
		SmartObject.toObject(node, obj);
		
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
	public String toLiteString(String name, String tab) {
		if (name == null || name.trim().length() == 0)
			name = NAME;
		return super.toLiteString(name, tab);
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
	public void setObjId(String objId) {
		this.objId = objId;
	}

}
