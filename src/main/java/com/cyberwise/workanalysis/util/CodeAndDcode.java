package com.cyberwise.workanalysis.util;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;  
public class CodeAndDcode {
	/**
	 * ����
	 * @param s
	 * @return
	 */
	public static String getBASE64(String s) { 
		if (s == null) return null; 
		return new BASE64Encoder().encode( s.getBytes() ); 
		} 
	   /**
	    * ����
	    * @param str
	    * @return
	    */
	public static String getFromBASE64(String s,String charset) { 
		if (s == null) return null; 
		BASE64Decoder decoder = new BASE64Decoder(); 
		try { 
			s=s.replace("\r","");
		byte[] b = decoder.decodeBuffer(s); 
		String ss= new String(b,charset);
		 return ss.replace(" ","");
		} catch (Exception e) { 
			 
		}
		return s;
		}
	public static byte[] getFromBASE641(String s,String charset) { 
		if (s == null) return null; 
		BASE64Decoder decoder = new BASE64Decoder(); 
		s=s.replace("\n","");
		try { 
		byte[] b = decoder.decodeBuffer(s); 
		return b;
		} catch (Exception e) { 
			 
		}
		return null;
		}
}
