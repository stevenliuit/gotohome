package com.demo.xmlcovert;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * @author Jiaming.Zhang E-mail:eJMingZhang@gmail.com
 * @version 创建时间：2012-2-22 下午02:35:41 类说明
 */

public class Train {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			test();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void test() throws IOException {
		String xmlFileName = "xml/testxml.xml";
		String xslFileName = "xml/testxsl.xsl";
		String htmlFileName = "d:/tutu/html.html";
		// Train.transform(xmlFileName, xslFileName, htmlFileName);
		System.out.println(Train.transform(xmlFileName, xslFileName));
	}

	public static void transform(String xmlFileName, String xslFileName, String htmlFileName) {
		try {
			TransformerFactory tFac = TransformerFactory.newInstance();
			Source xslSource = new StreamSource(xslFileName);
			Transformer t = tFac.newTransformer(xslSource);
			File xmlFile = new File(xmlFileName);
			File htmlFile = new File(htmlFileName);
			Source source = new StreamSource(xmlFile);
			Result result = new StreamResult(htmlFile);
			System.out.println(result.toString());
			t.transform(source, result);

			System.out.println(readFile(htmlFileName));
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	public static String transform(String xmlFileName, String xslFileName) {
		try {
			TransformerFactory tFac = TransformerFactory.newInstance();
			Source xslSource = new StreamSource(xslFileName);
			Transformer t = tFac.newTransformer(xslSource);
			File xmlFile = new File(xmlFileName);
			// File htmlFile = new File(htmlFileName);
			Source source = new StreamSource(xmlFile);
			String res = "";
			OutputStream outputStream = new ByteArrayOutputStream();
			Result result = new StreamResult(outputStream);
			t.transform(source, result);
			return outputStream.toString();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String TransformWithInput(InputStream inputStream, String xslFileName, String htmlFileName) {
		try {
			TransformerFactory tFac = TransformerFactory.newInstance();
			Source xslSource = new StreamSource(xslFileName);
			Transformer t = tFac.newTransformer(xslSource);

			// File htmlFile = new File(htmlFileName);
			String resultXML = null;
			Source source = new StreamSource(inputStream);
			Result result = new StreamResult(resultXML);
			System.out.println(result.toString());
			t.transform(source, result);

			return resultXML;
			// System.out.println(readFile(htmlFileName));
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 读取xml文件
	public static String readFile(String fileName) {
		// TODO Auto-generated method stub
		StringBuffer requestXML = new StringBuffer();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf-8"));
			String s = null;
			while ((s = reader.readLine()) != null) {
				requestXML.append(s);
			}
		} catch (IOException ex) {

		}
		return requestXML.toString();
	}
}
