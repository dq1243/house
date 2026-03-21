package com.dq.house.web.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dq.house.model.entity.FeeKey;
import com.dq.house.web.admin.vo.fee.FeeKeyVo;

import java.util.List;

/**
* @author liubo
* @description 针对表【fee_key(杂项费用名称表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface FeeKeyService extends IService<FeeKey> {

    /**
     * 查询全部杂费名称和杂费值列表
     * @return List<FeeKeyVo>
     */
    List<FeeKeyVo> feeInfoList();
}
