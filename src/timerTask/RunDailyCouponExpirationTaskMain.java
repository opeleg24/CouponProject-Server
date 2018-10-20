package com.omri.coupon.timerTask;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class RunDailyCouponExpirationTaskMain {

	public static void main(String[] args) {
		
//		 creating timer task, timer, I run in so now every 24 hours it will remove expired coupons
//		
		   TimerTask dailyCouponExpirationTask = new DailyCouponExpirationTask();  
		   Timer timer = new Timer();
		   Date now = Calendar.getInstance().getTime(); 
		   timer.schedule(dailyCouponExpirationTask,now,1000*60*60*24);
//		   timer.cancel();

		  

	}

}
