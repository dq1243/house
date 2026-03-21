package com.dq.house.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dq.house.model.entity.LeaseTerm;
import com.dq.house.model.entity.RoomLeaseTerm;

import java.util.List;

/**
* @author liubo
* @description 针对表【room_lease_term(房间租期管理表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.dq.house.model.RoomLeaseTerm
*/
public interface RoomLeaseTermMapper extends BaseMapper<RoomLeaseTerm> {


    /**
     * 根据房间id获取租期列表
     * @param id
     */
    List<LeaseTerm> getListByRoomId(Long id);
}




