package com.aks.security.rback.exception;

import org.springframework.stereotype.Component;

/**
 * @author Ankush.Verman
 * 
 */
@Component
public class CustomException extends Exception {

	private String errorCode;
	private String errorInfo;
	private Object[]	objs;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -224841374146550515L;

	/**
	 * No Argument Constructor
	 */
	public CustomException() {
		super();
	}
	
	/**
	 * Constructor with errorCode & errorInfo
	 * 
	 * @param errorCode
	 * @param info
	 */
	public CustomException(String errorCode,String errorInfo) {
		super(errorCode);
		this.errorCode = errorCode;
		this.errorInfo = errorInfo;
	}

	/**
	 * Construcotr take errorCode with actual exception
	 * 
	 * @param errorCode
	 * @param ex
	 */
	public CustomException(String errorCode, Exception ex) {
		super(errorCode, ex);
		this.errorCode = errorCode;
	}

	/**
	 * Takes errorCode, ErrorInfo with original Exception
	 * 
	 * @param errorCode
	 * @param errorInfo
	 * @param ex
	 */
	public CustomException(String errorCode, String errorInfo, Exception ex) {
		super(errorCode, ex);
		this.errorCode = errorCode;
		this.errorInfo = errorInfo;
	}
	
	
	/**
	 * Constructor with errorcode, error msgs & original exception
	 * 
	 * @param errorCode, objs, ex
	 */
	public CustomException(String errorCode, Object[] objs, Exception ex) {
		super();
		this.errorCode = errorCode;
		this.objs = objs;
	}

	//setter & getter...
	public Object[] getObjs() {
		return objs;
	}

	public void setObjs(Object[] objs) {
		this.objs = objs;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

}
