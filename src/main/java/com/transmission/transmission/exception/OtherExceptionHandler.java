package com.transmission.transmission.exception;

import java.net.ConnectException;

import com.transmission.transmission.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.NoHandlerFoundException;




/**
 * 异常处理器
 * 
 * @author shengwu
 * @date 2020年3月17日
 */
@Component
@RestControllerAdvice
public class OtherExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(OtherException.class)
	public Result handleRRException(OtherException e){
		Result r = new Result();
		r.put("code", e.getCode());
		r.put("msg", e.getMessage());
		return r;
		 
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public Result handlerNoFoundException(Exception e) {
		logger.error(e.getMessage(), e);
		return Result.error("404", "路径不存在，请检查路径是否正确");
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public Result handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return Result.error("数据库中已存在该记录");
	}

	@ExceptionHandler(Exception.class)
	public Result handleException(Exception e){
		logger.error(e.getMessage(), e);
		return Result.error();
	}
	
	@ExceptionHandler(ConnectException.class)
	public Result connectException(ConnectException e){
		logger.error(e.getMessage(), e);
		return Result.error("系统调用异常");
	}
	@ExceptionHandler(ResourceAccessException.class)
	public Result connectException(ResourceAccessException e){
		logger.error(e.getMessage(), e);
		return Result.error("系统之间调用异常");
	}
	
}


