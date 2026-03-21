package com.dq.house.web.admin.controller.apartment;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dq.house.common.result.Result;
import com.dq.house.model.entity.LabelInfo;
import com.dq.house.model.enums.BaseEnum;
import com.dq.house.model.enums.ItemType;
import com.dq.house.web.admin.service.LabelInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@Tag(name = "标签管理")
@RestController
@RequestMapping("/admin/label")
public class LabelController {

    @Autowired
    private LabelInfoService labelInfoService;

    @Operation(summary = "（根据类型）查询标签列表")
    @GetMapping("list")
    // @RequestParm修饰的参数不是String类型时，会自动进行类型转换
    //    转换流程：（string）->枚举值（APARTMENT(ROOM)）
    //    当你的 Controller 方法参数声明为实现了 BaseEnum 的枚举类型（如 ItemType），且前端传递的是字符串（如 "1"），
    //    Spring MVC 会尝试将字符串转换为枚举类型。
    //    Spring 容器会查找注册的 ConverterFactory<String, BaseEnum>，即你的 StringToBaseEnumConverter。
    //    Spring 调用 getConverter(Class<T> targetType)，传入目标枚举类型（如 ItemType.class），获取一个具体的 Converter。
    //    然后用这个 Converter 把请求参数的字符串转换为对应的枚举值。
    public Result<List<LabelInfo>> labelList(@RequestParam(required = false) ItemType type) {  // @RequestParam 注解会将请求参数中的字符串转换为 ItemType 枚举值
        LambdaQueryWrapper<LabelInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(type != null, LabelInfo::getType, type);
        List<LabelInfo> list = labelInfoService.list(wrapper);
        return Result.ok(list);
    }

    @Operation(summary = "新增或修改标签信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdateLabel(@RequestBody LabelInfo labelInfo) {  // @RequestBody注解会将请求体中的JSON数据转换为LabelInfo对象
        labelInfoService.saveOrUpdate(labelInfo);
        return Result.ok();
    }

    @Operation(summary = "根据id删除标签信息")
    @DeleteMapping("deleteById")
    public Result deleteLabelById(@RequestParam Long id) {
        labelInfoService.removeById(id);
        return Result.ok();
    }
}
