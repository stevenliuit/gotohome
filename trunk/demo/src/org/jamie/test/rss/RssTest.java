package org.jamie.test.rss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URLConnection;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class RssTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RssHandler rssHandler = new RssHandler(); 
		  try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			
			 reader.setContentHandler(rssHandler); 		 
			 
			 InputStream is = getRSSSource();
			 /*BufferedReader breader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			 	 StringBuilder sb = new StringBuilder();
			 String line;
			 while ((line = breader.readLine()) != null) {
			sb.append(line).append("\n");
			 }
			 
			 System.out.println(sb.toString());
			*/
	
			reader.parse(new InputSource(is));   
			rssHandler.printMessageList();
			
		//	String result = rssHandler;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
	}

	
	public static InputStream getRSSSource(){
		String RSS_Url="http://tt.mop.com/rss/zy.xml";
		RSS_Url="http://news.baidu.com/n?cmd=1&class=civilnews&tn=rss";
		System.getProperties().put("proxySet", "true");
		System.getProperties().put("proxyHost", "172.17.18.80");
		System.getProperties().put("proxyPort", "8080");
		java.net.URL url;
		try {
			url = new java.net.URL(RSS_Url);
			return url.openStream();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	
}
