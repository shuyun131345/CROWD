package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.mapper.RoleMapper;
import com.atguigu.crowd.service.inf.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        return mapper.saveRole(roleName);
    }


}
