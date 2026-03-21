package com.dq.house.web.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dq.house.model.entity.BrowsingHistory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dq.house.web.app.vo.history.HistoryItemVo;

/**
* @author liubo
* @description 针对表【browsing_history(浏览历史)】的数据库操作Service
* @createDate 2023-07-26 11:12:39
*/
public interface BrowsingHistoryService extends IService<BrowsingHistory> {

    /**
     * 分页查询浏览历史
     * @param page
     * @param userId
     * @return
     */
    IPage<HistoryItemVo> pageItem(Page<HistoryItemVo> page, Long userId);

    /**
     * 保存浏览历史
     * @param userId
     * @param id
     */
    void saveHistory(Long userId, Long id);
}
