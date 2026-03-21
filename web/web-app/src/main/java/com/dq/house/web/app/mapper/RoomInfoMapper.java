package com.dq.house.web.app.mapper;

import com.dq.house.model.entity.RoomInfo;
import com.dq.house.model.enums.ItemType;
import com.dq.house.web.app.vo.attr.AttrValueVo;
import com.dq.house.web.app.vo.room.RoomItemVo;
import com.dq.house.web.app.vo.room.RoomQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.math.BigDecimal;
import java.util.List;

/**
* @author liubo
* @description 针对表【room_info(房间信息表)】的数据库操作Mapper
* @createDate 2023-07-26 11:12:39
* @Entity com.dq.house.model.entity.RoomInfo
*/
public interface RoomInfoMapper extends BaseMapper<RoomInfo> {

    /**
     * 分页查询房间列表
     * @param page
     * @param queryVo
     * @return
     */
    IPage<RoomItemVo> pageItem(Page<RoomItemVo> page, RoomQueryVo queryVo);

    /**
     * 根据公寓id分页查询房间列表
     * @param page
     * @param id
     * @return
     */
    IPage<RoomItemVo> pageItemByApartmentId(Page<RoomItemVo> page, Long id);
}