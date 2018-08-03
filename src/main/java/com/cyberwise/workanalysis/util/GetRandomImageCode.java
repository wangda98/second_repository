package com.cyberwise.workanalysis.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import org.aspectj.apache.bcel.classfile.Code;

public class GetRandomImageCode {
	private static final   int imageWidth=158;
	private static final  int imageHeight=47;
	 private static final Random random=new Random();
	
	private static final char[] chars={ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',   
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',  
		'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',  
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',   
		'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	public static  Object[] getImageAndCode(){
		StringBuffer sb=new StringBuffer();
		BufferedImage img=new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = img.createGraphics();//获取 画笔
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fillRect(0, 0, imageWidth, imageHeight);//填充图片背景颜色
		//在图片上画干扰线
		graphics.setFont(new Font( "微软雅黑",Font.PLAIN,25));
		for (int i = 0; i < 12; i++) {
			graphics.setColor(getRandomColor());
			graphics.drawLine(random.nextInt(imageWidth), random.nextInt(imageHeight), random.nextInt(imageWidth), random.nextInt(imageHeight));
		}
		//在图片上画干扰点
		for (int i = 0; i < 130; i++) {
			graphics.setColor(getRandomColor());
			int x=random.nextInt(imageWidth);
			int y=random.nextInt(imageHeight);
			graphics.drawLine(x,y,x,y);
		}
		//在图片上开始画字符
		graphics.setFont(new Font( "微软雅黑",Font.PLAIN,38));
		for (int i = 0; i <4; i++) {
			graphics.setColor(getRandomColor());//设置随机颜色
			String code=Character.toString(chars[random.nextInt(chars.length-1)]);//随机获取字
			graphics.drawString(code, imageWidth/4*i+8, imageHeight*2/3+7);
			sb.append(code);
 		}
		return new Object[]{img,sb.toString()};
	}
	public static Color getRandomColor(){
		return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
	}
	public static void main(String[] args) {
		Object[] objects = GetRandomImageCode.getImageAndCode();
		BufferedImage image=(BufferedImage) objects[0];
		try {
			FileOutputStream fos=new FileOutputStream(new File("c:/2a2.png"));
			ImageIO.write(image, "png", fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
