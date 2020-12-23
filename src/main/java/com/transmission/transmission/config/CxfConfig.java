package com.transmission.transmission.config;


import com.transmission.transmission.service.DataCenterService;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

//import com.platform.web.webService.server.Interceptor.AuthInterceptor;

@Configuration
public class CxfConfig {
    
    @Autowired
    private DataCenterService DataCenterServiceImpl;
    /**
     * 为了不被springSecurity拦截，自定义暴露的路径
     * 默认为/services/**
     */
    @Bean
    public ServletRegistrationBean<CXFServlet> myServlet(){
        ServletRegistrationBean<CXFServlet> registrationBean =new ServletRegistrationBean<CXFServlet>(new CXFServlet(),"/service/*");
        return registrationBean;
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    //终端路径
    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), DataCenterServiceImpl);
   //     endpoint.getInInterceptors().add(new AuthInterceptor());//添加校验拦截器
        endpoint.publish("/dataShare");
        return endpoint;
    }
}