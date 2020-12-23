package com.transmission.transmission.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.transmission.transmission.config.HttpRequest;
import com.transmission.transmission.config.KeenlyUUID;
import com.transmission.transmission.config.XMLParser;
import com.transmission.transmission.dao.GcbProSingleDao;
import com.transmission.transmission.dao.GcbProjectDao;
import com.transmission.transmission.entity.GcbProjSingle;
import com.transmission.transmission.entity.GcbProject;
import com.transmission.transmission.service.TransmissionService;
import com.transmission.transmission.utils.Result;
import com.transmission.transmission.utils.UtilValidate;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

@Service
public class TransmissionServiceImpl  implements TransmissionService {
    private static final Log log = LogFactory.getLog(TransmissionServiceImpl.class);
    @Autowired
    private GcbProjectDao gcbProjectDao;
    @Autowired
    private GcbProSingleDao gcbProSingleDao;
    @Value("${httpRequest.downloadurl}")
    private String downloadurl;
    @Value("${httpRequest.uploadurl}")
    private String uploadurl;
    @Value("${httpRequest.requestUrl}")
    private String requestUrl;
    @Override
    public void fixedRate() {
        EntityWrapper<GcbProject> sysUser = new EntityWrapper<GcbProject>();
        sysUser.isNotNull("data_ids");
        sysUser.isNull("data_status");
        List<GcbProject> gcbList = gcbProjectDao.selectList(sysUser);
        for (GcbProject gcb : gcbList) {
            String dataIds = gcb.getDataIds();
            if (dataIds.equals("")) {
                continue;
            }
            String[] arr = dataIds.split(",");
            String projType = gcb.getProjType();
            boolean status = projType.contains("11010");
            if(status){
                projType = "ff9ac67697494fef9d9ca436a1f1bbbc";
            }else{
                projType = "ff9ac67697494fef9d9ca436a1f1bbbc_sz";
            }

            for (String dataId : arr) {
                GcbProjSingle singleEntity = new GcbProjSingle();
                List<GcbProjSingle> single = gcbProjectDao.selectGcbProjSingle(dataId, gcb.getId());
                if (single.size() == 0) {
                    String uuid = KeenlyUUID.getUUID();
                    singleEntity.setId(uuid);
                    singleEntity.setDataId(dataId);
                    singleEntity.setProjId(gcb.getId());
                    singleEntity.setCreateDate(new Date());
                    singleEntity.setProjType(projType);
                    gcbProSingleDao.insertSingle(singleEntity);
                    log.info("河北单体数据code=="+uuid);
                }

            }

            gcb.setDataStatus("0");
            gcbProjectDao.updateById(gcb);
        }
    }

    @Override
    public Result callback(MultipartFile file, String code) {
        GcbProjSingle single = new GcbProjSingle();
        single.setId(code);
        single.setResponseDate(new Date());
        File file1 = null;
        try {
            log.info("接受图纸系统code=="+code);
            file1 = XMLParser.multipartFileToFile(file);
            JSONObject allUserNames = XMLParser.getAllUserNames(file1);
            if (UtilValidate.isNotEmpty(allUserNames)) {
                if("0".equals(allUserNames.get("studentList"))){
                    log.info("文件解析没有门窗表code=="+code);
                    single.setResponseError("文件解析没有门窗表");
                    single.setResponseStatus("2");
                    gcbProSingleDao.updateById(single);
                    return Result.ok();
                }
                String originalXml = (String) allUserNames.get("originalXml");
                if("0".equals(allUserNames.get("errList"))){
                    if("4".equals(allUserNames.get("type"))){
                        log.info("文件下载为空code=="+code);
                        single.setResponseError("文件下载为空");
                        single.setResponseStatus("4");
                    }else{
                        log.info("文件解析失败code=="+code);
                        single.setResponseError("文件解析失败");
                        single.setResponseStatus("3");
                    }

                    single.setData(originalXml);
                    gcbProSingleDao.updateById(single);
                    return Result.ok();
                }
                single.setData(originalXml);
                single.setDoorNum((Integer) allUserNames.get("doorNum"));
                single.setWindowNum((Integer) allUserNames.get("windowNum"));
                single.setDoorData(allUserNames.get("doorList").toString());
                single.setWindowData(allUserNames.get("windowList").toString());
                single.setResponseStatus("0");
                gcbProSingleDao.updateById(single);
                log.info("code=="+code+"接受图纸成功！");

                GcbProjSingle single1 = gcbProSingleDao.selectById(code);
                EntityWrapper<GcbProjSingle> singen = new EntityWrapper<GcbProjSingle>();
                singen.eq("proj_id",single1.getProjId());
                List<GcbProjSingle> gcbProjSingles = gcbProSingleDao.selectList(singen);
                int doorNum = 0;
                int windowNum = 0;
                for(GcbProjSingle sin : gcbProjSingles){
                    if(sin.getDoorNum()!=null){
                        doorNum = doorNum + sin.getDoorNum();
                    }
                    if(sin.getWindowNum()!=null){
                        windowNum = windowNum + sin.getWindowNum();
                    }
                }
                GcbProject gcb = new GcbProject();
                gcb.setId(single1.getProjId());
                gcb.setDoorNum(doorNum);
                gcb.setWindowNum(windowNum);
                gcbProjectDao.updateById(gcb);
                return Result.ok();
            }else {
                log.info("文件解析失败："+allUserNames.toString());
                single.setResponseError("文件解析失败："+allUserNames.toString());
                single.setResponseStatus("1");
                gcbProSingleDao.updateById(single);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("接受图纸异常");
        }
        return Result.error("接受图纸失败");
    }

    @Override
    public void httpRequest() {
        EntityWrapper<GcbProjSingle> gcbWrapper = new EntityWrapper<GcbProjSingle>();
        gcbWrapper.isNull("request_status");
        List<GcbProjSingle> gcbList = gcbProSingleDao.selectList(gcbWrapper);
        for (GcbProjSingle gcb : gcbList) {
            String download = downloadurl.replace("code", gcb.getDataId());
            if(!"".equals(gcb.getProjType())&&gcb.getProjType()!=null){
                download = download+"&specialtyId="+gcb.getProjType();
            }
            String code = "code="+"\""+gcb.getId()+"\"";
            log.info("请求图纸解析系统code=="+code);
            JSONObject jsonObject =  HttpRequest.doGet(requestUrl+"?"+code+"&downloadurl="+"\""+HttpRequest.encoded(download)+"\""+"&uploadurl="+"\""+HttpRequest.encoded(uploadurl)+"\"");
            System.out.println("请求图纸系统返回结果:"+jsonObject+code);
            GcbProjSingle single = new GcbProjSingle();
            single.setId(gcb.getId());
            single.setRequestDate(new Date());
            if(jsonObject==null){
                single.setRequestStatus("1");
                single.setResponseError("请求图纸解析系统返回信息为空");
                log.info("请求图纸解析系统返回信息为空");
            }else{
                if("success".equals(jsonObject.get("result"))){
                    single.setRequestStatus("0");
                }else{
                    single.setRequestStatus("1");
                    single.setResponseError("请求图纸解析系统返回失败"+jsonObject.toString());
                    log.info("请求图纸解析系统返回失败"+jsonObject.toString());
                }
            }
            gcbProSingleDao.updateById(single);
        }
    }
}
