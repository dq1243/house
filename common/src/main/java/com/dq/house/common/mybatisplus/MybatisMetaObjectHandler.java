package com.dq.house.common.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author DQ1243
 */

// mybatis-plus自动填充字段处理器，
// 目前暂时不使用，后续如果需要自动填充字段（如创建时间、更新时间等）
// 可以在此类中实现MetaObjectHandler接口并重写相关方法

@Component  // @Component 让 Spring 能够识别并实例化 MybatisMetaObjectHandler
public class MybatisMetaObjectHandler implements MetaObjectHandler {

    // 在插入数据时自动填充字段
    @Override
    public void insertFill(MetaObject metaObject) {
         this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
    }

    // 在更新数据时自动填充字段
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }
}
