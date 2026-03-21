package com.dq.house.web.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dq.house.model.entity.ViewAppointment;
import com.dq.house.web.admin.mapper.ViewAppointmentMapper;
import com.dq.house.web.admin.service.ViewAppointmentService;
import com.dq.house.web.admin.vo.appointment.AppointmentQueryVo;
import com.dq.house.web.admin.vo.appointment.AppointmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 * @description 针对表【view_appointment(预约看房信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class ViewAppointmentServiceImpl extends ServiceImpl<ViewAppointmentMapper, ViewAppointment>
        implements ViewAppointmentService {

    @Autowired
    private ViewAppointmentMapper viewAppointmentMapper;

    /**
     * 分页查询预约信息
     * @param page
     * @param queryVo
     * @return
     */
    @Override
    public IPage<AppointmentVo> itemPage(Page page, AppointmentQueryVo queryVo) {
        return viewAppointmentMapper.itemPage(page, queryVo);
    }
}




