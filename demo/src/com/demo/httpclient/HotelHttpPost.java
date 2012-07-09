package com.demo.httpclient;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.UUID;
import java.util.zip.GZIPInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.travelsky.thub.linkforhub.util.XMLOperate;

public class HotelHttpPost {
	private static int connectionTimeOut = 30000;
	private static int socketTimeOut = 30000;
	public static String HotelAvail = "HotelAvail";
	public static String HotelRes = "HotelRes";
	public static String HotelCancel = "HotelCancel";
	public static String HotelMofidy = "HotelMofidy";

	// 提交请求，并获取返回信息
	public static String xmlHttpPost(String requestInfo, String urlAddress, String soapAction, boolean isProxy, String proxyHost, int proxyPort, String encoding, String vendorCode, String transactionName, String requestType, String mqID) {
		String responseInfo = null;
		InputStream inputResStream = null;
		PostMethod postMethod = null;
		HttpClient httpClient = new HttpClient();
		String exceptionInfo = null;
		String errorCode = null;
		String errorDescription = null;
		int statusCode = 0; // 返回状�?
		UUID id = UUID.randomUUID();
		String uuid = id.toString().replaceAll("-", "").trim();
		long rqStart = new Long(System.currentTimeMillis());
		// 打印请求配置信息
		String requestSource = "urlAddress: " + urlAddress.trim() + "\n SOAPAction: " + soapAction + "\n proxyHost: " + proxyHost.trim() + "\n  proxyPort: " + proxyPort;
		String hotelCommand = vendorCode + "_" + transactionName;
		// 打印请求信息
		if ((null == requestType || "".equalsIgnoreCase(requestType))) {
			System.out.println("\n" + requestSource);
			System.out.println("\n" + vendorCode + "酒店接口请求 >>> 指令 :" + hotelCommand + " |MQID:" + mqID + " |请求响应标识ID:" + uuid + " |请求 �?RequestXML内容: \n" + formatXML(requestInfo));
		}
		try {
			if (isProxy) {
				// 设置代理
				HostConfiguration hostConfiguration = new HostConfiguration();
				hostConfiguration.setProxy(proxyHost.trim(), proxyPort);
				httpClient.setHostConfiguration(hostConfiguration);
			}
			if (!"DataCache".equalsIgnoreCase(requestType)) {
				// 设置超时
				HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();
				// 设置连接超时时间(单位毫秒)
				managerParams.setConnectionTimeout(connectionTimeOut);
				// 设置读数据超时时�?单位毫秒)
				managerParams.setSoTimeout(socketTimeOut);
			}

			// 将请求参数放入postMethod�?
			postMethod = new PostMethod(urlAddress.trim());
			postMethod.addRequestHeader("Accept-Encoding", "gzip, deflate");
			if (null != soapAction) {
				postMethod.addRequestHeader("SOAPAction", soapAction);
			}
			postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);
			postMethod.addRequestHeader("ContentLength", Integer.toString(requestInfo.length()));
			if ("JJH".equalsIgnoreCase(vendorCode)) {
				postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=" + encoding);
				// 填入各个表单域的�?
				NameValuePair[] data = { new NameValuePair("Message", requestInfo) };
				// 将表单的值放入postMethod�?
				postMethod.setRequestBody(data);
			} else {
				// 设置请求
				StringRequestEntity requestEntity = new StringRequestEntity(requestInfo, "text/xml", encoding);
				postMethod.addRequestHeader("Content-Type", "text/xml;charset=" + encoding);
				postMethod.setRequestEntity(requestEntity);
			}
			// 发�?请求并接受返回，统计返回时间
			long time1 = new Long(System.currentTimeMillis());
			statusCode = httpClient.executeMethod(postMethod);
			long time2 = new Long(System.currentTimeMillis());
			if (null == requestType || "".equalsIgnoreCase(requestType)) {
				System.out.println("\n" + vendorCode + "酒店接口响应 >>> 指令:" + hotelCommand + " |响应状�?:" + statusCode + " |响应时间: " + (time2 - time1) / 1000f + "(S)" + " |MQID:" + mqID + " |请求响应标识ID:" + uuid);
			}
			// 如果返回不是200，就会出现错误信�?
			if (statusCode == HttpStatus.SC_OK) {
				//inputResStream = postMethod.getResponseBodyAsStream();
				System.out.println("获取数据大小  ：" + postMethod.getResponseBody().length + "B");
				BufferedReader br = null;
				if (inputResStream != null) {
					// br = new BufferedReader(new
					// InputStreamReader(inputResStream, encoding));
					/*
					 * br = new BufferedReader(new
					 * InputStreamReader(inputResStream, encoding));
					 * StringBuffer resBuffer = new StringBuffer(); String
					 * resTemp = ""; if (br != null) { while ((resTemp =
					 * br.readLine()) != null) { resBuffer.append(resTemp); }
					 * responseInfo = resBuffer.toString(); }
					 */
					//responseInfo = unzip(inputResStream);
				}
			} else {// 服务返回错误信息
				errorCode = String.valueOf(postMethod.getStatusCode());
				errorDescription = CommonConst.httpStatusCodeMap.get(String.valueOf(statusCode));
				System.out.println("POST DATA FAILED!HTTP STATUS::" + postMethod.getStatusLine() + "(" + errorDescription + ")\n");
			}

		} catch (SocketTimeoutException e) {
			// exceptionInfo = getStackTraceAsString(e);
			errorCode = "451";
			long time3 = new Long(System.currentTimeMillis());
			errorDescription = "SocketTimeoutException! 服务器响应超�? :Read timed out！ " + (time3 - rqStart) / 1000f + "s";
		} catch (UnsupportedEncodingException e) {
			// exceptionInfo = getStackTraceAsString(e);
			errorCode = "452";
			errorDescription = "UnsupportedEncodingException! 不支持的字符编码  :" + encoding + " ";
		} catch (HttpException e) {
			// exceptionInfo = getStackTraceAsString(e);
			errorCode = "453";
			errorDescription = "HttpException! 发生致命的异常，可能是协议不对或者返回的内容有问题！";
		} catch (ConnectTimeoutException e) {
			errorCode = "454";
			errorDescription = "ConnectTimeoutException! 请求服务连接超时";
			// exceptionInfo = getStackTraceAsString(e);
		} catch (ConnectException e) {
			// exceptionInfo = getStackTraceAsString(e);
			errorCode = "455";
			errorDescription = "ConnectException! 请求本地代理连接失败！请�?��你的本地代理  IP:" + proxyHost + "  Port:" + proxyPort;
		} catch (UnknownHostException e) {
			// exceptionInfo = getStackTraceAsString(e);
			errorCode = "456";
			errorDescription = "UnknownHostException! 请求本地代理连接失败！请求的本地代理  IP:" + proxyHost + "  Port:" + proxyPort;
		} catch (IOException e) {
			errorCode = "457";
			errorDescription = "IOException! 发生网络异常，请�?��你的请求 URL " + urlAddress;
			// exceptionInfo = getStackTraceAsString(e);
		} catch (Exception e) {
			errorCode = "458";
			errorDescription = "其他Exception! 请检查你的请求代理和请求服务 �?" + requestSource;
			// exceptionInfo = getStackTraceAsString(e);

		} finally {
			try {
				// 释放连接
				if (postMethod != null) {
					postMethod.releaseConnection();
				}
				if (inputResStream != null) {
					inputResStream.close();
				}
				long time4 = new Long(System.currentTimeMillis());

				// 如果返回为空，或返回异常信息，就构�?返回错误信息返回给DHUB
				if ("".equalsIgnoreCase(responseInfo) || null == responseInfo || "null".equalsIgnoreCase(responseInfo)) {
					System.out.println("返回 结果 为 空 " + responseInfo);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 打印返回信息
		if ((null == requestType || "".equalsIgnoreCase(requestType)) || (statusCode != 200 && requestType == "DataCache")) {
			long rqEnd = new Long(System.currentTimeMillis());
			System.out.println("\n" + vendorCode + "酒店接口返回 >>> 指令:" + hotelCommand + " |耗时:" + (rqEnd - rqStart) / 1000f + "(S) |MQID:" + mqID + " |请求响应标识ID:" + uuid + " |酒店返回ResponseXML内容: ");
			 System.out.println("\n" + formatXML(responseInfo));

		}
		return responseInfo;
	}

	private static Document parseXmlFile(String in) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(in));
			return db.parse(is);
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		} catch (SAXException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String formatXML(String unformattedXml) {
		try {
			final Document document = parseXmlFile(unformattedXml);

			OutputFormat format = new OutputFormat(document);
			format.setLineWidth(65);
			format.setIndenting(true);
			format.setIndent(2);
			Writer out = new StringWriter();
			XMLSerializer serializer = new XMLSerializer(out, format);
			serializer.serialize(document);
			return out.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String unzip(InputStream in) {
		try {
			// Open the compressed stream
			GZIPInputStream gin = new GZIPInputStream(in);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			// Transfer bytes from the compressed stream to the output stream
			byte[] buf = new byte[1024];
			int len;
			while ((len = gin.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			// Close the file and stream
			gin.close();
			out.close();
			return out.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	 private int readUByte(InputStream in) throws IOException {
			int b = in.read();
			if (b == -1) {
			    throw new EOFException();
			}
		        if (b < -1 || b > 255) {
		            // Report on this.in, not argument in; see read{Header, Trailer}.
		          //  throw new IOException(this.in.getClass().getName()
		           //     + ".read() returned value out of range -1..255: " + b);
		        }
			return b;
		    }
	 
	public static void main(String[] args) {
		String urlAddress = null;
		String soapAction = "";
		boolean isProxy = true;
		String proxyHost = "172.17.18.80";
		int proxyPort = 8080;
		String encoding = "UTF-8";
		String vendorCode = "IHG";
		String transactionName = "HotelAvail";
		String requestType = null;
		while (true) {
			System.out.println("选择供应商：1、IHG ;2、JJH ;3、JJI ;4、MOT ;5、HLT ;6、STW ;9、退出");
			Scanner vendorInput = new Scanner(System.in);
			int vendor = Integer.parseInt(vendorInput.next());
			switch (vendor) {
			case 1:	vendorCode = "IHG";	break;
			case 2:	vendorCode = "JJH";	break;
			case 3:	vendorCode = "JJI";	break;
			case 4:	vendorCode = "MOT";	break;
			case 5:	vendorCode = "HLT";	break;
			case 6:	vendorCode = "STW";	break;
			case 9:	System.out.println("已经退出！");
				System.exit(0);
			}
			urlAddress =initVendorParam(vendorCode);
			String requestXml = XMLOperate.readFile("xml/HotelAvailRQ_" + vendorCode + ".xml");
			long time1 = new Long(System.currentTimeMillis());
			for (int i = 0; i < 1; i++) {
			HotelHttpPost.xmlHttpPost(requestXml, urlAddress, soapAction, isProxy, proxyHost, proxyPort, encoding, vendorCode, transactionName, requestType, "");
			}
			long time2 = new Long(System.currentTimeMillis());
			System.out.println("平均耗时：" + (time2 - time1) / 1000f);
		}

		
		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 * String now=sdf.format(new Date()); System.out.println(now);
		 */
		/*long nowTime = new Long(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = sdf.format(nowTime);
		System.out.println(now);*/

	}

	public static String initVendorParam(String vendorCode  ) {
		String urlAddress =null;

		if ("IHG".equalsIgnoreCase(vendorCode)) {
			urlAddress = "https://utc.b2b.ihg.com/b2b/xml/sprravailsearch/2006A/hotels.xml";
			// urlAddress =
			// "https://utc.b2b.ihg.com/b2b/xml/hoteldescriptivesearch/2006A/hotels.xml";

		} else if ("JJH".equalsIgnoreCase(vendorCode)) {
			urlAddress = "http://203.156.243.13:8090/switch/servlet/SwitchReceiveServlet";
			urlAddress = "http://sw.hubs1.net/servlet/SwitchReceiveServlet";

		} else if ("JJI".equalsIgnoreCase(vendorCode)) {

		} else if ("MOT".equalsIgnoreCase(vendorCode)) {

		} else if ("STW".equalsIgnoreCase(vendorCode)) {
			urlAddress="http://ud-uat.pegs.com:5454/apps/TransactionInterfaceV1_1/DispatcherServlet";
		}
		return urlAddress;
	}

}
