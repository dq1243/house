package com.dq.house.web.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dq.house.model.entity.ApartmentInfo;
import com.dq.house.web.admin.vo.apartment.ApartmentDetailVo;
import com.dq.house.web.admin.vo.apartment.ApartmentItemVo;
import com.dq.house.web.admin.vo.apartment.ApartmentQueryVo;
import com.dq.house.web.admin.vo.apartment.ApartmentSubmitVo;

import java.util.List;

/**
 * @author liubo
 * @description 针对表【apartment_info(公寓信息表)】的数据库操作Service
 * @createDate 2023-07-24 15:48:00
 */
public interface ApartmentInfoService extends IService<ApartmentInfo> {

    /**
     * 保存或更新公寓信息
     *
     * @param apartmentSubmitVo
     */
    void saveOrUpdateApartmentInfo(ApartmentSubmitVo apartmentSubmitVo);

    /**
     * 根据条件分页查询公寓列表
     * @param page 分页参数
     * @param queryVo 查询条件参数
     * @return 分页结果
     */
    IPage<ApartmentItemVo> pageItem(Page<ApartmentItemVo> page, ApartmentQueryVo queryVo);

    /**
     * 根据ID获取公寓详细信息
     * @param id 公寓ID
     * @return 公寓详细信息
     */
    ApartmentDetailVo getApartmentInfoById(Long id);

    /**
     * 根据公寓id删除公寓信息
     * @param id
     */
    void removeByApartmentId(Long id);
}
