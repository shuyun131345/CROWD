package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.exception.RoleException;
import com.atguigu.crowd.mapper.RoleMapper;
import com.atguigu.crowd.service.inf.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.atguigu.crowd.constant.ExceptionConstant.DOPLICATION_ROLE;

/**
 * @author shuyun
 * @date 2024-08-28 22:49:39
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper mapper;

    @Override
    public PageInfo<Role> getRolePageInfo(String keyWord, Integer pageNum, Integer pageSize) {

        if (pageNum == null || pageNum.equals(0)) {
            pageNum = 1;
        }

        if (pageSize == null || pageSize.equals(0)) {
            pageSize = 10;
        }
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);
        List<Role> roleList = mapper.getRoleListByKeyword(keyWord);
        PageInfo<Role> pageInfo = new PageInfo<>(roleList);
        return pageInfo;
    }

    @Override
    public int saveRole(String roleName) {
        int count = 0;
        try {
            count = mapper.saveRole(roleName);
        } catch (DuplicateKeyException e) {
            throw new RoleException(DOPLICATION_ROLE);
        }
        return count;
    }

    @Override
    public int updateRoleByid(Role role) {
        int count = 0;
        try {
            count = mapper.updateRoleById(role);
        } catch (DuplicateKeyException e) {
            throw new RoleException(DOPLICATION_ROLE);
        }
        return count;
    }

    @Override
    public void deleteRoles(List<Role> roleList) {
        mapper.deleteRoles(roleList);
    }


}
