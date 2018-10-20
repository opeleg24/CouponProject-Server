package com.omri.coupon.beans;



import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Customer {
	
		private long customerId;
		private String customerName;
		private String customerPassword;
		
		public Customer() {
			super();
		}

		public Customer(long customerId, String customerName, String customerPassword) {
			super();
			this.customerId = customerId;
			this.customerName = customerName;
			this.customerPassword = customerPassword;
		}

		public Customer(String customerName, String customerPassword) {
			super();
			this.customerName = customerName;
			this.customerPassword = customerPassword;
		}

		public long getCustomerId() {
			return customerId;
		}

		public void setCustomerId(long customerId) {
			this.customerId = customerId;
		}

		public String getCustomerName() {
			return customerName;
		}

		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}

		public String getCustomerPassword() {
			return customerPassword;
		}

		public void setCustomerPassword(String customerPassword) {
			this.customerPassword = customerPassword;
		}

		@Override
		public String toString() {
			return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerPassword="
					+ customerPassword + "]";
		}

	


}
