package com.dq.house.web.app.mapper;

import com.dq.house.model.entity.LabelInfo;
import com.dq.house.model.entity.RoomLabel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【room_label(房间&标签关联表)】的数据库操作Mapper
* @createDate 2023-07-26 11:12:39
* @Entity com.dq.house.model.entity.RoomLabel
*/
public interface RoomLabelMapper extends BaseMapper<RoomLabel> {

    /**
     * 根据房间id获取标签列表
     * @param id
     * @return
     */
    List<LabelInfo> getLabelListByRoomId(Long id);
}




