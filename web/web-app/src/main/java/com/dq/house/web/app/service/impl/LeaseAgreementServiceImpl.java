package com.dq.house.web.app.service.impl;

import com.dq.house.common.login.LoginUserHolder;
import com.dq.house.model.entity.LeaseAgreement;
import com.dq.house.web.app.mapper.LeaseAgreementMapper;
import com.dq.house.web.app.service.LeaseAgreementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dq.house.web.app.vo.agreement.AgreementDetailVo;
import com.dq.house.web.app.vo.agreement.AgreementItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liubo
 * @description 针对表【lease_agreement(租约信息表)】的数据库操作Service实现
 * @createDate 2023-07-26 11:12:39
 */
@Service
public class LeaseAgreementServiceImpl extends ServiceImpl<LeaseAgreementMapper, LeaseAgreement>
        implements LeaseAgreementService {

    @Autowired
    private LeaseAgreementMapper leaseAgreementMapper;

    /**
     * 获取个人租约基本信息列表
     *
     * @return
     */
    @Override
    public List<AgreementItemVo> getListItem() {
        String username = LoginUserHolder.getLoginUser().getUsername();
        return leaseAgreementMapper.getListItem(username);
    }

    /**
     * 根据id获取租约详细信息
     * @param id
     * @return
     */
    @Override
    public AgreementDetailVo getDetailById(Long id) {
        return leaseAgreementMapper.getDetailById(id);
    }
}




