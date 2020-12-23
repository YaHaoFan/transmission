package com.transmission.transmission.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.transmission.transmission.entity.GcbProjSingle;
import com.transmission.transmission.entity.GcbProject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GcbProjectDao extends BaseMapper<GcbProject> {

	/**
	 * 查询小蜜蜂数据
	 * @return
	 */
	List<GcbProject> selectProjectList();

	List<GcbProjSingle> selectGcbProjSingle(String dataId,String gcbProjectId);
}
