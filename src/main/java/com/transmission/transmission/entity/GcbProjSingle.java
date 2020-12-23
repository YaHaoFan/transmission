package com.transmission.transmission.entity;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName("gcb_proj_single")
public class GcbProjSingle implements Serializable {
  private static final long serialVersionUID = 1L;
  @TableId
  private String id;
  private String projId;
  private String dataId;
  private String data;
  private String name;
  private String doorData;
  private Integer doorNum;
  private String windowData;
  private Integer windowNum;
  private String createBy;
  private String requestStatus;
  private String responseError;
  private String responseStatus;
  private Date createDate;
  private Date updateDate;
  private Date requestDate;
  private Date responseDate;
  private String projType;
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getProjId() {
    return projId;
  }

  public void setProjId(String projId) {
    this.projId = projId;
  }

  public String getDataId() {
    return dataId;
  }

  public void setDataId(String dataId) {
    this.dataId = dataId;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDoorData() {
    return doorData;
  }

  public void setDoorData(String doorData) {
    this.doorData = doorData;
  }

  public String getWindowData() {
    return windowData;
  }

  public void setWindowData(String windowData) {
    this.windowData = windowData;
  }

  public String getCreateBy() {
    return createBy;
  }

  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  }

  public String getRequestStatus() {
    return requestStatus;
  }

  public void setRequestStatus(String requestStatus) {
    this.requestStatus = requestStatus;
  }

  public String getResponseError() {
    return responseError;
  }

  public void setResponseError(String responseError) {
    this.responseError = responseError;
  }

  public Integer getDoorNum() {
    return doorNum;
  }

  public void setDoorNum(Integer doorNum) {
    this.doorNum = doorNum;
  }

  public Integer getWindowNum() {
    return windowNum;
  }

  public void setWindowNum(Integer windowNum) {
    this.windowNum = windowNum;
  }

  public String getResponseStatus() {
    return responseStatus;
  }

  public void setResponseStatus(String responseStatus) {
    this.responseStatus = responseStatus;
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }

  public Date getRequestDate() {
    return requestDate;
  }

  public void setRequestDate(Date requestDate) {
    this.requestDate = requestDate;
  }

  public Date getResponseDate() {
    return responseDate;
  }

  public void setResponseDate(Date responseDate) {
    this.responseDate = responseDate;
  }

  public String getProjType() {
    return projType;
  }

  public void setProjType(String projType) {
    this.projType = projType;
  }

  @Override
  public String toString() {
    return "GcbProjSingle{" +
            "id='" + id + '\'' +
            ", projId='" + projId + '\'' +
            ", dataId='" + dataId + '\'' +
            ", data='" + data + '\'' +
            ", name='" + name + '\'' +
            ", doorData='" + doorData + '\'' +
            ", windowData='" + windowData + '\'' +
            ", createBy='" + createBy + '\'' +
            ", requestStatus='" + requestStatus + '\'' +
            ", responseError='" + responseError + '\'' +
            ", responseStatus='" + responseStatus + '\'' +
            ", projType='" + projType + '\'' +
            '}';
  }
}
