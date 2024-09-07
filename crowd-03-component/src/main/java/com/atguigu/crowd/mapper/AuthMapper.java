package com.atguigu.crowd.mapper;

import com.atguigu.crowd.entity.Auth;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author shuyun
 * @date 2024-09-05 09-18-51
 */
public interface AuthMapper {

    /**
     * 查询所有权限信息
     * @return
     */
    List<Auth> selectAuthList();

    /**
     * 根据角色id查询已有权限信息
     * roleId 角色id
     * @return
     */
    List<Auth> selectAssignAuthList(@Param("roleId") Integer roleId);
}
