package net.smartworks.server.engine.common.model;

import net.smartworks.server.util.CommonUtil;
import net.smartworks.server.util.XmlUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Cond extends Filters {
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(Cond.class);
	
	private static final String NAME = CommonUtil.toName(Cond.class, PREFIX);
	
	public static final String A_CONDID = "condId";
	public static final String A_CONDNAME = "condName";
	public static final String A_CONDDESCRIPTION = "condDescription";
	public static final String A_PAGENO = "pageNo";
	public static final String A_PAGESIZE = "pageSize";
	public static final String A_ORDERS = "orders";
	public static final String A_ORDER = "order";

	private String condId;
	private String condName;
	private String condDescription;
	private int pageNo = 0;
	private int pageSize = -1;

	private Order[] orders;

	public Cond() {
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
		appendAttributeString(A_CONDID, condId, buf);
		appendAttributeString(A_CONDNAME, condName, true, buf);
		appendAttributeString(A_PAGENO, pageNo, buf);
		appendAttributeString(A_PAGESIZE, pageSize, buf);
		return buf.toString();
	}
	public String toElementsString(String tab, boolean lite) {
		StringBuffer buf = new StringBuffer();
		buf.append(super.toElementsString(tab, lite));
		if (lite) {
			appendElementsString(null, A_ORDER, getOrders(), tab, lite, buf);
		} else {
			appendElementsString(A_ORDERS, A_ORDER, getOrders(), tab, lite, buf);
		}
		return buf.toString();
	}
	public static BaseObject toObject(Node node, BaseObject baseObj) throws Exception {
		if (node == null)
			return null;
		
		Cond obj = null;
		if (baseObj == null || !(baseObj instanceof Cond))
			obj = new Cond();
		else
			obj = (Cond)baseObj;
		
		Filters.toObject(node, obj);
		
		NamedNodeMap attrMap = node.getAttributes();
		if (attrMap != null) {
			Node condId = attrMap.getNamedItem(A_CONDID);
			Node condName = attrMap.getNamedItem(A_CONDNAME);
			Node pageNo = attrMap.getNamedItem(A_PAGENO);
			Node pageSize = attrMap.getNamedItem(A_PAGESIZE);
			if (condId != null)
				obj.setCondId(condId.getNodeValue());
			if (condName != null)
				obj.setCondName(condName.getNodeValue());
			if (pageNo != null)
				obj.setPageNo(Integer.parseInt(pageNo.getNodeValue()));
			if (pageSize != null)
				obj.setPageSize(Integer.parseInt(pageSize.getNodeValue()));
		}
		
		NodeList childNodeList = node.getChildNodes();
		if (childNodeList == null || childNodeList.getLength() == 0)
			return obj;
		for (int i=0; i<childNodeList.getLength(); i++) {
			Node childNode = childNodeList.item(i);
			if (childNode.getNodeType() != Node.ELEMENT_NODE || childNode.getNodeName() == null)
				continue;
			if (childNode.getNodeName().equals(A_CONDDESCRIPTION)) {
				obj.setCondDescription(getNodeValue(childNode, true));
			} else if (childNode.getNodeName().equals(A_ORDERS)) {
				Node[] nodes = getNodes(childNode);
				if (nodes == null || nodes.length == 0)
					continue;
				Order[] objs = new Order[nodes.length];
				for (int j=0; j<nodes.length; j++)
					objs[j] = (Order)Order.toObject(nodes[j], null);
				obj.setOrders(objs);
			} else if (childNode.getNodeName().equals(A_ORDER)) {
				obj.addOrder((Order)Order.toObject(childNode, null));
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
	public static Cond[] add(Cond[] objs, Cond obj) {
		if (obj == null)
			return objs;
		int size = 0;
		if (objs != null)
			size = objs.length;
		Cond[] newObjs = new Cond[size+1];
		int i;
		for (i=0; i<size; i++)
			newObjs[i] = objs[i];
		newObjs[i] = obj;
		return newObjs;
	}
	public static Cond[] remove(Cond[] objs, Cond obj) {
		if (obj == null)
			return objs;
		int size = 0;
		if (objs != null)
			size = objs.length;
		if (size == 0)
			return objs;
		Cond[] newObjs = new Cond[size-1];
		int i;
		int j = 0;
		for (i=0; i<size; i++) {
			if (objs[i].equals(obj))
				continue;
			newObjs[j++] = objs[i];
		}
		return newObjs;
	}
	public static Cond[] left(Cond[] objs, Cond obj) {
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
		Cond[] newObjs = new Cond[objs.length];
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
	public static Cond[] right(Cond[] objs, Cond obj) {
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
		Cond[] newObjs = new Cond[objs.length];
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
	public void addOrder(Order order) {
		if (order == null)
			return;
		this.setOrders(Order.add(this.getOrders(), order));
	}

	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Order[] getOrders() {
		return orders;
	}
	public void setOrders(Order[] orders) {
		this.orders = orders;
	}
	public String getCondId() {
		return condId;
	}
	public void setCondId(String condId) {
		this.condId = condId;
	}
	public String getCondName() {
		return condName;
	}
	public void setCondName(String condName) {
		this.condName = condName;
	}
	public String getCondDescription() {
		return condDescription;
	}
	public void setCondDescription(String condDescription) {
		this.condDescription = condDescription;
	}
}
