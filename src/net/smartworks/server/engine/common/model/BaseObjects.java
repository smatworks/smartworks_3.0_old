package net.smartworks.server.engine.common.model;

import net.smartworks.server.engine.common.exception.MisException;
import net.smartworks.server.util.CommonUtil;
import net.smartworks.server.util.XmlUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class BaseObjects extends BaseObject {
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(BaseObjects.class);
	
	private static final String NAME = CommonUtil.toName(BaseObjects.class, PREFIX);
	public static final String A_OBJID = "objId";
	
	private BaseObject[] objects;

	public BaseObjects() {
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
	public static BaseObject toObject(Node node, BaseObject baseObj) throws Exception {
		if (node == null)
			return null;
		throw new MisException("Not implemented yet.");
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
	public String toElementsString(String tab) {
		StringBuffer buf = new StringBuffer();
		buf.append(super.toElementsString(tab));
		appendElementsString(null, null, getObjects(), tab, buf);
		return buf.toString();
	}
	public BaseObject[] getObjects() {
		return objects;
	}
	public void setObjects(BaseObject[] objects) {
		this.objects = objects;
	}
}
