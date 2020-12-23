package com.transmission.transmission.controller;

import com.transmission.transmission.service.TransmissionService;
import com.transmission.transmission.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/transmission")
public class TransmissionController {
    @Autowired
    private TransmissionService transmissionService;

    /**
     * 自动读取河北单体
     */
//    @Scheduled(fixedRate = 300000)
    public void fixedRate() {
        transmissionService.fixedRate();
    }

    /**
     * 接收图纸
     *
     * @param file
     * @param code
     */
    @RequestMapping("callback")
    public Result callback(@RequestParam("file") MultipartFile file, @RequestParam String code) {
       return transmissionService.callback(file,code);
    }

    /**
     * 请求图纸解析系统
     */

    @Async
//    @Scheduled(fixedRate = 300000)
    @RequestMapping("httpRequest")
    public void httpRequest(){
        transmissionService.httpRequest();
    }



    @RequestMapping("httpRequests")
    public String httpRequests(){
        return "0000";
    }


    @RequestMapping("downloadS")
    public HttpServletResponse downloadS(HttpServletResponse response) {
        try {
            String decoded = "D:\\桦南县桃源农机加油站新建项目.zip";
            // path是指欲下载的文件的路径。
            File file = new File(decoded);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(decoded));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + filename.toString() + "\"");
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }

}
