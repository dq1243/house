package com.dq.house.web.app.mapper;

import com.dq.house.model.entity.ApartmentInfo;
import com.dq.house.web.app.vo.apartment.ApartmentDetailVo;
import com.dq.house.web.app.vo.apartment.ApartmentItemVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.math.BigDecimal;

/**
* @author liubo
* @description 针对表【apartment_info(公寓信息表)】的数据库操作Mapper
* @createDate 2023-07-26 11:12:39
* @Entity com.dq.house.model.entity.ApartmentInfo
*/
public interface ApartmentInfoMapper extends BaseMapper<ApartmentInfo> {

    /**
     * 根据预约ID查询预约看房的公寓信息
     * @param id
     * @return
     */
    ApartmentItemVo getAppointDetailVo(Long id);

    /**
     * 根据id获取公寓信息详情
     * @param id 公寓id
     * @return apartmentDetailVo 公寓详情信息
     */
    ApartmentDetailVo getDetailById(Long id);

    /**
     * 根据id获取公寓详情信息
     * @return
     */
    ApartmentItemVo getApartmentItemVoById(Long id);

    /**
     * 根据公寓id查询该公寓的最低租金
     * @param apartmentId
     * @return
     */
    BigDecimal getMinRent(Long apartmentId);
}




