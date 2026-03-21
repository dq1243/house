package com.dq.house.web.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dq.house.model.entity.RoomInfo;
import com.dq.house.web.admin.vo.room.RoomDetailVo;
import com.dq.house.web.admin.vo.room.RoomItemVo;
import com.dq.house.web.admin.vo.room.RoomQueryVo;
import com.dq.house.web.admin.vo.room.RoomSubmitVo;

/**
* @author liubo
* @description 针对表【room_info(房间信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface RoomInfoService extends IService<RoomInfo> {

    /**
     * 保存或更新房间信息
     *
     * @param roomSubmitVo 房间提交对象，包含房间基本信息和相关属性
     */
    void saveOrUpdateRoomInfo(RoomSubmitVo roomSubmitVo);

    /**
     * 根据条件分页查询房间列表
     *
     * @param page
     * @param queryVo
     * @return 分页结果对象，包含房间列表和分页信息
     */
    IPage<RoomItemVo> pageItem(Page<RoomItemVo> page, RoomQueryVo queryVo);

    /**
     * 根据id获取房间详细信息
     * @param id 房间id
     * @return 房间详细信息对象，包含房间基本信息和相关属性
     */
    RoomDetailVo getDetailById(Long id);

    /**
     * 根据id删除房间信息
     * @param id
     */
    void removeRoomInfoById(Long id);
}