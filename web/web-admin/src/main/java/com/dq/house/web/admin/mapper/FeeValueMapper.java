package com.dq.house.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dq.house.model.entity.FeeValue;
import com.dq.house.web.admin.vo.fee.FeeValueVo;

import java.util.List;

/**
* @author liubo
* @description 针对表【fee_value(杂项费用值表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.dq.house.model.FeeValue
*/
public interface FeeValueMapper extends BaseMapper<FeeValue> {

    /**
     * 根据公寓ID获取杂项费用值列表
     * @param id 公寓ID
     * @return 杂项费用值列表
     */
    List<FeeValueVo> getList(Long id);

    /**
     * 根据公寓id删除杂项费用值信息
     * @param id
     */
    void removeByApartmentId(Long id);
}




