package com.dq.house.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dq.house.model.entity.GraphInfo;
import com.dq.house.model.enums.ItemType;
import com.dq.house.web.admin.vo.graph.GraphVo;

import java.util.List;

/**
* @author liubo
* @description 针对表【graph_info(图片信息表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.dq.house.model.GraphInfo
*/
public interface GraphInfoMapper extends BaseMapper<GraphInfo> {

    /**
     * 根据公寓ID获取图片列表
     * @param id 公寓ID
     * @return 图片列表
     */
    List<GraphVo> getList(Long id, ItemType type);

    /**
     * 根据公寓id删除图片信息
     * @param id
     * @param type
     */
    void removeByApartmentId(Long id, ItemType type);
}




