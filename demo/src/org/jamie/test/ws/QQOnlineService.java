package org.jamie.test.ws;

import java.io.*;
import java.net.*;

public class QQOnlineService {
	public static void main(String[] args) throws Exception {
		String urlString = "http://www.webxml.com.cn/webservices/qqOnlineWebService.asmx";
		String xmlFile = "QQOnlineService.XML";
		String soapActionString = "http://WebXml.com.cn/qqCheckOnline";
		URL url = new URL(urlString);
		
		System.getProperties().put("proxySet", "true");
		System.getProperties().put("proxyHost", "172.17.18.80");
		System.getProperties().put("proxyPort", "8080");
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();		
		File fileToSend = new File(xmlFile);
		byte[] buf = new byte[(int) fileToSend.length()];
		new FileInputStream(xmlFile).read(buf);
		httpConn.setRequestProperty("Content-Length", String
				.valueOf(buf.length));
		httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
		httpConn.setRequestProperty("soapActionString", soapActionString);
		httpConn.setRequestMethod("POST");
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
		OutputStream out = httpConn.getOutputStream();
		out.write(buf);
		out.close();
		InputStreamReader isr = new InputStreamReader(
				httpConn.getInputStream(), "utf-8");
		BufferedReader in = new BufferedReader(isr);
		String inputLine;
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream("result.xml")));
		while ((inputLine = in.readLine()) != null) {
			System.out.println(inputLine);
			bw.write(inputLine);
			bw.newLine();
		}
		bw.close();
		in.close();
	}
}