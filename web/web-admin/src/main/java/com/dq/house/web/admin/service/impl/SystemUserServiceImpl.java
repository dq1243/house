package com.dq.house.web.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dq.house.model.entity.SystemUser;
import com.dq.house.web.admin.mapper.SystemUserMapper;
import com.dq.house.web.admin.service.SystemUserService;
import com.dq.house.web.admin.vo.system.user.SystemUserItemVo;
import com.dq.house.web.admin.vo.system.user.SystemUserQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 * @description 针对表【system_user(员工信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser>
        implements SystemUserService {

    @Autowired
    private SystemUserMapper systemUserMapper;

    /**
     * 根据条件分页查询后台用户列表
     *
     * @param page    分页对象
     * @param queryVo 查询条件对象
     * @return 分页结果
     */
    @Override
    public IPage<SystemUserItemVo> itemPage(Page<SystemUserItemVo> page, SystemUserQueryVo queryVo) {
        return systemUserMapper.selectItemPage(page, queryVo);
    }

    /**
     * 根据ID查询后台用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    @Override
    public SystemUserItemVo getUserById(Long id) {
        return systemUserMapper.getUserById(id);
    }
}




