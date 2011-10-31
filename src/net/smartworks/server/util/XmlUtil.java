package net.smartworks.server.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class XmlUtil {
	public static final String ENCODING_UTF8 = "UTF-8";
	public static final String ENCODING_EUCKR = "EUC-KR";
	public static final String XS_NAMESPACE = "http://www.w3.org/2001/XMLSchema";
	public static final String XMLNS = "xmlns";
	public static final String CDATA_OPEN = "<![CDATA[";
	public static final String CDATA_CLOSE = "]]>";
	private static final String EMPTY = CommonUtil.EMPTY;
	private static final String RN = CommonUtil.RN;

	public XmlUtil() {
		super();
	}
	//아래 method를 사용하고 있는 곳에 exception을 주기 위한 kmyu
	public static String toWrappedString(String name, Object[] objs, boolean kmyu) throws Exception {
		StringBuffer buf = new StringBuffer();
		buf.append("<").append(name).append(">");
		if (objs != null && objs.length != 0) {
			for (int i=0; i<objs.length; i++) {
				Object obj = objs[i];
				buf.append(RN);
				buf.append(obj.toString());
			}
			buf.append(RN);
		}
		buf.append("</").append(name).append(">");
		return buf.toString();
	}
//	public static String toWrappedString(String name, BaseObject[] objs) throws Exception {
//		StringBuffer buf = new StringBuffer();
//		buf.append("<").append(name).append(">");
//		if (objs != null && objs.length != 0) {
//			for (int i=0; i<objs.length; i++) {
//				BaseObject obj = objs[i];
//				buf.append(RN);
//				buf.append(obj.toString(null, "\t"));
//			}
//			buf.append(RN);
//		}
//		buf.append("</").append(name).append(">");
//		return buf.toString();
//	}
	public static String toXmlString(Document doc) throws Exception {
		return toXmlString((Node)doc);
	}
	public static String toXmlString(Node doc) throws Exception {
		if (doc == null)
			return null;
		TransformerFactory transFac = TransformerFactory.newInstance();
		Transformer trans = transFac.newTransformer();
		trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		trans.setOutputProperty(OutputKeys.INDENT, "yes");
		Source src = new DOMSource(doc);
		StringWriter writer = new StringWriter();
		StreamResult res = new StreamResult(writer);
		trans.transform(src, res);
		String xmlString = writer.toString();
		return xmlString;
	}
	public static String toXmlString(String str) {
		return toXmlString(str, null);
	}
	public static String toXmlString(String str, String encoding) {
		if (str == null)
			str = "<null/>";
		if (encoding == null || encoding.trim().length() == 0)
			encoding = ENCODING_UTF8;
		str = str.trim();
		while (str.startsWith(RN)) {
			str.replaceFirst(RN, EMPTY);
			str.trim();
		}
		if (!str.startsWith("<?xml")) {
			str = new StringBuffer("<?xml version=\"1.0\" encoding=\"").append(encoding).append("\"?>\r\n").append(str).toString();
		}
		return str;
	}
	public static String toCDataString(String str) {
		return toCDataString(str, false);
	}
	public static String toCDataString(String str, boolean hasXml) {
		if (str == null || str.startsWith(CDATA_OPEN))
			return str;
		if (hasXml && str.indexOf(CDATA_CLOSE) != -1)
			str = StringUtils.replace(str, CDATA_CLOSE, "]]&gt;");
		return new StringBuffer().append(CDATA_OPEN).append(str).append(CDATA_CLOSE).toString();
	}
	public static String encode(String str) {
		str = StringUtils.replace(str, "&", "&amp;");
		str = StringUtils.replace(str, "<", "&lt;");
		str = StringUtils.replace(str, ">", "&gt;");
		str = StringUtils.replace(str, "\"", "&quot;");
		str = StringUtils.replace(str, "'", "&apos;");
		return str;
	}
	public static String decode(String str) {
		str = StringUtils.replace(str, "&apos;", "'");
		str = StringUtils.replace(str, "&quot;", "\"");
		str = StringUtils.replace(str, "&gt;", ">");
		str = StringUtils.replace(str, "&lt;", "<");
		str = StringUtils.replace(str, "&amp;", "&");
		return str;
	}
	public static Node toNode(String str) throws Exception {
		return toDocument(str).getDocumentElement();
	}
	public static Document toDocument(String str) throws Exception {
		return toDocument(str, null);
	}
	public static Document toDocument(String str, String encoding) throws Exception {
		if (str == null)
			return null;
		str = str.trim();
		if (str.startsWith("<?xml")) {
			if (str.indexOf("encoding=\"UTF-8\"") != -1 || str.indexOf("encoding=\"utf-8\"") != -1) {
				encoding = ENCODING_UTF8;
			} else if (str.indexOf("encoding=\"EUC-KR\"") != -1 || str.indexOf("encoding=\"euc-kr\"") != -1) {
				encoding = ENCODING_EUCKR;
			} else if (CommonUtil.isEmpty(encoding)) {
				encoding = ENCODING_UTF8;
			}
		} else {
			if (CommonUtil.isEmpty(encoding))
				encoding = ENCODING_UTF8;
		}
		str = toXmlString(str, encoding);
		byte[] bytes = str.getBytes(encoding);
		InputStream is = new ByteArrayInputStream(bytes);
		Document doc = toDocument(is);
		return doc;
	}
	public static Document toDocument(URL url) throws Exception {
		if (url == null)
			return null;
		InputStream is = url.openStream();
		Document doc = toDocument(is);
		return doc;
	}
	public static Document toDocument(InputStream is) throws Exception {
		if (is == null)
			return null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		dbf.setValidating(false);
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(is);
		return doc;
	}
}
