package com.dq.house.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dq.house.model.entity.SystemUser;
import com.dq.house.web.admin.vo.system.user.SystemUserItemVo;
import com.dq.house.web.admin.vo.system.user.SystemUserQueryVo;

/**
 * @author liubo
 * @description 针对表【system_user(员工信息表)】的数据库操作Mapper
 * @createDate 2023-07-24 15:48:00
 * @Entity com.dq.house.model.SystemUser
 */
public interface SystemUserMapper extends BaseMapper<SystemUser> {

    /**
     * 根据条件分页查询后台用户列表
     *
     * @param page    分页对象
     * @param queryVo 查询条件对象
     * @return 分页结果
     */
    IPage<SystemUserItemVo> selectItemPage(Page<SystemUserItemVo> page, SystemUserQueryVo queryVo);

    /**
     * 根据ID查询后台用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    SystemUserItemVo getUserById(Long id);

    /**
     * 根据用户名查询后台用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    SystemUser selectOneByUserName(String username);
}




