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
		connection = (HttpURLConnection) url.openConnection();//�½�����ʵ��
		connection.setDoOutput(true);//�Ƿ������� true|false
		connection.setDoInput(true);//�Ƿ��������true|false
		connection.setRequestMethod("POST");//�ύ����POST|GET
		connection.setUseCaches(false);//�Ƿ񻺴�true|false
		connection.connect();//�����Ӷ˿�
		DataOutputStream out = new DataOutputStream(connection.getOutputStream());//����������Զ˷�����д����
		out.writeBytes(content);//д����,Ҳ�����ύ��ı� name=xxx&pwd=xxx
		out.flush();//ˢ��
		out.close();//�ر������
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection
		.getInputStream(), "GBK"));//���Զ�д������ �Զ˷������������� ,��BufferedReader������ȡ
		StringBuffer buffer = new StringBuffer();
		String line = "";
		Byte[] bytes= new Byte[1000];  
		while ((line = reader.readLine()) != null) {
		buffer.append(line);
		}
		System.out.println("���صĽ��    ��" +buffer.toString());
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
		connection.disconnect();//�ر�����
		}
		}
		return null;
		}
}
