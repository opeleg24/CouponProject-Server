package com.omri.coupon.login;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class CustomerFilter
 */
@WebFilter("/rest/customer/*")
public class CustomerFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CustomerFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		String url=req.getRequestURL().toString();
		System.out.println(url);
	
		

		
		 if(req.getSession().getAttribute("CustomerId")!=null){
			System.out.println("Active Session,CustomerId session: "+req.getSession().getAttribute("CustomerId"));
		
			chain.doFilter(request, response);
			
		}
	
		 else{
				
				System.out.println("No Session");
				res.setStatus(401);
		 }
	
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
