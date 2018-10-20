package com.omri.coupon.exception;

import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.omri.coupon.beans.ErrorBean;
import com.sun.jersey.api.ParamException;
import com.sun.jersey.api.ParamException.PathParamException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;


@Provider
public class ExceptionsHandler implements ExceptionMapper<Throwable> 
{

	@Override
	public Response toResponse(Throwable exception) 
	{
		System.out.println("Just entered into the exceptions mapper");
		if (exception instanceof ApplicationException){
			ApplicationException e = (ApplicationException) exception;
			int errorCode =e.getErrorType().getNumber();
			System.out.println(e.getErrorType().toString());
			ErrorBean errorBean = new ErrorBean(e.getMessage());
			return Response.status(errorCode).entity(errorBean).build();

		}
		
		
		if(exception instanceof PathParamException|exception instanceof WebApplicationException){
			
			exception.printStackTrace();
			int errorNumber=602;
			ErrorBean errorBeanGerneral=new ErrorBean("General Error");
			return Response.status(errorNumber).entity(errorBeanGerneral).build();
	
		}
		

		System.out.println("Server error");
		return Response.status(500).build();
	}
}