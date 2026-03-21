package com.dq.house.web.admin.controller.apartment;

import com.dq.house.common.result.Result;
import com.dq.house.model.entity.PaymentType;
import com.dq.house.web.admin.service.PaymentTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "支付方式管理")
@RequestMapping("/admin/payment")
@RestController
public class PaymentTypeController {

    @Autowired
    private PaymentTypeService paymentTypeService;

    @Operation(summary = "查询全部支付方式列表")
    @GetMapping("list")
    public Result<List<PaymentType>> listPaymentType() {
//        第一种：list
//        拥有已被删除的支付方式，需要过滤
//        全局配置mybatis-plus自动过滤数据，或者在查询时添加过滤条件，只对baseService和baseMapper有效
//        自己手动添加方法需要手动过滤
        List<PaymentType> list = paymentTypeService.list();

//        第二种：list(QueryWrapper)
//        QueryWrapper<PaymentType> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("is_deleted", 0);
//        List<PaymentType> list = paymentTypeService.list(queryWrapper);

//        第三种：list(LambdaQueryWrapper)
//        LambdaQueryWrapper<PaymentType> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(PaymentType::getIsDeleted, 0);
//        List<PaymentType> list = paymentTypeService.list(wrapper);
        return Result.ok(list);
    }

    @Operation(summary = "保存或更新支付方式")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdatePaymentType(@RequestBody PaymentType paymentType) {
        // saveOrUpdate方法会根据传入的对象是否有id来判断是执行保存还是更新操作,
        // 如果对象没有id或者id为null，则执行保存操作；如果对象有id且id不为null，则执行更新操作。
        // 需要注意的是，saveOrUpdate方法在执行更新操作时，会根据id来查找数据库中是否存在对应的记录，如果存在则进行更新，如果不存在则会抛出异常。
        // 因此，在使用saveOrUpdate方法时，确保传入的对象具有正确的id值，以避免不必要的错误,小心mybatis的自动过滤（is_deleted）。
        paymentTypeService.saveOrUpdate(paymentType);
        return Result.ok();
    }

    @Operation(summary = "根据ID删除支付方式")
    @DeleteMapping("deleteById")
    public Result deletePaymentById(@RequestParam Long id) {
        // 逻辑删除：mybatis-plus会自动将is_deleted字段更新为1，表示该记录已被删除，但数据仍然保存在数据库中。
        // 如果没有配置逻辑删除，removeById 会执行真正的 DELETE 操作
        paymentTypeService.removeById(id);
        return Result.ok();
    }

}















