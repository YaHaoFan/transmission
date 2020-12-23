package com.transmission.transmission.service.impl;



import com.transmission.transmission.service.DataCenterService;
import org.springframework.stereotype.Service;

import javax.jws.WebService;


@Service
@WebService(name = "DataCenter" ,targetNamespace ="http://service.transmission.transmission.com/" ,endpointInterface = "com.transmission.transmission.service.DataCenterService")
public class DataCenterServiceImpl implements DataCenterService {

	/**
	 * 登录接口
	 */
	@Override
	public String Login(String username, String password, String verifycode) {
	   return "sadas";
	}


}