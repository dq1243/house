package com.dq.house.web.app.service.impl;

import com.dq.house.common.login.LoginUserHolder;
import com.dq.house.model.entity.ViewAppointment;
import com.dq.house.web.app.mapper.ApartmentInfoMapper;
import com.dq.house.web.app.mapper.ViewAppointmentMapper;
import com.dq.house.web.app.service.ViewAppointmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dq.house.web.app.vo.apartment.ApartmentItemVo;
import com.dq.house.web.app.vo.appointment.AppointmentDetailVo;
import com.dq.house.web.app.vo.appointment.AppointmentItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liubo
 * @description 针对表【view_appointment(预约看房信息表)】的数据库操作Service实现
 * @createDate 2023-07-26 11:12:39
 */
@Service
public class ViewAppointmentServiceImpl extends ServiceImpl<ViewAppointmentMapper, ViewAppointment>
        implements ViewAppointmentService {

    @Autowired
    private ViewAppointmentMapper viewAppointmentMapper;
    @Autowired
    private ApartmentInfoMapper apartmentInfoMapper;

    /**
     * 获取个人预约看房列表
     *
     * @return
     */
    @Override
    public List<AppointmentItemVo> listItem() {
        Long userId = LoginUserHolder.getLoginUser().getUserId();
        return viewAppointmentMapper.listItem(userId);
    }

    /**
     * 根据ID查询预约详情信息
     *
     * @param id
     * @return
     */
    @Override
    public AppointmentDetailVo getDetailById(Long id) {
        AppointmentDetailVo appointmentDetailVo = viewAppointmentMapper.getDetailById(id);
        ApartmentItemVo apartmentItemVo = apartmentInfoMapper.getAppointDetailVo(appointmentDetailVo.getApartmentId());
        appointmentDetailVo.setApartmentItemVo(apartmentItemVo);
        return appointmentDetailVo;
    }
}




