package com.dq.house.web.app.service.impl;

import com.dq.house.model.entity.ApartmentInfo;
import com.dq.house.web.app.mapper.ApartmentInfoMapper;
import com.dq.house.web.app.service.ApartmentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dq.house.web.app.vo.apartment.ApartmentDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 * @description 针对表【apartment_info(公寓信息表)】的数据库操作Service实现
 * @createDate 2023-07-26 11:12:39
 */
@Service
public class ApartmentInfoServiceImpl extends ServiceImpl<ApartmentInfoMapper, ApartmentInfo>
        implements ApartmentInfoService {

    @Autowired
    private ApartmentInfoMapper apartmentInfoMapper;

    /**
     * 根据id获取公寓信息
     *
     * @param id 公寓id
     * @return apartmentDetailVo 公寓详情信息
     */
    @Override
    public ApartmentDetailVo getDetailById(Long id) {
        return apartmentInfoMapper.getDetailById(id);
    }
}




