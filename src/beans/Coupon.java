package com.omri.coupon.beans;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.omri.coupon.enums.CouponType;

@XmlRootElement
public class Coupon {
		private long couponId;
		private String couponTitle;
		private long companyId;
		private String startDate;
		private String endDate;
		private long couponAmount;
		private CouponType couponType;
		private String message;
		private double price;
		private String image;
		
		

		public Coupon() {
			super();
		}



		public Coupon(long couponId, String couponTitle, long companyId, String startDate, String endDate,
				long couponAmount, CouponType couponType, String message, double price, String image) {
			super();
			this.couponId = couponId;
			this.couponTitle = couponTitle;
			this.companyId = companyId;
			this.startDate = startDate;
			this.endDate = endDate;
			this.couponAmount = couponAmount;
			this.couponType = couponType;
			this.message = message;
			this.price = price;
			this.image = image;
			
		}


		public Coupon(String couponTitle, long companyId, String startDate, String endDate, long couponAmount,
				CouponType couponType, String message, double price, String image) {
			super();
			this.couponTitle = couponTitle;
			this.companyId = companyId;
			this.startDate = startDate;
			this.endDate = endDate;
			this.couponAmount = couponAmount;
			this.couponType = couponType;
			this.message = message;
			this.price = price;
			this.image = image;
			
		}



		public Coupon(String endDate, long couponAmount, double price) {
			super();
			this.endDate = endDate;
			this.couponAmount = couponAmount;
			this.price = price;
		}



		public long getCouponId() {
			return couponId;
		}



		public void setCouponId(long couponId) {
			this.couponId = couponId;
		}



		public String getCouponTitle() {
			return couponTitle;
		}



		public void setCouponTitle(String couponTitle) {
			this.couponTitle = couponTitle;
		}



		public long getCompanyId() {
			return companyId;
		}



		public void setCompanyId(long companyId) {
			this.companyId = companyId;
		}



		public String getStartDate() {
			return startDate;
		}



		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}



		public String getEndDate() {
			return endDate;
		}



		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}



		public long getCouponAmount() {
			return couponAmount;
		}



		public void setCouponAmount(long couponAmount) {
			this.couponAmount = couponAmount;
		}



		public CouponType getCouponType() {
			return couponType;
		}



		public void setCouponType(CouponType couponType) {
			this.couponType = couponType;
		}



		public String getMessage() {
			return message;
		}



		public void setMessage(String message) {
			this.message = message;
		}



		public double getPrice() {
			return price;
		}



		public void setPrice(double price) {
			this.price = price;
		}



		public String getImage() {
			return image;
		}



		public void setImage(String image) {
			this.image = image;
		}



		@Override
		public String toString() {
			return "Coupon [couponId=" + couponId + ", couponTitle=" + couponTitle + ", companyId=" + companyId
					+ ", startDate=" + startDate + ", endDate=" + endDate + ", couponAmount=" + couponAmount
					+ ", couponType=" + couponType + ", message=" + message + ", price=" + price + ", image=" + image
					+ "]";
		}






	
}
