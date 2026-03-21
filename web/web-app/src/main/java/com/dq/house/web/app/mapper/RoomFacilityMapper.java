package com.dq.house.web.app.mapper;

import com.dq.house.model.entity.FacilityInfo;
import com.dq.house.model.entity.RoomFacility;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【room_facility(房间&配套关联表)】的数据库操作Mapper
* @createDate 2023-07-26 11:12:39
* @Entity com.dq.house.model.entity.RoomFacility
*/
public interface RoomFacilityMapper extends BaseMapper<RoomFacility> {

    /**
     * 根据房间id获取配套信息列表
     * @param id
     * @return
     */
    List<FacilityInfo> getFacilityListByRoomId(Long id);
}




