package com.dq.house.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "创建时间")
    // fill属性：INSERT表示在插入数据时自动填充，UPDATE表示在更新数据时自动填充，INSERT_UPDATE表示在插入和更新数据时都自动填充
    @TableField(value = "create_time", fill = FieldFill.INSERT)  //
    @JsonIgnore  // 不需要返回给前端，隐藏字段
    private Date createTime;

    @Schema(description = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    @JsonIgnore
    private Date updateTime;

    @Schema(description = "逻辑删除")
    @TableField("is_deleted")
    @TableLogic  // 逻辑删除注解，mybatis-plus会自动处理逻辑删除的字段，在查询操作时会自动过滤掉已删除的数据
    private Byte isDeleted;

}