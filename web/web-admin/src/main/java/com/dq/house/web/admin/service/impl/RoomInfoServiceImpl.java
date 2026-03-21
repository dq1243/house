package com.dq.house.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dq.house.common.constant.RedisConstant;
import com.dq.house.model.entity.*;
import com.dq.house.model.enums.ItemType;
import com.dq.house.web.admin.mapper.*;
import com.dq.house.web.admin.service.*;
import com.dq.house.web.admin.vo.attr.AttrValueVo;
import com.dq.house.web.admin.vo.graph.GraphVo;
import com.dq.house.web.admin.vo.room.RoomDetailVo;
import com.dq.house.web.admin.vo.room.RoomItemVo;
import com.dq.house.web.admin.vo.room.RoomQueryVo;
import com.dq.house.web.admin.vo.room.RoomSubmitVo;
import kotlin.jvm.internal.Lambda;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liubo
 * @description 针对表【room_info(房间信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class RoomInfoServiceImpl extends ServiceImpl<RoomInfoMapper, RoomInfo>
        implements RoomInfoService {

    @Autowired
    private RoomInfoMapper roomInfoMapper;
    @Autowired
    private GraphInfoService graphInfoService;
    @Autowired
    private GraphInfoMapper graphInfoMapper;
    @Autowired
    private RoomFacilityService roomFacilityService;
    @Autowired
    private RoomAttrValueService roomAttrValueService;
    @Autowired
    private RoomLabelService roomLabelService;
    @Autowired
    private RoomLeaseTermService roomLeaseTermService;
    @Autowired
    private RoomPaymentTypeService roomPaymentTypeService;
    @Autowired
    private ApartmentInfoService apartmentInfoService;
    @Autowired
    private RoomFacilityMapper roomFacilityMapper;
    @Autowired
    private RoomAttrValueMapper roomAttrValueMapper;
    @Autowired
    private RoomLeaseTermMapper roomLeaseTermMapper;
    @Autowired
    private RoomPaymentTypeMapper roomPaymentTypeMapper;
    @Autowired
    private RoomLabelMapper roomLabelMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 保存或更新房间信息
     *
     * @param roomSubmitVo 房间提交对象，包含房间基本信息和相关属性
     */
    @Override
    public void saveOrUpdateRoomInfo(RoomSubmitVo roomSubmitVo) {
        if (roomSubmitVo.getId() != null) {
            // 删除房间图片信息列表
            LambdaQueryWrapper<GraphInfo> GraphInfoWrapper = new LambdaQueryWrapper<>();
            GraphInfoWrapper.eq(GraphInfo::getItemType, ItemType.ROOM);
            GraphInfoWrapper.in(GraphInfo::getItemId, roomSubmitVo.getId());
            graphInfoService.remove(GraphInfoWrapper);
            // 删除房间设施信息列表
            LambdaQueryWrapper<RoomFacility> RoomFacilityWrapper = new LambdaQueryWrapper<>();
            RoomFacilityWrapper.in(RoomFacility::getRoomId, roomSubmitVo.getId());
            roomFacilityService.remove(RoomFacilityWrapper);
            // 删除房间标签信息列表
            LambdaQueryWrapper<RoomLabel> RoomLabelWrapper = new LambdaQueryWrapper<>();
            RoomLabelWrapper.in(RoomLabel::getRoomId, roomSubmitVo.getId());
            roomLabelService.remove(RoomLabelWrapper);
            // 删除房间租约信息列表
            LambdaQueryWrapper<RoomLeaseTerm> RoomLeaseTermWrapper = new LambdaQueryWrapper<>();
            RoomLeaseTermWrapper.in(RoomLeaseTerm::getRoomId, roomSubmitVo.getId());
            roomLeaseTermService.remove(RoomLeaseTermWrapper);
            // 删除房间支付方式信息列表
            LambdaQueryWrapper<RoomPaymentType> RoomPaymentTypeWrapper = new LambdaQueryWrapper<>();
            RoomPaymentTypeWrapper.in(RoomPaymentType::getRoomId, roomSubmitVo.getId());
            roomPaymentTypeService.remove(RoomPaymentTypeWrapper);
            // 删除房间信息列表
            LambdaQueryWrapper<RoomAttrValue> RoomAttrValueWrapper = new LambdaQueryWrapper<>();
            RoomAttrValueWrapper.in(RoomAttrValue::getRoomId, roomSubmitVo.getId());
            roomAttrValueService.remove(RoomAttrValueWrapper);
            // 删除缓存中的房间详细信息
            String key = RedisConstant.APP_ROOM_PREFIX + roomSubmitVo.getId();
            redisTemplate.delete(key);
        }
        // 保存房间基本信息
        super.saveOrUpdate(roomSubmitVo);
        // 保存房间图片列表
        if (!CollectionUtils.isEmpty(roomSubmitVo.getGraphVoList())) {
            ArrayList<GraphInfo> graphVos = new ArrayList<>();
            for (GraphVo graphVo : roomSubmitVo.getGraphVoList()) {
                GraphInfo graphInfo = GraphInfo.builder()
                        .itemType(ItemType.ROOM)
                        .itemId(roomSubmitVo.getId())
                        .url(graphVo.getUrl())
                        .name(graphVo.getName())
                        .build();
                graphVos.add(graphInfo);
            }
            graphInfoService.saveBatch(graphVos);
        }
        // 保存房间设施信息列表
        if (!CollectionUtils.isEmpty(roomSubmitVo.getFacilityInfoIds())) {
            ArrayList<RoomFacility> roomFacilities = new ArrayList<>();
            for (Long facilityId : roomSubmitVo.getFacilityInfoIds()) {
                RoomFacility roomFacility = RoomFacility.builder()
                        .roomId(roomSubmitVo.getId())
                        .facilityId(facilityId)
                        .build();
                roomFacilities.add(roomFacility);
            }
            roomFacilityService.saveBatch(roomFacilities);
        }
        // 保存房间标签信息列表
        if (!CollectionUtils.isEmpty(roomSubmitVo.getLabelInfoIds())) {
            ArrayList<RoomLabel> roomLabels = new ArrayList<>();
            for (Long labelId : roomSubmitVo.getLabelInfoIds()) {
                RoomLabel roomLabel = RoomLabel.builder()
                        .roomId(roomSubmitVo.getId())
                        .labelId(labelId)
                        .build();
                roomLabels.add(roomLabel);
            }
            roomLabelService.saveBatch(roomLabels);
        }
        // 保存房间租约信息列表
        if (!CollectionUtils.isEmpty(roomSubmitVo.getLeaseTermIds())) {
            ArrayList<RoomLeaseTerm> roomLeaseTerms = new ArrayList<>();
            for (Long leaseTermId : roomSubmitVo.getLeaseTermIds()) {
                RoomLeaseTerm roomLeaseTerm = RoomLeaseTerm.builder()
                        .roomId(roomSubmitVo.getId())
                        .leaseTermId(leaseTermId)
                        .build();
                roomLeaseTerms.add(roomLeaseTerm);
            }
            roomLeaseTermService.saveBatch(roomLeaseTerms);
        }
        // 保存房间支付方式信息列表
        if (!CollectionUtils.isEmpty(roomSubmitVo.getPaymentTypeIds())) {
            ArrayList<RoomPaymentType> roomPaymentTypes = new ArrayList<>();
            for (Long paymentTypeId : roomSubmitVo.getPaymentTypeIds()) {
                RoomPaymentType roomPaymentType = RoomPaymentType.builder()
                        .roomId(roomSubmitVo.getId())
                        .paymentTypeId(paymentTypeId)
                        .build();
                roomPaymentTypes.add(roomPaymentType);
            }
            roomPaymentTypeService.saveBatch(roomPaymentTypes);
        }
        // 保存房间信息列表
        if (!CollectionUtils.isEmpty(roomSubmitVo.getAttrValueIds())) {
            ArrayList<RoomAttrValue> roomAttrValues = new ArrayList<>();
            for (Long attrValueId : roomSubmitVo.getAttrValueIds()) {
                RoomAttrValue roomAttrValue = RoomAttrValue.builder()
                        .roomId(roomSubmitVo.getId())
                        .attrValueId(attrValueId)
                        .build();
                roomAttrValues.add(roomAttrValue);
            }
            roomAttrValueService.saveBatch(roomAttrValues);
        }
    }

    /**
     * 根据条件分页查询房间列表
     *
     * @param page
     * @param queryVo
     * @return
     */
    @Override
    public IPage<RoomItemVo> pageItem(Page<RoomItemVo> page, RoomQueryVo queryVo) {
        return roomInfoMapper.pageItem(page, queryVo);
    }

    /**
     * 根据公寓id获取房间详细信息
     *
     * @param id 房间id
     * @return 房间详细信息对象，包含房间基本信息和相关属性
     */
    @Override
    public RoomDetailVo getDetailById(Long id) {
        RoomInfo roomInfo = super.getById(id);
        // 获取公寓信息
        ApartmentInfo apartmentInfo = apartmentInfoService.getById(roomInfo.getApartmentId());
        // 获取房间图片列表
        List<GraphVo> graphVoList = graphInfoMapper.getList(id, ItemType.ROOM);
        // 获取属性信息列表
        List<AttrValueVo> attrValueVoList = roomAttrValueMapper.getList(id);
        // 获取配套信息列表
        List<FacilityInfo> facilityInfoList = roomFacilityMapper.getListByRoomId(id);
        // 获取标签信息列表
        List<LabelInfo> labelInfoList = roomLabelMapper.getListByRoomId(id);
        // 获取支付方式列表
        List<PaymentType> paymentTypeList = roomPaymentTypeMapper.getListByRoomId(id);
        // 获取可选租期列表
        List<LeaseTerm> leaseTermList = roomLeaseTermMapper.getListByRoomId(id);
        RoomDetailVo roomDetailVo = new RoomDetailVo();
        BeanUtils.copyProperties(roomInfo, roomDetailVo);
        roomDetailVo.setApartmentInfo(apartmentInfo);
        roomDetailVo.setGraphVoList(graphVoList);
        roomDetailVo.setAttrValueVoList(attrValueVoList);
        roomDetailVo.setFacilityInfoList(facilityInfoList);
        roomDetailVo.setLabelInfoList(labelInfoList);
        roomDetailVo.setPaymentTypeList(paymentTypeList);
        roomDetailVo.setLeaseTermList(leaseTermList);
        return roomDetailVo;
    }

    /**
     * 根据id删除房间信息
     * @param id
     */
    @Override
    public void removeRoomInfoById(Long id) {
        super.removeById(id);
         // 删除房间图片信息列表
        LambdaQueryWrapper<GraphInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GraphInfo::getItemType, ItemType.ROOM);
        wrapper.in(GraphInfo::getItemId, id);
        graphInfoService.remove(wrapper);
        // 删除房间设施信息列表
        LambdaQueryWrapper<RoomFacility> roomFacilityWrapper = new LambdaQueryWrapper<>();
        roomFacilityWrapper.in(RoomFacility::getRoomId, id);
        roomFacilityService.remove(roomFacilityWrapper);
        // 删除房间标签信息列表
        LambdaQueryWrapper<RoomLabel> roomLabelWrapper = new LambdaQueryWrapper<>();
        roomLabelWrapper.in(RoomLabel::getRoomId, id);
        roomLabelService.remove(roomLabelWrapper);
        // 删除房间租约信息列表
        LambdaQueryWrapper<RoomLeaseTerm> roomLeaseTermWrapper = new LambdaQueryWrapper<>();
        roomLeaseTermWrapper.in(RoomLeaseTerm::getRoomId, id);
        roomLeaseTermService.remove(roomLeaseTermWrapper);
        // 删除房间支付方式信息列表
        LambdaQueryWrapper<RoomPaymentType> roomPaymentTypeWrapper = new LambdaQueryWrapper<>();
        roomPaymentTypeWrapper.in(RoomPaymentType::getRoomId, id);
        roomPaymentTypeService.remove(roomPaymentTypeWrapper);
        // 删除房间信息列表
        LambdaQueryWrapper<RoomAttrValue> roomAttrValueWrapper = new LambdaQueryWrapper<>();
        roomAttrValueWrapper.in(RoomAttrValue::getRoomId, id);
        roomAttrValueService.remove(roomAttrValueWrapper);
        // 删除缓存中的房间详细信息
        String key = RedisConstant.APP_ROOM_PREFIX + id;
        redisTemplate.delete(key);
    }
}