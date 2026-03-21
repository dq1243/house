package com.dq.house.web.app.service.impl;

import com.dq.house.model.entity.PaymentType;
import com.dq.house.web.app.mapper.PaymentTypeMapper;
import com.dq.house.web.app.mapper.RoomPaymentTypeMapper;
import com.dq.house.web.app.service.PaymentTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liubo
 * @description 针对表【payment_type(支付方式表)】的数据库操作Service实现
 * @createDate 2023-07-26 11:12:39
 */
@Service
public class PaymentTypeServiceImpl extends ServiceImpl<PaymentTypeMapper, PaymentType>
        implements PaymentTypeService {

    @Autowired
    private RoomPaymentTypeMapper roomPaymentTypeMapper;

    /**
     * 根据房间id获取可选支付方式列表
     *
     * @param id
     * @return
     */
    @Override
    public List<PaymentType> getListByRoomId(Long id) {
        return roomPaymentTypeMapper.getListByRoomId(id);
    }
}




