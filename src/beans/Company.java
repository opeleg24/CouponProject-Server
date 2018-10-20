package com.omri.coupon.beans;


import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Company {
	
		private long companyId;
		private String companyName;
		private String companyPassword;
		private String companyEmail;
		
		
		public Company() {
			super();
		}


		public Company(long companyId, String companyName, String companyPassword, String companyEmail) {
			super();
			this.companyId = companyId;
			this.companyName = companyName;
			this.companyPassword = companyPassword;
			this.companyEmail = companyEmail;
		}


		public Company(String companyName, String companyPassword, String companyEmail) {
			super();
			this.companyName = companyName;
			this.companyPassword = companyPassword;
			this.companyEmail = companyEmail;
		}


		public long getCompanyId() {
			return companyId;
		}


		public void setCompanyId(long companyId) {
			this.companyId = companyId;
		}


		public String getCompanyName() {
			return companyName;
		}


		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}


		public String getCompanyPassword() {
			return companyPassword;
		}


		public void setCompanyPassword(String companyPassword) {
			this.companyPassword = companyPassword;
		}


		public String getCompanyEmail() {
			return companyEmail;
		}


		public void setCompanyEmail(String companyEmail) {
			this.companyEmail = companyEmail;
		}


		@Override
		public String toString() {
			return "Company [companyId=" + companyId + ", companyName=" + companyName + ", companyPassword="
					+ companyPassword + ", companyEmail=" + companyEmail + "]";
		}

		

		


}
