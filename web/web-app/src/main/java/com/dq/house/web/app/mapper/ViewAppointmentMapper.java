package com.dq.house.web.app.mapper;

import com.dq.house.model.entity.ViewAppointment;
import com.dq.house.web.app.vo.appointment.AppointmentDetailVo;
import com.dq.house.web.app.vo.appointment.AppointmentItemVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【view_appointment(预约看房信息表)】的数据库操作Mapper
* @createDate 2023-07-26 11:12:39
* @Entity com.dq.house.model.entity.ViewAppointment
*/
public interface ViewAppointmentMapper extends BaseMapper<ViewAppointment> {

    /**
     * 获取个人预约看房列表
     * @return
     */
    List<AppointmentItemVo> listItem(Long userId);

    /**
     * 根据ID查询预约详情信息
     * @param id
     * @return
     */
    AppointmentDetailVo getDetailById(Long id);
}




