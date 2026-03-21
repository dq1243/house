package com.dq.house.web.app.mapper;

import com.dq.house.model.entity.PaymentType;
import com.dq.house.model.entity.RoomPaymentType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author liubo
 * @description 针对表【room_payment_type(房间&支付方式关联表)】的数据库操作Mapper
 * @createDate 2023-07-26 11:12:39
 * @Entity com.dq.house.model.entity.RoomPaymentType
 */
public interface RoomPaymentTypeMapper extends BaseMapper<RoomPaymentType> {

    /**
     * 根据房间id获取可选支付方式列表
     *
     * @param id
     * @return
     */
    List<PaymentType> getListByRoomId(Long id);
}