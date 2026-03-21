package com.dq.house.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dq.house.model.entity.LabelInfo;
import com.dq.house.model.entity.RoomLabel;

import java.util.List;

/**
* @author liubo
* @description 针对表【room_label(房间&标签关联表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.dq.house.model.RoomLabel
*/
public interface RoomLabelMapper extends BaseMapper<RoomLabel> {

    /**
     * 根据房间id获取关联的标签列表
     * @param id 房间id
     * @return 标签列表
     */
    List<LabelInfo> getListByRoomId(Long id);
}




