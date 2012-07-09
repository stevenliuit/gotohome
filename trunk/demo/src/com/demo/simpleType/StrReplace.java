package com.demo.simpleType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiaming.Zhang E-mail:eJMingZhang@gmail.com
 * @version 创建时间：2012-2-16 下午01:29:53 类说明
 */

public class StrReplace {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String str1 = "15/06";

		System.out.println("20" + str1.replace("/", "-"));

		String str2 = "|dkkkk | 4444 | dddd";
		//strSplit();
		System.out.println(str2.split("\\|")[0]);
		if(str2.startsWith("|")){
			str2 =str2.substring(1, str2.length());
		}
		System.out.println(str2);
	}

	public static void strSplit() {
		String str1 = "wushiyigebuzhidaodehaoxingqingwoyaoaiwomendezhoghuarenmingongheguo";
		double ss = str1.length() / 50f;
		ss = Integer.parseInt(new BigDecimal(ss).setScale(0, BigDecimal.ROUND_UP) + "");
		List list = new ArrayList();
		String temp;

		if (ss > 1) {
			int beginIndex = 0;
			int endIndex = 59;
			for (int i = 0; i < ss; i++) {
				if (endIndex <= str1.length()) {
					temp = str1.substring(beginIndex, endIndex);
				} else {
					temp = str1.substring(beginIndex, str1.length()-1);
				}
				beginIndex += 60;
				endIndex += 60;
				list.add(temp);
			}
		} else {
			list.add(str1);
		}
		System.out.println();
	}
}
