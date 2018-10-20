package com.omri.coupon.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.plaf.synth.SynthSpinnerUI;

import com.omri.coupon.beans.Company;
import com.omri.coupon.beans.Coupon;
import com.omri.coupon.beans.UserLoginDetails;
import com.omri.coupon.controller.CouponController;
import com.omri.coupon.dao.CouponDAO;
import com.omri.coupon.enums.CouponType;
import com.omri.coupon.timerTask.DailyCouponExpirationTask;



public class MainCoupon {

	public static void main(String[] args) throws Exception {
		
		CouponDAO coupondao=new CouponDAO();
		CouponController couponController=new CouponController();
//		coupondao.deleteCouponByCompanyId(17, 2);
//		Coupon coupon=new Coupon();
//		Coupon coupon=new Coupon("Popcorn","2017/11/01","2018/5/24",200,CouponType.FOOD,"This coupon will give you 50 percent discount on large popcorn",6.99,"IMAGE");
//		Coupon coupon2=new Coupon("dsfgdsfg",1,"2017/11/01","2017/11/14", 200,couponType.FOOD,"This coupon will give you 50 percent discount on large popcorn",6.99,"IMAGE");
//		Coupon coupon3=new Coupon("Second Ticket Half Price",1,"2017/11/01","2017/12/18",2,couponType.FOOD,"This coupon will give you 50 percent discount on your second ticket",9.99,"IMAGE");
//		couponController.createCoupon(coupon);
//		coupondao.updateCoupon(coupon,1);
//		couponController.getCoupon(1);


	}
		
	
		
	}

