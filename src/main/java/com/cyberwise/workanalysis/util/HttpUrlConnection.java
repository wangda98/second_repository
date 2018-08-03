package com.cyberwise.workanalysis.util;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
public class HttpUrlConnection {
	 private static String sessionId="";
	 private static URL url1;//申明一个url对象
	 private static URL url2;//申明一个url对象
	 public  static String  connection(String account,String password){
		 try {
				//获取url对象
		url1=new URL("http://pm.cyberwise.com.cn/pms/www/index.php?m=api&f=getSessionID&t=json");
		//根据请求类型获取到生成的URLConnection类的子类HttpURLConnection,故此处最好将其转化    
		// 为HttpURLConnection类型的对象,以便用到    HttpURLConnection更多的API
		 HttpURLConnection openConnection = (HttpURLConnection) url1.openConnection();
		//设置HttpURLConnection参数
		 openConnection.setRequestMethod("GET");
		// 设置是否向httpUrlConnection输出，因为这个是get请求，参数放在  url中，因此需要设为false, 默认情况下是false;  
		 openConnection.setDoOutput(false);
		// 设置 请求是否使用缓存
		 openConnection.setUseCaches(true);
		 //设置传送的内容是可序列化的java对象
		 openConnection.setRequestProperty("Content-type", "application/x-java-serialized-object");    
		 //设置完毕然后开始连接
		 openConnection.connect();
		 //用getOutputStream或者getInputstream方法也可以建立连接
		 InputStream inputStream = openConnection.getInputStream();
		 BufferedReader bReader=new BufferedReader(new InputStreamReader(inputStream));
		StringBuffer sb=new StringBuffer();
		 while (bReader.ready()) {
			 String string=bReader.readLine();
			sb.append(string);
			}
		 bReader.close();//关闭输入流
		 String result=sb.toString();
		 sessionId=result.substring(result.indexOf("sessionID")+"sessionID".length()+5, result.indexOf("sessionID")+"sessionID".length()+5+26);
			} catch ( Exception e) {
				e.printStackTrace();
			}
			try {
				//获取url对象
				url2=new URL("http://pm.cyberwise.com.cn/pms/www/index.php?m=user&f=login&t=json&sessionID="+sessionId);
		//根据请求类型获取到生成的URLConnection类的子类HttpURLConnection,故此处最好将其转化    
		// 为HttpURLConnection类型的对象,以便用到    HttpURLConnection更多的API
		 HttpURLConnection openConnection = (HttpURLConnection) url2.openConnection();
		//设置HttpURLConnection参数
		 openConnection.setRequestMethod("POST");
		// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在    
		// http正文内，因此需要设为true, 默认情况下是false;  
		 openConnection.setDoOutput(true);
		 //设置完毕然后开始连接
		 openConnection.connect();
		 //用getOutputStream或者getInputstream方法也可以建立连接
		 String cmd="account="+account+"&password="+password;
		 OutputStream outputStream = openConnection.getOutputStream();
		 outputStream.write(cmd.getBytes());
		 InputStream inputStream = openConnection.getInputStream();
		 BufferedReader bReader=new BufferedReader(new InputStreamReader(inputStream));
		StringBuffer sb=new StringBuffer();
		 while (bReader.ready()) {
			 String string=bReader.readLine();
			sb.append(string);
			}
		 String result=sb.toString();
		 String str=result.substring(result.indexOf("status")+"status".length()+3, result.indexOf("status")+"status".length()+3+7);
		 return str;
			} catch ( Exception e) {
				e.printStackTrace();
			}
			return null;
	 }
	 /**
	  *从Jason格式的对象中获取字段对应的值
	  * @param json
	  * @param string
	  * @return
	  */
	private static String paserJson(String json,String string ) {
		JSONObject jsonObject = JSON.parseObject(json);
		 String msg = (String) jsonObject.get(string);
		return msg;
	}
	public static void main(String[] args) {
		System.out.println(HttpUrlConnection.connection("wangda", "Wd5233"));
	}
}
