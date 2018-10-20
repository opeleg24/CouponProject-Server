package com.omri.coupon.interfaceDao;

import java.util.ArrayList;

import com.omri.coupon.beans.Coupon;
import com.omri.coupon.exception.ApplicationException;



public interface ICouponDao {
	public void createCoupon(Coupon coupon) throws ApplicationException;
	public void deleteCoupon(long couponId) throws ApplicationException;
	public void updateCoupon(Coupon coupon) throws ApplicationException;
	public Coupon getCoupon(long couponId) throws ApplicationException;
	public ArrayList<Coupon> getAllCoupons() throws ApplicationException;
	public ArrayList<Coupon> getAllPurchasedCouponsByCustomerId(long customerId) throws ApplicationException;
	public ArrayList<Coupon> getCouponsByCompanyId(long companyId) throws ApplicationException;
	public ArrayList<Coupon> getCouponsByCouponType(String couponType) throws ApplicationException;


}
