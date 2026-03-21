package com.dq.house.web.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dq.house.model.entity.LeaseAgreement;
import com.dq.house.web.admin.vo.agreement.AgreementQueryVo;
import com.dq.house.web.admin.vo.agreement.AgreementVo;

/**
* @author liubo
* @description 针对表【lease_agreement(租约信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface LeaseAgreementService extends IService<LeaseAgreement> {

    /**
     * 根据条件分页查询租约列表
     * @param page
     * @param queryVo
     * @return
     */
    IPage<AgreementVo> itemPage(Page page, AgreementQueryVo queryVo);

    /**
     * 根据id查询租约信息
     * @param id
     * @return
     */
    AgreementVo getAgreementById(Long id);
}
