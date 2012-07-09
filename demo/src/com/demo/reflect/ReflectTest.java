package com.demo.reflect;

import java.lang.reflect.Field;


/**
 * @author Jiaming.Zhang E-mail:eJMingZhang@gmail.com
 * @version 创建时间：2012-3-31 上午10:40:45 类说明
 */

public class ReflectTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReflectTest reflect = new ReflectTest();
		String classPath = reflect.getClass().toString();
		System.out.println(ReflectTest.class);
		System.out.println(classPath);
		
		 String b[]=classPath.split(" ");
		  Class cla=null;
		  try {
		   cla=Class.forName(b[1]);
		  } catch (ClassNotFoundException e) {
		   e.printStackTrace();
		  }
		  //获取类名
		  System.out.println(cla.getSimpleName());
		  Field[] f=cla.getDeclaredFields();
		  //获取字段名
		  for(int i=0;i<f.length;i++){
		   System.out.println(f[i].getName());
		  }
	}

}
