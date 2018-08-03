package com.cyberwise.workanalysis.util;

public class DecodeUtil {
	public static String deCode(String password){
		String [] array=password.split("");
		String [] array2=new String[array.length];
		String []array3=new String[array.length/2];
		for (int i = 0; i < array.length; i++) {
			array2[i]=array[array.length-i-1];
		} 
		for (int i = 0; i < array3.length; i++) {
			array3[i]=array2[2*i+1];
		}
		StringBuffer sbBuffer=new StringBuffer();
		for (String string : array3) {
			sbBuffer.append(string);
		}
		return sbBuffer.toString();
	} 
}
