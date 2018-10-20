package com.omri.coupon.timerTask;

import java.util.TimerTask;

import com.omri.coupon.dao.CouponDAO;
import com.omri.coupon.exception.ApplicationException;


public class DailyCouponExpirationTask extends TimerTask {
	
	private CouponDAO couponDao;

	
	public DailyCouponExpirationTask() {
		super();
		this.couponDao = new CouponDAO();
	}

	
	public void run() {
		
		try {
		
			couponDao.RemoveOldCouponsFromJoinCouponTable();
			couponDao.RemoveOldCouponsFromCouponTable();
		
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
