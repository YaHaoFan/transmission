package com.transmission.transmission.service;


import org.apache.cxf.annotations.WSDLDocumentation;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.io.UnsupportedEncodingException;

@WebService
public interface DataCenterService {

    @WebMethod
	@WSDLDocumentation( "登录接口，返回JSON格式数据 如：{code:'SUCCESS',userName:'admin',userId:'1759a2fc3532f1dfd0278f32df02569c'} 如果未登录成功 code为ERROR")
    public String Login(@WebParam(name = "username") String username, @WebParam(name = "password") String password, @WebParam(name = "verifycode") String verifycode)throws UnsupportedEncodingException;

}