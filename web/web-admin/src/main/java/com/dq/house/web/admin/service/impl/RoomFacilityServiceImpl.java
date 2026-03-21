package com.dq.house.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dq.house.model.entity.RoomFacility;
import com.dq.house.web.admin.mapper.RoomFacilityMapper;
import com.dq.house.web.admin.service.RoomFacilityService;
import com.dq.house.web.admin.vo.room.RoomSubmitVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author liubo
* @description 针对表【room_facility(房间&配套关联表)】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class RoomFacilityServiceImpl extends ServiceImpl<RoomFacilityMapper, RoomFacility>
    implements RoomFacilityService {

}




