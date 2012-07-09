package com.demo.stack;

/**
 * @author Jiaming.Zhang E-mail:eJMingZhang@gmail.com
 * @version 创建时间：2012-3-9 上午10:49:04 类说明
 */

public class StackSize {
	private int size = 1;

	public void stackLeak() {
		size++;
		stackLeak();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Throwable {
		StackSize gg = new StackSize();  
		        try{  
		            gg.stackLeak();  
		        }catch(Throwable e){  
		           System.out.println(gg.size);  
		          // throw e;  
		            //e.printStackTrace();   
		        }  

	}

}
