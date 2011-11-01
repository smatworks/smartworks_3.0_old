package net.smartworks.server.util;

import java.net.InetAddress;

public class IdUtil {
	private static IdUtil instance;

	private static final int IP;
	static {
		int ipadd;
		try {
			ipadd = toInt(InetAddress.getLocalHost().getAddress());
		} catch (Exception e) {
			ipadd = 0;
		}
		IP = ipadd;
	}
	private static short counter = (short) 0;
	private static final int JVM = (int) ( System.currentTimeMillis() >>> 8 );
	
	public static IdUtil getInstance() {
		if (instance == null)
			instance = new IdUtil();
		return instance;
	}

	public static int toInt( byte[] bytes ) {
		int result = 0;
		for (int i=0; i<4; i++) {
			result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
		}
		return result;
	}

	protected String format(int intval) {
		String formatted = Integer.toHexString(intval);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8-formatted.length(), 8, formatted );
		return buf.toString();
	}
	
	protected String format(short shortval) {
		String formatted = Integer.toHexString(shortval);
		StringBuffer buf = new StringBuffer("0000");
		buf.replace(4-formatted.length(), 4, formatted );
		return buf.toString();
	}

	protected short getHiTime() {
		return (short)(System.currentTimeMillis() >>> 32 );
	}

	protected int getLoTime() {
		return (int)System.currentTimeMillis();
	}

	protected short getCount() {
		synchronized(IdUtil.class) {
			if (counter<0) counter=0;
			return counter++;
		}
	}

	public String generate() {
		return new StringBuffer(32)
		.append(format(IP))
		.append(format(JVM))
		.append(format(getHiTime()))
		.append(format(getLoTime()))
		.append(format(getCount()))
		.toString();
	}

}
