package com.dq.house.web.app.service;

import com.dq.house.model.entity.LeaseAgreement;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dq.house.web.app.vo.agreement.AgreementDetailVo;
import com.dq.house.web.app.vo.agreement.AgreementItemVo;

import java.util.List;

/**
* @author liubo
* @description 针对表【lease_agreement(租约信息表)】的数据库操作Service
* @createDate 2023-07-26 11:12:39
*/
public interface LeaseAgreementService extends IService<LeaseAgreement> {

    /**
     * 获取个人租约基本信息列表
     * @return
     */
    List<AgreementItemVo> getListItem();

    /**
     * 根据id获取租约详细信息
     * @param id
     * @return
     */
    AgreementDetailVo getDetailById(Long id);
}
