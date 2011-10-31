package net.smartworks.server.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
 * MODEL(OBJECT) -> XML STRING
 * XML STRING -> MODEL(OBJECT)
 * 개발중
 */

public class XmlModelConverter {
	
	public static String modelToXml(Object obj) throws Exception {
		if (obj == null)
			return null;
	
		Class cls = obj.getClass();
		
		Map<String, FieldInfo> fieldAttrMap = new HashMap<String, FieldInfo>();
		Map<String, FieldInfo> fieldEleMap = new HashMap<String, FieldInfo>();
		
		setFieldsMap(cls, fieldAttrMap, fieldEleMap);
		
		StringBuffer buffXml = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
		
		String attrStr = makeAttributeStr(fieldAttrMap, obj);
		String eleStr = makeElementStr(fieldEleMap, obj);
		
		buffXml.append("<").append(cls.getSimpleName())
				.append(attrStr).append(">\r\n")
				.append(eleStr)
				.append("</").append(cls.getSimpleName()).append(">");
		
		return buffXml.toString();
		
	}
	private static void setFieldsMap(Class cls, Map<String, FieldInfo> attrMap, Map<String, FieldInfo> eleMap) throws Exception {
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			if (field.getName().indexOf("A_") == 0) {
				FieldInfo fieldInfo = new FieldInfo(cls, field);
				attrMap.put(fieldInfo.getName(), fieldInfo);
				continue;
			}
			if (field.getName().indexOf("E_") == 0) {
				FieldInfo fieldInfo = new FieldInfo(cls, field);
				eleMap.put(fieldInfo.getName(), fieldInfo);
				continue;
			}
		}
		if (cls.getSuperclass() != null)
			setFieldsMap(cls.getSuperclass(), attrMap, eleMap);
	}
	private static String makeAttributeStr(Map<String, FieldInfo> attrMap, Object obj) throws Exception {
		//test="test" test2="test2"
		StringBuffer attrBuff = new StringBuffer();
		for (Iterator iterator = attrMap.keySet().iterator(); iterator.hasNext();) {
			String key = (String)iterator.next();
			FieldInfo fieldInfo = attrMap.get(key);
			String fieldName = fieldInfo.getName();
			String fieldType = fieldInfo.getType();
			
			Method methodGet = fieldInfo.getMethodGet();
			
			Object value = methodGet.invoke(obj, null);
			
			
			if (fieldType.equalsIgnoreCase("boolean")) {
				Object bool = methodGet.invoke(obj, null);
				value = (Boolean)methodGet.invoke(obj, null);
			} else if (fieldType.equalsIgnoreCase("int")) {
				value = (Integer)methodGet.invoke(obj, null);
			} else if (fieldType.equalsIgnoreCase("long")) {
				value = (Long)methodGet.invoke(obj, null);
			} else if (fieldType.equalsIgnoreCase("float")) {
				value = (Float)methodGet.invoke(obj, null);
			} else if (fieldType.equalsIgnoreCase("double")) {
				value = (Double)methodGet.invoke(obj, null);
			} else if (fieldType.equalsIgnoreCase("short")) {
				value = (Short)methodGet.invoke(obj, null);
			} else if (fieldType.equalsIgnoreCase("byte")) {
				value = (Byte)methodGet.invoke(obj, null);
			} else if (fieldType.equalsIgnoreCase("char")) {
				value = (Character)methodGet.invoke(obj, null);
			} else if (fieldType.equalsIgnoreCase("double")) {
				value = (Double)methodGet.invoke(obj, null);
			} else {
				value = methodGet.invoke(obj, null);
			}
			if (value == null || value.equals(""))
				continue;
			attrBuff.append(" ").append(fieldName).append("=").append("\"").append(value).append("\"");
		}
		return attrBuff.toString();
	}
	private static String makeElementStr(Map<String, FieldInfo> eleMap, Object obj) throws Exception {
		//<test><![CDATA[test]]></test>\n\r
		StringBuffer eleBuff = new StringBuffer();
		for (Iterator iterator = eleMap.keySet().iterator(); iterator.hasNext();) {
			String key = (String)iterator.next();
			FieldInfo fieldInfo = eleMap.get(key);
			String fieldName = fieldInfo.getName();
			String fieldType = fieldInfo.getType();
			Method methodGet = fieldInfo.getMethodGet();
			Object value = null;
			if (fieldType.equalsIgnoreCase("boolean")) {
				value = (Boolean)methodGet.invoke(obj, null);
			} else if (fieldType.equalsIgnoreCase("int")) {
				value = (Integer)methodGet.invoke(obj, null);
			} else if (fieldType.equalsIgnoreCase("long")) {
				value = (Long)methodGet.invoke(obj, null);
			} else if (fieldType.equalsIgnoreCase("float")) {
				value = (Float)methodGet.invoke(obj, null);
			} else if (fieldType.equalsIgnoreCase("double")) {
				value = (Double)methodGet.invoke(obj, null);
			} else if (fieldType.equalsIgnoreCase("short")) {
				value = (Short)methodGet.invoke(obj, null);
			} else if (fieldType.equalsIgnoreCase("byte")) {
				value = (Byte)methodGet.invoke(obj, null);
			} else if (fieldType.equalsIgnoreCase("char")) {
				value = (Character)methodGet.invoke(obj, null);
			} else if (fieldType.equalsIgnoreCase("double")) {
				value = (Double)methodGet.invoke(obj, null);
			} else {
				value = methodGet.invoke(obj, null);
			}
			if (value == null || value.equals(""))
				continue;
			eleBuff.append("\t<").append(fieldName).append(">")
				.append("<![CDATA[").append(value).append("]]></")
					.append(fieldName).append(">")
						.append("\n");
		}
		return eleBuff.toString();
	}
}
class FieldInfo {
	private String type = null;
	private String name = null;
	private Method methodGet = null;
	private Method methodSet = null;
	
	public FieldInfo(Class cls, Field field) throws Exception {
		String fieldName = (String)field.get(field.getName());
		this.name = fieldName;
		Field privateField = cls.getDeclaredField(fieldName);
		this.type = privateField.getType().getSimpleName();
		Method[] methods = cls.getDeclaredMethods();
		for(Method method : methods) {
			if (this.type.equals("boolean")) {
				
				
			}
			if (method.getName().equalsIgnoreCase("get"+fieldName)) {
				this.methodGet = method;
			}
			if (method.getName().equalsIgnoreCase("set"+fieldName)) {
				this.methodSet = method;
			}
		}
	}
	public Method getMethodGet() {
		return methodGet;
	}
	public void setMethodGet(Method methodGet) {
		this.methodGet = methodGet;
	}
	public Method getMethodSet() {
		return methodSet;
	}
	public void setMethodSet(Method methodSet) {
		this.methodSet = methodSet;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
