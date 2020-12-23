package com.transmission.transmission.config;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.sf.json.JSONObject;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParser {

    public static JSONObject getAllUserNames(File file) {
        JSONObject json = new JSONObject();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            if (file.exists()) {
                Document doc = db.parse(file);
                Element docEle = doc.getDocumentElement();
                String s = callWriteXmlString(doc, null);
                json.put("originalXml", s);
                // Print root element of the document
                System.out.println("Root element of the document: "
                        + docEle.getNodeName());

                NodeList studentList = docEle.getElementsByTagName("record");

                NodeList errList = docEle.getElementsByTagName("error");
                System.out
                        .println("Total errList: " + errList.getLength());
                // Print total student elements in document
                System.out
                        .println("Total students: " + studentList.getLength());
                file.delete();
                if (errList != null && errList.getLength() > 0) {
                    for (int i = 0; i < errList.getLength(); i++) {
                        Node node = errList.item(i);
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element e = (Element) node;
                            NodeList nodeList = e.getElementsByTagName("type");
                              //标签中的属性值
//                            String name = e.getAttribute("type");
                            if(nodeList.item(0)!=null){
                                json.put("type", "4");
                            }
                        }
                    }
                    json.put("errList", "0");
                    return json;
                }
                List<Map<String,Object>> doorList = new ArrayList<Map<String,Object>>();
                List<Map<String,Object>> windowList = new ArrayList<Map<String,Object>>();
                if(studentList.getLength()<=0){
                    json.put("studentList","0");
                    return json;
                }
                int doorNum = 0;
                int windowNum = 0;
                if (studentList != null && studentList.getLength() > 0) {

                    for (int i = 0; i < studentList.getLength(); i++) {

                        Node node = studentList.item(i);

                        if (node.getNodeType() == Node.ELEMENT_NODE) {

                            System.out
                                    .println("=====================");
                            Element e = (Element) node;
                            NodeList nodeList = e.getElementsByTagName("type");
                            String type = "";
                            if(nodeList.item(0).getChildNodes().item(0)!=null){
                                type = nodeList.item(0).getChildNodes().item(0).getNodeValue();
                            }
                            System.out.println("type: " + type);

                            nodeList = e.getElementsByTagName("code");
                            String code = "";
                            if(nodeList.item(0).getChildNodes().item(0)!=null){
                                code =nodeList.item(0).getChildNodes().item(0).getNodeValue();
                            }
                            System.out.println("code: "+code);
                            nodeList = e.getElementsByTagName("total");
                            String total = "0";
                            if(nodeList.item(0).getChildNodes().item(0)!=null){
                                total = nodeList.item(0).getChildNodes().item(0).getNodeValue();
                                if(!isNumeric(total)){
                                    total = "0";
                                }
                            }
                            System.out.println("total: "+ total);

                            boolean door = type.contains("门");
                            boolean window = type.contains("窗");
                            if(door){
                                int d =  Integer.parseInt(total);
                                doorNum =  doorNum + d;
                                Map<String,Object>  map = new HashMap<>();
                                map.put("type",type);
                                map.put("code",code);
                                map.put("total",total);
                                doorList.add(map);
                            }
                            if(window){
                                int w =  Integer.parseInt(total);
                                windowNum =  windowNum + w;
                                Map<String,Object>  map = new HashMap<>();
                                map.put("type",type);
                                map.put("code",code);
                                map.put("total",total);
                                windowList.add(map);
                            }


                        }
                    }
                } else {
                    System.exit(1);
                }
                json.put("doorNum",doorNum);
                json.put("windowNum",windowNum);
                json.put("doorList",doorList);
                json.put("windowList",windowList);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return json;
    }
    public static File multipartFileToFile(MultipartFile file) throws Exception {

        File toFile = null;

        if (file.equals("") || file.getSize() <= 0) {

            file = null;

        } else {

            InputStream ins = null;

            ins = file.getInputStream();

            toFile = new File(file.getOriginalFilename());

            inputStreamToFile(ins, toFile);

            ins.close();

        }

        return toFile;

    }

    public static void inputStreamToFile(InputStream ins, File file) {
        try {

            OutputStream os = new FileOutputStream(file);

            int bytesRead = 0;

            byte[] buffer = new byte[8192];

            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {

                os.write(buffer, 0, bytesRead);

            }

            os.close();

            ins.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // 将Document内容 写入XML字符串并返回
    private static String callWriteXmlString(Document doc, String encoding) {

        try {
            Source source = new DOMSource(doc);
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            OutputStreamWriter write = new OutputStreamWriter(outStream);
            Result result = new StreamResult(write);

            Transformer xformer = TransformerFactory.newInstance()
                    .newTransformer();
            xformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

//            xformer.transform(source, result);
            return outStream.toString();

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            return null;
        } catch (TransformerException e) {
            e.printStackTrace();
            return null;
        }

    }

//    public static void main(String[] args) {
//        XMLParser parser = new XMLParser();
//        File flie = new File("C:\\Users\\Admin\\Desktop\\333.xml");
//        parser.getAllUserNames(flie);
//    }
    public static void main(String[] args) {
        System.out.println(isNumeric("L"));
    }
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}