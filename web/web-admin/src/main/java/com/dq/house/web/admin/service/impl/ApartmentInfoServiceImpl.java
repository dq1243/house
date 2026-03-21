package com.dq.house.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dq.house.common.exception.HouseException;
import com.dq.house.common.result.ResultCodeEnum;
import com.dq.house.model.entity.*;
import com.dq.house.model.enums.ItemType;
import com.dq.house.web.admin.mapper.*;
import com.dq.house.web.admin.service.*;
import com.dq.house.web.admin.vo.apartment.ApartmentDetailVo;
import com.dq.house.web.admin.vo.apartment.ApartmentItemVo;
import com.dq.house.web.admin.vo.apartment.ApartmentQueryVo;
import com.dq.house.web.admin.vo.apartment.ApartmentSubmitVo;
import com.dq.house.web.admin.vo.fee.FeeValueVo;
import com.dq.house.web.admin.vo.graph.GraphVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liubo
 * @description 针对表【apartment_info(公寓信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class ApartmentInfoServiceImpl extends ServiceImpl<ApartmentInfoMapper, ApartmentInfo>
        implements ApartmentInfoService {

    @Autowired
    private GraphInfoService graphInfoService;
    @Autowired
    private ApartmentFacilityService apartmentFacilityService;
    @Autowired
    private ApartmentLabelService apartmentLabelService;
    @Autowired
    private ApartmentFeeValueService apartmentFeeValueService;
    @Autowired
    private ApartmentInfoMapper apartmentInfoMapper;
    @Autowired
    private GraphInfoMapper graphInfoMapper;
    @Autowired
    private LabelInfoMapper labelInfoMapper;
    @Autowired
    private FacilityInfoMapper facilityInfoMapper;
    @Autowired
    private FeeValueMapper feeValueMapper;
    @Autowired
    private RoomInfoMapper roomInfoMapper;

    /**
     * 保存或更新公寓信息
     *
     * @param apartmentSubmitVo
     */
    @Override
    public void saveOrUpdateApartmentInfo(ApartmentSubmitVo apartmentSubmitVo) {
        if (apartmentSubmitVo.getId() != null) {
            // 删除图片列表
            LambdaQueryWrapper<GraphInfo> graphWrapper = new LambdaQueryWrapper<>();
            graphWrapper.eq(GraphInfo::getItemType, ItemType.APARTMENT);
            graphWrapper.eq(GraphInfo::getItemId, apartmentSubmitVo.getId());
            graphInfoService.remove(graphWrapper);
            // 删除配套列表
            LambdaQueryWrapper<ApartmentFacility> facilityWrapper = new LambdaQueryWrapper<>();
            facilityWrapper.eq(ApartmentFacility::getApartmentId, apartmentSubmitVo.getId());
            apartmentFacilityService.remove(facilityWrapper);
            // 删除标签列表
            LambdaQueryWrapper<ApartmentLabel> labelWrapper = new LambdaQueryWrapper<>();
            labelWrapper.eq(ApartmentLabel::getApartmentId, apartmentSubmitVo.getId());
            apartmentLabelService.remove(labelWrapper);
            // 删除杂费列表
            LambdaQueryWrapper<ApartmentFeeValue> feeWrapper = new LambdaQueryWrapper<>();
            feeWrapper.eq(ApartmentFeeValue::getId, apartmentSubmitVo.getId());
            apartmentFeeValueService.remove(feeWrapper);
        }
        // 如果id不为null，说明是更新操作，需要先删除原有的公寓设施信息，再插入新的公寓设施信息
        super.saveOrUpdate(apartmentSubmitVo);
        // 插入图片列表
        List<GraphVo> graphVoList = apartmentSubmitVo.getGraphVoList();
        if (!CollectionUtils.isEmpty(graphVoList)) {
            ArrayList<GraphInfo> graphInfoList = new ArrayList<>();
            for (GraphVo graphVo : graphVoList) {
                GraphInfo graphInfo = GraphInfo.builder()
                        .itemId(apartmentSubmitVo.getId())
                        .itemType(ItemType.APARTMENT)
                        .url(graphVo.getUrl())
                        .build();
                graphInfoList.add(graphInfo);
            }
            graphInfoService.saveBatch(graphInfoList);
        }
        // 插入配套列表
        List<Long> facilityInfoIds = apartmentSubmitVo.getFacilityInfoIds();
        if (!CollectionUtils.isEmpty(facilityInfoIds)) {
            ArrayList<ApartmentFacility> facilities = new ArrayList<>();
            for (Long facilityInfoId : facilityInfoIds) {
                ApartmentFacility apartmentFacility = ApartmentFacility.builder()
                        .apartmentId(apartmentSubmitVo.getId())
                        .facilityId(facilityInfoId)
                        .build();
                apartmentFacility.setApartmentId(apartmentSubmitVo.getId());
                apartmentFacility.setFacilityId(facilityInfoId);
                facilities.add(apartmentFacility);
            }
            apartmentFacilityService.saveBatch(facilities);
        }
        // 插入标签列表
        List<Long> labelIds = apartmentSubmitVo.getLabelIds();
        if (!CollectionUtils.isEmpty(labelIds)) {
            ArrayList<ApartmentLabel> apartmentLabels = new ArrayList<>();
            for (Long labelId : labelIds) {
                ApartmentLabel apartmentLabel = ApartmentLabel.builder()
                        .apartmentId(apartmentSubmitVo.getId())
                        .labelId(labelId)
                        .build();
                apartmentLabels.add(apartmentLabel);
            }
            apartmentLabelService.saveBatch(apartmentLabels);
        }
        // 插入杂费列表
        List<Long> feeValueIds = apartmentSubmitVo.getFeeValueIds();
        if (!CollectionUtils.isEmpty(feeValueIds)) {
            ArrayList<ApartmentFeeValue> apartmentFeeValues = new ArrayList<>();
            for (Long feeValueId : feeValueIds) {
                ApartmentFeeValue apartmentFeeValue = ApartmentFeeValue.builder()
                        .apartmentId(apartmentSubmitVo.getId())
                        .feeValueId(feeValueId)
                        .build();
                apartmentFeeValues.add(apartmentFeeValue);
            }
            apartmentFeeValueService.saveBatch(apartmentFeeValues);
        }
    }

    /**
     * 根据条件分页查询公寓列表
     *
     * @param page
     * @param queryVo
     * @return
     */
    @Override
    public IPage<ApartmentItemVo> pageItem(Page<ApartmentItemVo> page, ApartmentQueryVo queryVo) {
        return apartmentInfoMapper.pageItem(page, queryVo);
    }

    /**
     * 根据ID获取公寓详细信息
     *
     * @param id
     * @return
     */
    @Override
    public ApartmentDetailVo getApartmentInfoById(Long id) {
        // 获取公寓基本信息
        ApartmentInfo apartmentInfo = apartmentInfoMapper.selectById(id);
        // 获取图片列表
        List<GraphVo> graphVoList = graphInfoMapper.getList(id, ItemType.APARTMENT);
        // 获取标签列表
        List<LabelInfo> labelInfoList = labelInfoMapper.getList(id);
        // 获取配套列表
        List<FacilityInfo> facilityInfoList = facilityInfoMapper.getListByApartmentId(id);
        // 获取杂费列表
        List<FeeValueVo> feeValueList = feeValueMapper.getList(id);
        ApartmentDetailVo apartmentDetailVo = new ApartmentDetailVo();
        BeanUtils.copyProperties(apartmentInfo, apartmentDetailVo);
        apartmentDetailVo.setGraphVoList(graphVoList);
        apartmentDetailVo.setLabelInfoList(labelInfoList);
        apartmentDetailVo.setFacilityInfoList(facilityInfoList);
        apartmentDetailVo.setFeeValueVoList(feeValueList);
        return apartmentDetailVo;
    }

    /**
     * 根据公寓id删除公寓信息
     *
     * @param id
     */
    @Override
    public void removeByApartmentId(Long id) {
        LambdaQueryWrapper<RoomInfo> roomWrapper = new LambdaQueryWrapper<>();
        roomWrapper.eq(RoomInfo::getApartmentId, id);
        Long count = roomInfoMapper.selectCount(roomWrapper);
        if (count > 0) {
            throw new HouseException(ResultCodeEnum.ADMIN_APARTMENT_DELETE_ERROR);
        } else {
            super.removeById(id);
            // 删除图片列表
            graphInfoMapper.removeByApartmentId(id, ItemType.APARTMENT);
            // 删除配套列表
            facilityInfoMapper.removeByApartmentId(id);
            // 删除标签列表
            labelInfoMapper.removeByApartmentId(id);
            // 删除杂费列表
            feeValueMapper.removeByApartmentId(id);
        }
    }
}