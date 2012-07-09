package org.jamie.test.self;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		
		Map map = new HashMap();
		double d=1.01111111111111111111111111111111d;
		float f= 1.04444444444444444444444444444444444f;
		int ii = 2;
		System.out.println(f);
		
		Set set = new HashSet();
		set.add("123");
		set.add("56");
		
		List<String> list = new ArrayList<String>();
		list.add("123");
		list.add("56");
		System.out.println("set :" +set.containsAll(list));
		
		//String  xmlStr = "<BasicPropertyInfo HotelCode="SZXLM" HotelName="HOTEL &UITES LANDMARK SHENZHEN"/>";
		
		String requestXml = "2011-10-17&amp; vCheckOutDate=&";
		//String sysout = requestXml.replaceAll("&", " ");
		//System.out.println(sysout+ "        "+requestXml);
		String content = requestXml;
		String[] specialChar = new String[]{ "&amp;", "&apos;", "&gt;", "&lt;", "&quot;" };
		String[]     midvar =  new String[]{"{amp;}", "{apos;}", "{gt;}", "{lt;}", "{quot;}" };
		for (int i = 0; i < specialChar.length; i++) {
			if (requestXml.contains(specialChar[i])) {
				System.out.println("specialChar " +specialChar[i] +" -  midvar "+midvar[i]);
				requestXml =	requestXml.replaceAll(specialChar[i] ,midvar[i] );
				requestXml = content;
			}
		}
		
		System.out.println(" content:\n "+requestXml);
	//	requestXml =requestXml.replace("&", " ");
		System.out.println(" content2:\n "+requestXml);
		
		for (int j = 0; j < specialChar.length; j++) {
			if (requestXml.contains(midvar[j])) {
				requestXml =requestXml.replaceAll(midvar[j], specialChar[j]);
			}
		}	
		
		//double 类型小数位截取
		double d1 = 12.566;
		java.text.DecimalFormat   df=new   java.text.DecimalFormat( "#.## "); 
		System.out.println(df.format(d1));		
		
		 // double d1 = 12.566;
		  int   scale   =   2;//设置位数  
		  int   roundingMode   =   3;//表示四舍五入，可以选择其他舍值方式，例如去尾，等等.  

		BigDecimal   bd   =   new   BigDecimal((double)d1);  
			//setScale(保留位数   ,   截取方式3：直接截取、4四舍五入)
		  bd   =   bd.setScale(scale,BigDecimal.ROUND_FLOOR);  
		  double dv   =   bd.doubleValue();  
		System.out.println("dv "+dv);
		
		System.out.println(BigDecimal.ROUND_CEILING+" "+BigDecimal.ROUND_FLOOR+" "+BigDecimal.ROUND_HALF_DOWN );
		
		//
		 String str = "1|2.0";
		 System.out.println(str.substring(0, str.indexOf("|")));
		 System.out.println("\u007C");
		 
		 
		 
	}

}
