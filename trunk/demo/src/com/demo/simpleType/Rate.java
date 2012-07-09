package com.demo.simpleType;
/**
 * @author Jiaming.Zhang  E-mail:eJMingZhang@gmail.com
 * @version 创建时间：2012-2-27 下午05:31:08
 * 类说明
 */

public class Rate {
	
	public Rate() {
	
	}
	public Rate(String rateAmount, String checkInDate, String checkOutDate, String ratePlanCode, String rate, String date) {
		this.rateAmount = rateAmount;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.ratePlanCode = ratePlanCode;
		this.rate = rate;
		this.date = date;
	}
	String rateAmount;
	public String getRateAmount() {
		return rateAmount;
	}
	public void setRateAmount(String rateAmount) {
		this.rateAmount = rateAmount;
	}
	public String getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}
	public String getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public String getRatePlanCode() {
		return ratePlanCode;
	}
	public void setRatePlanCode(String ratePlanCode) {
		this.ratePlanCode = ratePlanCode;
	}
	String checkInDate;
	String checkOutDate;
	String ratePlanCode;
	String rate;
	String date;
}
