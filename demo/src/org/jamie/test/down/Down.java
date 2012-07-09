package org.jamie.test.down;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Down {

	public static void main2(String[] args){
		//makeImg("http://newsxml.cnool.net/newspic2/2009/2009-1/2009-1-31/633689982874843750.jpg");
	}
	public static void main(String[] args) {

		String img = "";
		List<String> pics = new ArrayList<String>();

		String str = getWebCon("http://www.163.com");
		//String regEx="";
		String regEx = "http://[([a-z0-9]|.|/|\\|-|,|?|;|'|:|<|>|{|}|[|]|&|=|!|@|#|$|%|^|*|(|)|_|-|+|)]+.[(jpeg)|(bmp)|(gif)|(png)|(jpg)] "; // 表示a或f
		//String regEx = "http://.*[(jpeg)|(bmp)|(gif)|(png)|(jpg)] ";
		//regEx ="src=\"(.{1,150})\"\s*/>";
		Pattern p = Pattern.compile(regEx);

		Matcher m_image = p.matcher(str);

		while (m_image.find()) {
			img = m_image.group();
			String string = img.charAt(img.length() - 1) + "";
			System.out.println("string :" +string);
			while (!(string.equals("g") || string.equals("f"))) {

				img = img.substring(0, img.length() - 1);
				string = img.charAt(img.length() - 1) + "";
			}

			System.out.println(img);

			pics.add((img));

		}

		for (String string : pics) {
			Down mi = new Down();
			mi.makeImg(string);
		}
		
	}

	public  void makeImg(String str) {
		try {
			String url = str;
			BufferedInputStream in = new BufferedInputStream(new URL(url)
					.openStream());
			int index = str.lastIndexOf("/");
			String sName = str.substring(index, str.length());

			File img = new File("D:\\tutu\\" + sName);
			BufferedOutputStream out = new BufferedOutputStream(
					new FileOutputStream(img));
			byte[] buf = new byte[2048];
			int length = in.read(buf);
			while (length != -1) {
				out.write(buf, 0, length);
				length = in.read(buf);
			}
			in.close();
			out.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getWebCon(String domain) {
		// System.out.println("开始读取内容...("+domain+")");
		StringBuffer sb = new StringBuffer();
		try {
			System.getProperties().put("proxySet", "true");
			System.getProperties().put("proxyHost", "172.17.18.80");
			System.getProperties().put("proxyPort", "8080");
			java.net.URL url = new java.net.URL(domain);
			URLConnection connection = url.openConnection();			
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			while ((line = in.readLine()) != null) {
				//System.out.println(line);
				sb.append(line);
				
			}
			in.close();

		} catch (Exception e) { // Report any errors that arise
			sb.append(e.toString());
			System.err.println(e);
			System.err.println("Usage: java HttpClient <URL> [<filename>]");
		}
		return sb.toString();
	}

}
