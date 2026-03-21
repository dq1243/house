package com.dq.house.web.app.mapper;

import com.dq.house.model.entity.FeeValue;
import com.dq.house.web.app.vo.fee.FeeValueVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【fee_value(杂项费用值表)】的数据库操作Mapper
* @createDate 2023-07-26 11:12:39
* @Entity com.dq.house.model.entity.FeeValue
*/
public interface FeeValueMapper extends BaseMapper<FeeValue> {

    /**
     * 根据房间id查询杂项费用列表
     * @param id
     * @return
     */
    List<FeeValueVo> getListByRoomId(Long id);
}




