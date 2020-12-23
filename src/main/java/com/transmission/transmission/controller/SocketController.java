package com.transmission.transmission.controller;

import com.transmission.transmission.service.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SocketController {

    @Autowired
    private WebSocketServer webSocketServer;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/webSocket")
    public ModelAndView socket() {
        ModelAndView mav=new ModelAndView("/webSocket.html");
//        mav.addObject("userId", "11");
        return mav;
    }
    @GetMapping("/info")
    public void info(String name) {
        webSocketServer.sendInfo(name,"收到服务端信息。。。。");
    }
}