package com.demo.simpleType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiaming.Zhang  E-mail:eJMingZhang@gmail.com
 * @version 创建时间：2012-3-31 下午03:36:12
 * 类说明
 */

public class ListTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List list = new ArrayList(3);
		
		System.out.println(" list size " +list.size());
		list.add(0, "23");
		list.add(2, "23");
		System.out.println(" list size " +list.size());
		//System.out.println(list.get(0));
	}

}
