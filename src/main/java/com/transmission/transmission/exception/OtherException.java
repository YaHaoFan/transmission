package com.transmission.transmission.exception;

/**
 * 自定义异常
 * 
 * @author ydw
 * @email yaodou@360yaopin.com
 * @date 2018年7月19日
 */
public class OtherException extends RuntimeException {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
    private String code = "1000";
    
    public OtherException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public OtherException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public OtherException(String msg, String code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}
	
	public OtherException(String msg, String code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
