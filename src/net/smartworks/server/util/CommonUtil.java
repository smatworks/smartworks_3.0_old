package net.smartworks.server.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.net.InetAddress;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

public class CommonUtil {
	public static final String EMPTY = "";
	public static final String NULL = "null";
	public static final String SPACE = " ";
	public static final String EQUAL = "=";
	public static final String DASH = "-";
	public static final String UNDERBAR = "_";
	public static final String DOT = ".";
	public static final String COMMA = ",";
	public static final String ASTERISK = "*";
	public static final String QUESTION = "?";
	public static final String COLON = ":";
	public static final String SEMICOLON = ";";
	public static final String BAR = "|";
	public static final String SLASH = "/";
	public static final String PERCENT = "%";
	public static final String RN = "\r\n";
	public static final String TAB = "\t";
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	public static final String Y = "Y";
	public static final String N = "N";
	public static final String ON = "on";
	public static final String ONE = "1";
	public static final String ZERO = "0";
	public static final String DELIMITEROPEN = "${";
	public static final String DELIMITERCLOSE = "}";

	public static String getMyIpAddress() throws Exception {
		InetAddress ia = InetAddress.getLocalHost();
		String ip = ia.getHostAddress();
		return ip;
	}
	public static String getMacAddress(String ipAddress) throws Exception {
		String os = System.getProperty("os.name");
		if (ipAddress == null || ipAddress.length() == 0)
			ipAddress = getMyIpAddress();
		
		String mac = null;
		
		if(os.startsWith("Windows")) {
			if (os.indexOf("Vista") != -1) {
				mac = windowsVistaParseMacAddress(windowsVistaRunNbtstatCommand(ipAddress));
			} else {
				mac = windowsParseMacAddress(windowsRunIpConfigCommand());
			}
		} else if(os.startsWith("Linux")) {
			mac = linuxParseMacAddress(linuxRunIfConfigCommand());
		} else if(os.startsWith("Mac OS X")) {
			mac = osxParseMacAddress(osxRunIfConfigCommand());
		}
		return mac;
	}

	/*
	 * Windows Vista stuff
	 */
	private static String windowsVistaParseMacAddress(String nbtStatResponse) throws Exception {
		StringTokenizer tokenizer = new StringTokenizer(nbtStatResponse, "\n");
		String lastMacAddress = null;
 
		while(tokenizer.hasMoreTokens()) {
			String line = tokenizer.nextToken().trim();
 
			if(!line.startsWith("MAC")) 
				continue;
 
			// see if line contains MAC address
			int macAddressPosition = line.indexOf("=");
			if(macAddressPosition <= 0) 
				continue;
 
			lastMacAddress = line.substring(macAddressPosition + 1).trim();
		}
		return lastMacAddress;
	}
	
	private static String windowsVistaRunNbtstatCommand(String ipAddress) throws Exception {
		if (ipAddress == null || ipAddress.length() == 0)
			return null;
		Process p = Runtime.getRuntime().exec("nbtstat -a " + ipAddress);
		//InputStream stdoutStream = new BufferedInputStream(p.getInputStream());
		Reader stdoutStream = new InputStreamReader(p.getInputStream());
 
		StringBuffer buffer= new StringBuffer();
		for (;;) {
			int c = stdoutStream.read();
			if (c == -1) 
				break;
			buffer.append((char)c);
		}
		String outputText = buffer.toString();
 
		stdoutStream.close();
		
		return outputText;
	}
	
	
	/*
	 * Windows stuff
	 */
	private static String windowsParseMacAddress(String ipConfigResponse) throws ParseException {
		String localHost = null;
		try {
			localHost = InetAddress.getLocalHost().getHostAddress();
		} catch(java.net.UnknownHostException ex) {
			ex.printStackTrace();
			throw new ParseException(ex.getMessage(), 0);
		}
 
		StringTokenizer tokenizer = new StringTokenizer(ipConfigResponse, "\n");
		String lastMacAddress = null;
 
		while(tokenizer.hasMoreTokens()) {
			String line = tokenizer.nextToken().trim();
 
			// see if line contains IP address
			if(line.endsWith(localHost) && lastMacAddress != null) {
				return lastMacAddress;
			}
 
			// see if line contains MAC address
			int macAddressPosition = line.indexOf(":");
			if(macAddressPosition <= 0) continue;
 
			String macAddressCandidate = line.substring(macAddressPosition + 1).trim();
			if(windowsIsMacAddress(macAddressCandidate)) {
				lastMacAddress = macAddressCandidate;
				continue;
			}
		}
 
		ParseException ex = new ParseException("cannot read MAC address from [" + ipConfigResponse + "]", 0);
		ex.printStackTrace();
		throw ex;
	}
 
 
	private static boolean windowsIsMacAddress(String macAddressCandidate) {
		Pattern macPattern = Pattern.compile("[0-9a-fA-F]{2}[-:][0-9a-fA-F]{2}[-:][0-9a-fA-F]{2}[-:][0-9a-fA-F]{2}[-:][0 -9a-fA-F]{2}[-:][0-9a-fA-F]{2}");
		Matcher m = macPattern.matcher(macAddressCandidate);
		return m.matches();
	}
 
	private static String windowsRunIpConfigCommand() throws IOException {
		Process p = Runtime.getRuntime().exec("ipconfig /all");
//		InputStream stdoutStream = new BufferedInputStream(p.getInputStream());
		Reader stdoutStream = new InputStreamReader(p.getInputStream());
 
		StringBuffer buffer= new StringBuffer();
		for (;;) {
			int c = stdoutStream.read();
			if (c == -1) break;
			buffer.append((char)c);
		}
		String outputText = buffer.toString();
 
		stdoutStream.close();
 
		return outputText;
	}
	/*
	 * Linux stuff
	 */
	private static String linuxParseMacAddress(String ipConfigResponse) throws ParseException {
		String localHost = null;
		try {
			localHost = InetAddress.getLocalHost().getHostAddress();
		} catch(java.net.UnknownHostException ex) {
			ex.printStackTrace();
			throw new ParseException(ex.getMessage(), 0);
		}
 
		StringTokenizer tokenizer = new StringTokenizer(ipConfigResponse, "\n");
		String lastMacAddress = null;
 
		while(tokenizer.hasMoreTokens()) {
			String line = tokenizer.nextToken().trim();
			boolean containsLocalHost = line.indexOf(localHost) >= 0;
 
			// see if line contains IP address
			if(containsLocalHost && lastMacAddress != null) {
				return lastMacAddress;
			}
 
			// see if line contains MAC address
			int macAddressPosition = line.indexOf("HWaddr");
			if(macAddressPosition <= 0) continue;
 
			String macAddressCandidate = line.substring(macAddressPosition + 6).trim();
			if(linuxIsMacAddress(macAddressCandidate)) {
				lastMacAddress = macAddressCandidate;
				continue;
			}
		}
 
		ParseException ex = new ParseException
			("cannot read MAC address for " + localHost + " from [" + ipConfigResponse + "]", 0);
		ex.printStackTrace();
		throw ex;
	}
 
 
	private static boolean linuxIsMacAddress(String macAddressCandidate) {
		Pattern macPattern = Pattern.compile("[0-9a-fA-F]{2}[-:][0-9a-fA-F]{2}[-:][0-9a-fA-F]{2}[-:][0-9a-fA-F]{2}[-:][0 -9a-fA-F]{2}[-:][0-9a-fA-F]{2}");
		Matcher m = macPattern.matcher(macAddressCandidate);
		return m.matches();
	}
 
 
	private static String linuxRunIfConfigCommand() throws IOException {
		Process p = Runtime.getRuntime().exec("ifconfig");
		InputStream stdoutStream = new BufferedInputStream(p.getInputStream());
 
		StringBuffer buffer= new StringBuffer();
		for (;;) {
			int c = stdoutStream.read();
			if (c == -1) break;
			buffer.append((char)c);
		}
		String outputText = buffer.toString();
 
		stdoutStream.close();
 
		return outputText;
	}

	/*
	 * Mac OS X Stuff
	 */
	 private static String osxParseMacAddress(String ipConfigResponse) throws ParseException {
		String localHost = null;
 
		try {
			localHost = InetAddress.getLocalHost().getHostAddress();
		} catch(java.net.UnknownHostException ex) {
			ex.printStackTrace();
			throw new ParseException(ex.getMessage(), 0);
		}
 
		StringTokenizer tokenizer = new StringTokenizer(ipConfigResponse, "\n");
 
		while(tokenizer.hasMoreTokens()) {
			String line = tokenizer.nextToken().trim();
			boolean containsLocalHost = line.indexOf(localHost) >= 0;
			// see if line contains MAC address
			int macAddressPosition = line.indexOf("ether");
			if(macAddressPosition != 0) continue;
			String macAddressCandidate = line.substring(macAddressPosition + 6).trim();
			if(osxIsMacAddress(macAddressCandidate)) {
				return macAddressCandidate;
			}
		}
 
		ParseException ex = new ParseException
			("cannot read MAC address for " + localHost + " from [" + ipConfigResponse + "]", 0);
			ex.printStackTrace();
			throw ex;
	}
 
	private static boolean osxIsMacAddress(String macAddressCandidate) {
		Pattern macPattern = Pattern.compile("[0-9a-fA-F]{2}[-:][0-9a-fA-F]{2}[-:][0-9a-fA-F]{2}[-:][0-9a-fA-F]{2}[-:][0 -9a-fA-F]{2}[-:][0-9a-fA-F]{2}");
		Matcher m = macPattern.matcher(macAddressCandidate);
		return m.matches();
	}
 
	private static String osxRunIfConfigCommand() throws IOException {
		Process p = Runtime.getRuntime().exec("ifconfig");
		InputStream stdoutStream = new BufferedInputStream(p.getInputStream());
		StringBuffer buffer= new StringBuffer();
		for (;;) {
			int c = stdoutStream.read();
			if (c == -1) break;
			buffer.append((char)c);
		}
		String outputText = buffer.toString();
		stdoutStream.close();
		return outputText;
	}
	
	public static String newId() {
		return IdUtil.getInstance().generate();
	}
	public static String encode(String str) {
		str = StringUtils.replace(str, "\"", "[e_dq]");
		str = StringUtils.replace(str, "'", "[e_q]");
		str = StringUtils.replace(str, "&", "[e_a]");
		return str;
	}
	
	public static String decode(String str) {
		str = StringUtils.replace(str, "[e_a]", "&");
		str = StringUtils.replace(str, "[e_q]", "'");
		str = StringUtils.replace(str, "[e_dq]", "\"");
		return str;
	}
	
	public static String tabs(int size) {
		if (size <= 0)
			return EMPTY;
		StringBuffer buf = new StringBuffer();
		for (int i=0; i<size; i++) {
			buf.append(TAB);
		}
		return buf.toString();
	}
	public static String substring(String str, int beginIndex, int endIndex) {
		if (CommonUtil.isEmpty(str) || str.length() <= beginIndex)
			return EMPTY;
		if (str.length() <= endIndex)
			return beginIndex > 0? str.substring(beginIndex) : str;
		return str.substring(beginIndex, endIndex);
	}
	public static String toLikeString(String str) {
		if (str == null)
			return null;
		return new StringBuffer(PERCENT).append(str).append(PERCENT).toString();
	}
	public static String toLikeLeftString(String str) {
		if (str == null)
			return null;
		return new StringBuffer().append(str).append(PERCENT).toString();
	}
	public static String toLikeRightString(String str) {
		if (str == null)
			return null;
		return new StringBuffer(PERCENT).append(str).toString();
	}
	public static String toNotNull(Object value) {
		if (value == null)
			return EMPTY;
		if (value instanceof String) {
			String valueStr = value.toString();
			if (isEmpty(valueStr))
				return EMPTY;
			return valueStr;
		}
		return value.toString();
	}
	public static String toNull(String value) {
		if (isEmpty(value))
			return null;
		return value;
	}
	public static Object toNull(Object value) {
		if (value instanceof String)
			return toNull((String)value);
		return value;
	}
	public static String toDefault(String value, String defValue) {
		if (isEmpty(value))
			return defValue;
		return value;
	}
	public static List toList(String strs, String deli) {
		List list = new ArrayList();
		fill(strs, deli, list);
		if (list.isEmpty())
			return null;
		return list;
	}
	public static Set toSet(String strs, String deli) {
		Set set = new HashSet();
		fill(strs, deli, set);
		if (set.isEmpty())
			return null;
		return set;
	}
	public static Set toSet(Object[] array) {
		if (isEmpty(array))
			return null;
		Set set = new HashSet();
		Object obj = null;
		for (int i=0; i<array.length; i++) {
			obj = array[i];
			if (obj == null)
				continue;
			set.add(obj);
		}
		return set;
	}
	private static void fill(String strs, String deli, Collection col) {
		if (strs == null)
			return;
		if (deli == null)
			deli = BAR;
		String[] tmps = StringUtils.tokenizeToStringArray(strs, deli);
		if (isEmpty(tmps))
			return;
		for (int i=0; i<tmps.length; i++) {
			String tmp = tmps[i];
			if (tmp == null || tmp.trim().length() == 0)
				continue;
			col.add(tmp.trim());
		}
	}
	public static String[] toArray(String strs, String deli) {
		List list = toList(strs, deli);
		if (CommonUtil.isEmpty(list))
			return null;
		String[] array = new String[list.size()];
		list.toArray(array);
		return array;
	}
	public static String toString(String[] array, String deli) {
		if (deli == null)
			deli = BAR;
		if (isEmpty(array))
			return null;
		StringBuffer buf = new StringBuffer();
		for (int i=0; i<array.length; i++) {
			buf.append(deli);
			buf.append(array[i]);
		}
		buf.append(deli);
		return buf.toString();
	}
	public static String[] toStringArray(int[] array) {
		if (array == null || array.length == 0)
			return null;
		String[] strArray = new String[array.length];
		for (int i=0; i<array.length; i++)
			strArray[i] = array[i] + EMPTY;
		return strArray;
	}
	public static String[] toStringArray(Collection col) {
		if (isEmpty(col))
			return null;
		String[] strArray = new String[col.size()];
		col.toArray(strArray);
		return strArray;
	}
	public static String[] toStringArray(List list) {
		return toStringArray((Collection)list);
	}
	
	public static boolean toBoolean(Object bool) {
		if (bool == null)
			return false;
		if (bool instanceof Boolean) {
			boolean b = ((Boolean) bool).booleanValue();
			return b;
		}
		bool = bool.toString();
		if (bool.equals(TRUE) || bool.equals(Y) || 
				bool.equals(ON) || bool.equals(ONE))
			return true;
		return false;
	}
	public static int toInt(Object obj) {
		return toInt(obj, 0);
	}
	public static int toInt(Object obj, int defaultInt) {
		if (obj == null || obj.equals("null"))
			return defaultInt;
		if (obj instanceof Integer)
			return ((Integer)obj).intValue();
		String str = obj.toString().trim();
		if (str.length() == 0)
			return defaultInt;
		return Integer.parseInt(obj.toString());
	}
	public static long toLong(Object obj) {
		return toLong(obj, 0);
	}
	public static long toLong(Object obj, long defaultLong) {
		if (obj == null)
			return defaultLong;
		if (obj instanceof Long)
			return ((Long)obj).longValue();
		String str = obj.toString().trim();
		if (str.length() == 0)
			return defaultLong;
		return Long.parseLong(obj.toString());
	}
	public static float toFloat(Object obj) {
		return toFloat(obj, 0);
	}
	public static float toFloat(Object obj, float defaultFloat) {
		if (obj == null)
			return defaultFloat;
		if (obj instanceof Integer) {
			return ((Integer)obj).intValue();
		} else if (obj instanceof Float) {
			return ((Float)obj).floatValue();
		}
		String str = obj.toString().trim();
		if (str.length() == 0)
			return defaultFloat;
		return Float.parseFloat(obj.toString());
	}
	
	public static List toList(Object[] array) {
		if (isEmpty(array))
			return null;
		List list = new ArrayList();
		for (int i=0; i<array.length; i++)
			list.add(array[i]);
		return list;
	}

	public static String[] add(String[] objs, String obj) {
		if (obj == null)
			return objs;
		List list = toList(objs);
		if (list == null)
			list = new ArrayList();
		list.add(obj);
		return toStringArray(list);
	}
	public static String[] addAll(String[] objs, String[] array) {
		if (CommonUtil.isEmpty(array))
			return objs;
		if (CommonUtil.isEmpty(objs))
			return array;
		List list = toList(objs);
		list.addAll(toList(array));
		return toStringArray(list);
	}

	public static String toClassName(Class cls) {
		return toClassName(cls, null);
	}
	public static String toClassName(Class cls, String prefix) {
		if (cls == null)
			return null;
		return toClassName(cls.getName(), prefix);
	}
	public static String toClassName(String pkgName) {
		return toClassName(pkgName, null);
	}
	public static String toClassName(String pkgName, String prefix) {
		if (pkgName == null)
			return null;
		String[] pkgNames = StringUtils.tokenizeToStringArray(pkgName, DOT);
		if (pkgNames.length == 1)
			return pkgName;
		String name = pkgNames[pkgNames.length - 1];
		if (prefix != null && prefix.length() != 0)
			name = name.replaceFirst(prefix, EMPTY);
		return name;
	}
	
	public static String toName(Class cls) {
		return toName(cls, null);
	}
	public static String toName(Class cls, String prefix) {
		if (cls == null)
			return null;
		return toName(cls.getName(), prefix);
	}
	public static String toName(String name) {
		return toName(name, null);
	}
	public static String toName(String name, String prefix) {
		if (name == null)
			return null;
		name = toClassName(name, prefix);
		String firstStr = name.charAt(0) + EMPTY;
		name = name.replaceFirst(firstStr, firstStr.toLowerCase());
		return name;
	}
	
	public static String toUpperName(String name) {
		if (name == null)
			return null;
		name = toClassName(name);
		String firstStr = name.charAt(0) + EMPTY;
		name = name.replaceFirst(firstStr, firstStr.toUpperCase());
		return name;
	}
	
	public static boolean isNumber(String str) {
		if (str == null)
			return false;
		boolean dotNotFound = true;
		for(int i = 0; i < str.length(); i++) {
			int ascii = (int) str.charAt(i);
			if (dotNotFound && ascii == 46) {
				dotNotFound = false;
				continue;
			}
			if(ascii < 48 || ascii > 57)
				return false;
		}
		return true;
	}
	
	public static boolean contains(Object[] objs, Object obj) {
		if (CommonUtil.isEmpty(objs) || obj == null)
			return false;
		for (int i=0; i<objs.length; i++) {
			if (obj.equals(objs[i]))
				return true;
		}
		return false;
	}

	public static int size(Object[] obj) {
		return isEmpty(obj)? 0 : obj.length;
	}
	public static int size(Collection obj) {
		return isEmpty(obj)? 0 : obj.size();
	}
	public static int size(Map obj) {
		return isEmpty(obj)? 0 : obj.size();
	}
	public static int size(NodeList obj) {
		return isEmpty(obj)? 0 : obj.getLength();
	}
	public static int size(NamedNodeMap obj) {
		return isEmpty(obj)? 0 : obj.getLength();
	}
	public static boolean isEmpty(Object[] array) {
		return array == null || array.length == 0;
	}
	public static boolean isEmpty(Collection col) {
		return col == null || col.isEmpty();
	}
	public static boolean isEmpty(List list) {
		return list == null || list.isEmpty();
	}
	public static boolean isEmpty(Map map) {
		return map == null || map.isEmpty();
	}
	public static boolean isEmpty(NodeList list) {
		return list == null || list.getLength() == 0;
	}
	public static boolean isEmpty(NamedNodeMap nnm) {
		return nnm == null || nnm.getLength() == 0;
	}
	public static boolean isEmpty(String str) {
		if (str == null || str.length() == 0)
			return true;
		String tmp = str.trim();
		return tmp.length() == 0 || tmp.equals(NULL) || tmp.equals("undefined");
	}
	
	public static boolean isEqual(Object a, Object b) {
		if (a == null) {
			if (b == null)
				return true;
			return false;
		}
		if (b == null)
			return false;
		return a.equals(b);
	}

	public static String toStackTraceString(Throwable t) {
		StringWriter sWriter = new StringWriter();
		PrintWriter pWriter = new PrintWriter(sWriter, true);
		t.printStackTrace(pWriter);
		return sWriter.toString();
	}
}