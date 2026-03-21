package com.dq.house.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dq.house.model.entity.AttrKey;
import com.dq.house.web.admin.vo.attr.AttrKeyVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【attr_key(房间基本属性表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.dq.house.model.AttrKey
*/
public interface AttrKeyMapper extends BaseMapper<AttrKey> {

    /**
     * 查询全部属性名称和属性值列表
     * @return 属性名称和属性值列表
     */
    List<AttrKeyVo> listAttrInfo();
}




