package com.demo.simpleType;

import java.math.BigDecimal;

/**
 * @author Jiaming.Zhang  E-mail:eJMingZhang@gmail.com
 * @version 创建时间：2012-3-13 下午12:26:58
 * 类说明
 */

public class UpNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a= "3";
		String b="2";
		double val =Math.ceil(Float.parseFloat(a)/Float.parseFloat(b));
		System.out.println( val +"   " );
		double i = 2, j = 2.1, k = 2.5, m = 2.9;    
		System.out.println("BigDecimal四舍五入取整:(-" + i + ")="    
				+ new BigDecimal("-2").setScale(0, BigDecimal.ROUND_HALF_UP));    
				System.out.println("BigDecimal四舍五入取整:(" + j + ")="    
				+ new BigDecimal("2.1").setScale(0, BigDecimal.ROUND_UP));    
				System.out.println("BigDecimal四舍五入取整:(-" + k + ")="    
				+ new BigDecimal("-2.5").setScale(0, BigDecimal.ROUND_HALF_UP));    
				System.out.println("BigDecimal四舍五入取整:(-" + m + ")="    
				+ new BigDecimal("-2.9").setScale(0, BigDecimal.ROUND_HALF_UP));    
				System.out.println();    
		
	}

}
