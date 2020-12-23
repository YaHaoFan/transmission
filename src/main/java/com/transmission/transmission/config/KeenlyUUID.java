package com.transmission.transmission.config;

import java.util.Random;
import java.util.UUID;

public class KeenlyUUID {
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
        return uuid;
    }
    
    
    public static String getFileName() {
    	Long time=System.currentTimeMillis();
    	return time+KeenlyUUID.getRandomString(3);
    	
    }
    
    
    public static String getRandomString(int length){
        String str="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
          int number=random.nextInt(11);
          sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
