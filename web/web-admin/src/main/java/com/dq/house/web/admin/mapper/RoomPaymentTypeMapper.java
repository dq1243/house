package com.dq.house.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dq.house.model.entity.PaymentType;
import com.dq.house.model.entity.RoomPaymentType;

import java.util.List;

/**
* @author liubo
* @description 针对表【room_payment_type(房间&支付方式关联表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.dq.house.model.RoomPaymentType
*/
public interface RoomPaymentTypeMapper extends BaseMapper<RoomPaymentType> {

    /**
     * 根据房间id获取支付方式列表
     * @param id
     */
    List<PaymentType> getListByRoomId(Long id);
}




