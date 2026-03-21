package com.dq.house.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dq.house.model.entity.FacilityInfo;
import com.dq.house.model.enums.ItemType;

import java.util.List;

/**
* @author liubo
* @description 针对表【facility_info(配套信息表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.dq.house.model.FacilityInfo
*/
public interface FacilityInfoMapper extends BaseMapper<FacilityInfo> {

    /**
     * 根据公寓ID获取配套列表
     * @param id
     * @return
     */
    List<FacilityInfo> getListByApartmentId(Long id);

    /**
     * 根据公寓ID删除配套信息
     *
     * @param id
     */
    void removeByApartmentId(Long id);

}




