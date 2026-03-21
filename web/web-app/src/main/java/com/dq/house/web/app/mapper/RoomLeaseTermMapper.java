package com.dq.house.web.app.mapper;

import com.dq.house.model.entity.LeaseTerm;
import com.dq.house.model.entity.RoomLeaseTerm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【room_lease_term(房间租期管理表)】的数据库操作Mapper
* @createDate 2023-07-26 11:12:39
* @Entity com.dq.house.model.entity.RoomLeaseTerm
*/
public interface RoomLeaseTermMapper extends BaseMapper<RoomLeaseTerm> {

    /**
     * 根据房间id查询租期列表
     * @param id
     * @return
     */
    List<LeaseTerm> getListByRoomId(Long id);
}




