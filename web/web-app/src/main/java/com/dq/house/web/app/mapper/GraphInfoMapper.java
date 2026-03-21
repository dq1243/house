package com.dq.house.web.app.mapper;

import com.dq.house.model.entity.GraphInfo;
import com.dq.house.model.enums.ItemType;
import com.dq.house.web.app.vo.graph.GraphVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【graph_info(图片信息表)】的数据库操作Mapper
* @createDate 2023-07-26 11:12:39
* @Entity com.dq.house.model.entity.GraphInfo
*/
public interface GraphInfoMapper extends BaseMapper<GraphInfo> {

    /**
     * 根据房间id获取图片列表
     *
     * @param id
     * @param itemType
     * @return
     */
    List<GraphVo> getListByRoomId(Long id, ItemType itemType);
}




