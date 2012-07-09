package org.jamie.test.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestCase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String   url ="http://gdcainfo.miitbeian.gov.cn/icp/publish/query/icpMemoInfo_searchExecute.action";
		url="http://gdcainfo.miitbeian.gov.cn/validateCode";
		String  content="siteDomain=hanjuz.com";
		getResult(url,content);
	}
	public static String getResult(String urlStr, String content) {
		System.getProperties().put("proxySet", "true");
		System.getProperties().put("proxyHost", "172.17.18.80");
		System.getProperties().put("proxyPort", "8080");
		URL url = null;
		HttpURLConnection connection = null;
		try {
		url = new URL(urlStr);
		connection = (HttpURLConnection) url.openConnection();//新建连接实例
		connection.setDoOutput(true);//是否打开输出流 true|false
		connection.setDoInput(true);//是否打开输入流true|false
		connection.setRequestMethod("POST");//提交方法POST|GET
		connection.setUseCaches(false);//是否缓存true|false
		connection.connect();//打开连接端口
		DataOutputStream out = new DataOutputStream(connection.getOutputStream());//打开输出流往对端服务器写数据
		out.writeBytes(content);//写数据,也就是提交你的表单 name=xxx&pwd=xxx
		out.flush();//刷新
		out.close();//关闭输出流
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection
		.getInputStream(), "GBK"));//往对端写完数据 对端服务器返回数据 ,以BufferedReader流来读取
		StringBuffer buffer = new StringBuffer();
		String line = "";
		Byte[] bytes= new Byte[1000];  
		while ((line = reader.readLine()) != null) {
		buffer.append(line);
		}
		System.out.println("返回的结果    ：" +buffer.toString());
		File f = new File("d:\\opt\\validateCode.bmp");
		if(f.exists()){
		 
		}
			OutputStream fos = new FileOutputStream(f);
			fos.write(buffer.toString().getBytes());
			fos.flush();
			fos.close();
		
		reader.close();
		return buffer.toString();
		} catch (IOException e) {
		e.printStackTrace();
		} finally {
		if (connection != null) {
		connection.disconnect();//关闭连接
		}
		}
		return null;
		}
}
