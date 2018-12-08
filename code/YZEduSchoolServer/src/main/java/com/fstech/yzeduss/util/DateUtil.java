package com.fstech.yzeduss.util;

import java.util.Calendar;

public class DateUtil {
	
	public static String mydatetime(){
		String datetime=null;
		 Calendar now = Calendar.getInstance();
		datetime=buling(now.get(Calendar.YEAR))+"-"+
				buling((now.get(Calendar.MONTH) + 1))+"-"+
		 		buling(now.get(Calendar.DAY_OF_MONTH))+" "+
		 		buling(now.get(Calendar.HOUR_OF_DAY))+":"+
		 		buling(now.get(Calendar.MINUTE));
		return datetime;
	}
	
	public static String buling(int i){
		String x=i+"";
		if(x.length()==1){
			x='0'+x;
		}
		return x;
	}

}
