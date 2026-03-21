package com.dq.house.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dq.house.model.entity.LabelInfo;
import com.dq.house.model.enums.ItemType;

import java.util.List;

/**
 * @author liubo
 * @description 针对表【label_info(标签信息表)】的数据库操作Mapper
 * @createDate 2023-07-24 15:48:00
 * @Entity com.dq.house.model.LabelInfo
 */
public interface LabelInfoMapper extends BaseMapper<LabelInfo> {

    /**
     * 根据公寓ID获取标签列表
     *
     * @param id 公寓ID
     * @return 标签列表
     */
    List<LabelInfo> getList(Long id);

    /**
     * 根据公寓id删除标签信息
     * @param id
     */
    void removeByApartmentId(Long id);
}




