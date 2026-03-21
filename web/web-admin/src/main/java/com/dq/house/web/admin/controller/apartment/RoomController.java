package com.dq.house.web.admin.controller.apartment;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dq.house.common.constant.RedisConstant;
import com.dq.house.common.result.Result;
import com.dq.house.model.entity.RoomInfo;
import com.dq.house.model.enums.ReleaseStatus;
import com.dq.house.web.admin.service.*;
import com.dq.house.web.admin.vo.room.RoomDetailVo;
import com.dq.house.web.admin.vo.room.RoomItemVo;
import com.dq.house.web.admin.vo.room.RoomQueryVo;
import com.dq.house.web.admin.vo.room.RoomSubmitVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "房间信息管理")
@RestController
@RequestMapping("/admin/room")
public class RoomController {

    @Autowired
    private RoomInfoService roomInfoService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Operation(summary = "保存或更新房间信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody RoomSubmitVo roomSubmitVo) {
        roomInfoService.saveOrUpdateRoomInfo(roomSubmitVo);
        return Result.ok();
    }

    @Operation(summary = "根据条件分页查询房间列表")
    @GetMapping("pageItem")
    public Result<IPage<RoomItemVo>> pageItem(@RequestParam long current, @RequestParam long size, RoomQueryVo queryVo) {
        Page<RoomItemVo> page = new Page<>(current, size);
        IPage<RoomItemVo> result = roomInfoService.pageItem(page, queryVo);
        return Result.ok(result);
    }

    @Operation(summary = "根据id获取房间详细信息")
    @GetMapping("getDetailById")
    public Result<RoomDetailVo> getDetailById(@RequestParam Long id) {
        RoomDetailVo roomDetailVo = roomInfoService.getDetailById(id);
        return Result.ok(roomDetailVo);
    }

    @Operation(summary = "根据id删除房间信息")
    @DeleteMapping("removeById")
    public Result removeById(@RequestParam Long id) {
        roomInfoService.removeRoomInfoById(id);
        return Result.ok();
    }

    @Operation(summary = "根据id修改房间发布状态")
    @PostMapping("updateReleaseStatusById")
    public Result updateReleaseStatusById(Long id, ReleaseStatus status) {
        LambdaUpdateWrapper<RoomInfo> roomInfoWrapper = new LambdaUpdateWrapper<>();
        roomInfoWrapper.eq(RoomInfo::getId, id).set(RoomInfo::getIsRelease, status);
        roomInfoService.update(roomInfoWrapper);
        // 删除缓存
        redisTemplate.delete(RedisConstant.APP_ROOM_PREFIX + id);
        return Result.ok();
    }

    @GetMapping("listBasicByApartmentId")
    @Operation(summary = "根据公寓id查询房间列表")
    public Result<List<RoomInfo>> listBasicByApartmentId(Long id) {
        LambdaQueryWrapper<RoomInfo> roomInfoWrapper = new LambdaQueryWrapper<>();
        roomInfoWrapper.eq(RoomInfo::getApartmentId, id);
        List<RoomInfo> roomInfoList = roomInfoService.list(roomInfoWrapper);
        return Result.ok(roomInfoList);
    }
}