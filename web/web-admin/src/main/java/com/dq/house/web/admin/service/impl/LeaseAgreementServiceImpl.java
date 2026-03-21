package com.dq.house.web.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dq.house.model.entity.LeaseAgreement;
import com.dq.house.web.admin.mapper.LeaseAgreementMapper;
import com.dq.house.web.admin.service.LeaseAgreementService;
import com.dq.house.web.admin.vo.agreement.AgreementQueryVo;
import com.dq.house.web.admin.vo.agreement.AgreementVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 * @description 针对表【lease_agreement(租约信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class LeaseAgreementServiceImpl extends ServiceImpl<LeaseAgreementMapper, LeaseAgreement>
        implements LeaseAgreementService {

    @Autowired
    private LeaseAgreementMapper leaseAgreementMapper;

    /**
     * 根据条件分页查询租约列表
     *
     * @param page
     * @param queryVo
     * @return
     */
    @Override
    public IPage<AgreementVo> itemPage(Page page, AgreementQueryVo queryVo) {
        IPage<AgreementVo> itemPage = leaseAgreementMapper.itemPage(page, queryVo);
        return itemPage;
    }

    /**
     * 根据id查询租约信息
     *
     * @param id
     * @return
     */
    @Override
    public AgreementVo getAgreementById(Long id) {
        AgreementVo agreementVo = leaseAgreementMapper.getAgreementById(id);
        return agreementVo;
    }
}




