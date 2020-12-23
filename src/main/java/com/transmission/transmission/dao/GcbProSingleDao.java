package com.transmission.transmission.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.transmission.transmission.entity.GcbProjSingle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;


@Mapper
public interface GcbProSingleDao extends BaseMapper<GcbProjSingle> {

    /**
     * 添加
     * @param singleEntity
     */
    void insertSingle(GcbProjSingle singleEntity);

    /**
     * 修改
     * @param parmMap
     */
    void updateSingle(@Param("parmMap") Map<String,Object> parmMap);
}
