package com.demo.simpleType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.travelsky.thub.linkforhub.util.DateCounter;

/**
 * @author Jiaming.Zhang E-mail:eJMingZhang@gmail.com
 * @version 创建时间：2012-2-27 下午03:47:00 类说明
 */

public class ListCopy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("ddd");
		String[] strs = list.toArray(new String[list.size()]);
		System.out.println(strs.toString());
		list.add(2, "ccc");
		printList(null);
		List<String> list2 = new ArrayList<String>();
		//Collections.copy(list2, list);
		String[] strs1 = null;
		for (int i = 0; (strs1 != null && i < strs1.length); i++) {
			System.out.println(strs1[i]);
		}
		int j = 0;
		System.out.println(++j);

		for (String string : list2) {
			System.out.println(string);
		}
		List<Rate> cvsRates = new ArrayList<Rate>();
		cvsRates.add(new Rate(null,null,null,"00011111001","152","2012-03-01"));
		cvsRates.add(new Rate(null,null,null,"00011111001","152","2012-03-03"));
		cvsRates.add(new Rate(null,null,null,"00011111001","152","2012-03-07"));
		cvsRates.add(new Rate(null,null,null,"00011111001","152","2012-03-08"));
		
		List<Rate> dbRates = new ArrayList<Rate>();
		dbRates.add(new Rate("153","2012-03-01","2012-03-07","00011111001",null,null));
		//cvsRates.add(new Rate(null,null,null,"00011111001","152","2012-03-02"));
		//cvsRates.add(new Rate(null,null,null,"00011111001","152","2012-03-03"));
		Map<String, List<Rate>> dbRatesMap = new HashMap<String, List<Rate>>();
		dbRatesMap.put("00011111001", dbRates);
		getChangeAndNewRate( cvsRates, 0,  dbRatesMap);
		//DateCounter.shiftDate(input, offset, type)
	}
	
	public static void printList(List<String> list){
		
		for (int i = 0; (list != null && i < list.size()); i++) {
		//for (int i = 0;  i < list.size(); i++) {
			System.out.println("list "+i +": "+list.get(i));
		}
	}

	public static void getChangeAndNewRate(List<Rate> cvsRates, int index, Map<String, List<Rate>> dbRatesMap) {
		Rate cvsRate = null;
		Boolean newFlag = true;
		if (cvsRates != null && index < cvsRates.size()) {
			cvsRate = cvsRates.get(index);
			List<Rate> dbRates = dbRatesMap.get(cvsRate.getRatePlanCode());

			for (int i = 0; dbRates != null && i < dbRates.size(); i++) {				
				Rate dbRate = dbRates.get(i);
				String cvsRateDate = cvsRate.date;
				String dbCheckInDate = dbRate.getCheckInDate();
				String dbCheckOutDate = dbRate.getCheckOutDate();
				if (cvsRateDate.compareToIgnoreCase(dbCheckOutDate) <= 0 && cvsRateDate.compareToIgnoreCase(dbCheckInDate) >= 0) {
					newFlag = false;
					if (!cvsRate.rate.equalsIgnoreCase(dbRate.getRateAmount())) {
						if (dbCheckInDate.equalsIgnoreCase(dbCheckOutDate)) {
							// 如果库中价格开始时间和结束时间相等
							dbRate.setRateAmount(cvsRate.rate);
						} else if (dbCheckInDate.compareToIgnoreCase(dbCheckOutDate) < 0 && dbCheckInDate .equalsIgnoreCase(cvsRateDate)) {
							// 如果cvsRate的时间等于dbRate的开始时间，就创建该天时间的价格，原来的dbRate开始时间向后移一天
							Rate newdbRate = cloneDhubRate(dbRate);
							newdbRate.setCheckOutDate(cvsRateDate);
							newdbRate.setRateAmount(cvsRate.rate);
							dbRate.setCheckInDate(DateCounter.shiftDate(cvsRateDate, 1, "yyyy-MM-dd") );
							dbRates.add(i,newdbRate);

						} else if (dbCheckInDate.compareToIgnoreCase(dbCheckOutDate) < 0 && dbCheckOutDate.equalsIgnoreCase(cvsRateDate)) {
							// 如果cvsRate的时间等于dbRate的结束时间，就创建该天时间的价格，原来的dbRate结束时间向前移一天
							Rate newdbRate = cloneDhubRate(dbRate);
							newdbRate.setCheckInDate(cvsRateDate);
							newdbRate.setRateAmount(cvsRate.rate);
							dbRate.setCheckOutDate(DateCounter.shiftDate(cvsRateDate, -1, "yyyy-MM-dd"));
							dbRates.add(i+1,newdbRate);

						} else {
							Rate newdbRatePre = cloneDhubRate(dbRate);
							Rate newdbRateMid = cloneDhubRate(dbRate);
							newdbRatePre.setCheckOutDate(DateCounter.shiftDate(cvsRateDate, -1, "yyyy-MM-dd"));
							newdbRateMid.setCheckInDate(cvsRateDate);
							newdbRateMid.setCheckOutDate(cvsRateDate);
							newdbRateMid.setRateAmount(cvsRate.rate);
							dbRate.setCheckInDate(DateCounter.shiftDate(cvsRateDate, 1, "yyyy-MM-dd"));
							dbRates.add(i,newdbRatePre);
							dbRates.add(i+1,newdbRateMid);
						}
					}
					break;
				}				
			}// end for
			if(newFlag){//如果所查询的价格是新的时间段
				Rate dbNewRate = new Rate();
				dbNewRate.setCheckInDate(cvsRate.date);
				dbNewRate.setCheckOutDate(cvsRate.date);
				dbNewRate.setRateAmount(cvsRate.rate);
				dbNewRate.setRatePlanCode(cvsRate.getRatePlanCode());
				//..
				
				dbRates.add(dbRates.size(),dbNewRate);
			}
				
			getChangeAndNewRate(cvsRates,++index,dbRatesMap);

		}// end of IF

	}

	private static Rate cloneDhubRate(Rate rate) {

		Rate rate1 = new Rate();

		try {
			BeanUtils.copyProperties(rate1, rate);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rate1;
	}
}
