package com.dq.house.web.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dq.house.common.constant.RedisConstant;
import com.dq.house.common.login.LoginUserHolder;
import com.dq.house.model.entity.*;
import com.dq.house.model.enums.ItemType;
import com.dq.house.web.app.mapper.*;
import com.dq.house.web.app.service.BrowsingHistoryService;
import com.dq.house.web.app.service.RoomInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dq.house.web.app.vo.apartment.ApartmentDetailVo;
import com.dq.house.web.app.vo.apartment.ApartmentItemVo;
import com.dq.house.web.app.vo.attr.AttrValueVo;
import com.dq.house.web.app.vo.fee.FeeValueVo;
import com.dq.house.web.app.vo.graph.GraphVo;
import com.dq.house.web.app.vo.room.RoomDetailVo;
import com.dq.house.web.app.vo.room.RoomItemVo;
import com.dq.house.web.app.vo.room.RoomQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author liubo
 * @description 针对表【room_info(房间信息表)】的数据库操作Service实现
 * @createDate 2023-07-26 11:12:39
 */
@Service
@Slf4j
public class RoomInfoServiceImpl extends ServiceImpl<RoomInfoMapper, RoomInfo>
        implements RoomInfoService {

    @Autowired
    private ApartmentInfoMapper apartmentInfoMapper;
    @Autowired
    private RoomInfoMapper roomInfoMapper;
    @Autowired
    private GraphInfoMapper graphInfoMapper;
    @Autowired
    private RoomAttrValueMapper attrValueMapper;
    @Autowired
    private RoomFacilityMapper roomFacilityMapper;
    @Autowired
    private RoomLabelMapper roomLabelMapper;
    @Autowired
    private RoomPaymentTypeMapper roomPaymentTypeMapper;
    @Autowired
    private FeeValueMapper feeValueMapper;
    @Autowired
    private RoomLeaseTermMapper roomLeaseTermMapper;
    @Autowired
    private BrowsingHistoryService browsingHistoryService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 分页查询房间列表
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
     * 根据id获取房间的详细信息
     *
     * @param id
     * @return
     */
    @Override
    public RoomDetailVo getDetailById(Long id) {
        // 先从缓存中获取
        String key = RedisConstant.APP_ROOM_PREFIX + id;
        RoomDetailVo roomDetailVo = (RoomDetailVo) redisTemplate.opsForValue().get(key);
        if (roomDetailVo == null) {  // 缓存无数据
            RoomInfo roomInfo = roomInfoMapper.selectById(id);
            BigDecimal minRent = apartmentInfoMapper.getMinRent(roomInfo.getApartmentId());
            ApartmentItemVo apartmentItemVo = apartmentInfoMapper.getApartmentItemVoById(roomInfo.getApartmentId());
            apartmentItemVo.setMinRent(minRent);
            List<GraphVo> graphVoList = graphInfoMapper.getListByRoomId(id, ItemType.ROOM);
            List<AttrValueVo> attrValueVoList = attrValueMapper.getListByRoomId(id);
            List<FacilityInfo> facilityInfoList = roomFacilityMapper.getFacilityListByRoomId(id);
            List<LabelInfo> labelInfoList = roomLabelMapper.getLabelListByRoomId(id);
            List<PaymentType> paymentTypeList = roomPaymentTypeMapper.getListByRoomId(id);
            List<FeeValueVo> feeValueVoList = feeValueMapper.getListByRoomId(id);
            List<LeaseTerm> leaseTermList = roomLeaseTermMapper.getListByRoomId(id);

            roomDetailVo = new RoomDetailVo();
            BeanUtils.copyProperties(roomInfo, roomDetailVo);
            roomDetailVo.setApartmentItemVo(apartmentItemVo);
            roomDetailVo.setGraphVoList(graphVoList);
            roomDetailVo.setAttrValueVoList(attrValueVoList);
            roomDetailVo.setFacilityInfoList(facilityInfoList);
            roomDetailVo.setLabelInfoList(labelInfoList);
            roomDetailVo.setPaymentTypeList(paymentTypeList);
            roomDetailVo.setFeeValueVoList(feeValueVoList);
            roomDetailVo.setLeaseTermList(leaseTermList);
            // 将查询结果放入缓存
            redisTemplate.opsForValue().set(key, roomDetailVo);
        }
        // 保存浏览历史
        browsingHistoryService.saveHistory(LoginUserHolder.getLoginUser().getUserId(), id);
        return roomDetailVo;
    }

    /**
     * 根据公寓id分页查询房间列表
     *
     * @param page
     * @param id
     * @return
     */
    @Override
    public IPage<RoomItemVo> pageItemByApartmentId(Page<RoomItemVo> page, Long id) {
        return roomInfoMapper.pageItemByApartmentId(page, id);
    }
}




