package com.inventario.tienda.util;


public class RespuestaGeneral {
	
	private String status;
	private String message;
	private Object resul;
	
	public RespuestaGeneral() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RespuestaGeneral(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public RespuestaGeneral(String status, String message, Object resul) {
		super();
		this.status = status;
		this.message = message;
		this.resul = resul;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Object getResul() {
		return resul;
	}
	
	public void setResul(Object resul) {
		this.resul = resul;
	}
	


}
