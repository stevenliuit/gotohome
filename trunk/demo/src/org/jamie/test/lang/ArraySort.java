package org.jamie.test.lang;

import java.util.*;

public class ArraySort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	int [] a = new int[]{1,5,3};
	
	Arrays.sort(a);
	for (int i : a) {
		//System.out.println(i);
	}
	
	//List l  = new ArrayList();
	
	String [] strs = 
	{"2012-05-06",
	 "2012-05-04",
	 "2012-05-08",
	 "2012-05-05",
	 "2012-05-09",};
	Arrays.sort(strs);
	for (String s : strs) {
		System.out.println(s);
	}
	
	
	for (int i = 0; i < 20; i++) {
		
		if(i==10) continue;
		System.out.println(" print: " +i );
		
	}
	
	}

}
