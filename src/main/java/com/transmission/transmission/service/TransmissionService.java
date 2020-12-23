package com.transmission.transmission.service;


import com.transmission.transmission.utils.Result;
import org.springframework.web.multipart.MultipartFile;

public interface TransmissionService {
    /**
     * 自动读取河北单体
     */
    void fixedRate();
    /**
     * 接受图纸接口
     */
    Result callback(MultipartFile file,String code);
    /**
     * 请求图纸解析系统
     */
    void httpRequest();

}
