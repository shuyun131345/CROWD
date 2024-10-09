package com.atguigu.crowd.service.inf;

import com.atguigu.crowd.entity.Admin;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author shuyun
 * @date 2024-08-17 15:48:30
 */
public interface AdminService {


    /**
     * 管理员登录检查
     *
     * @param admin
     * @return
     */
    Admin checkAdminLogin(Admin admin);

    /**
     * 查询Admin列表
     *
     * @return List<Admin>
     */
    List<Admin> selectAdminList();


    /**
     * 按关键字查询管理员信息
     *
     * @param keyWord
     * @return
     */
    PageInfo<Admin> selectAdminByKeyWord(String keyWord, Integer pageNum, Integer pageSize);

    /**
     * 按id删除管理员
     * @param id
     * @return
     */
    int removeAdminByid(Integer id);

    /**
     * 新增管理员信息
     * @param admin
     * @return
     */
    int addAdmin(Admin admin);

    /**
     * 根据id查询管理员信息
     * @param id
     * @return
     */
    Admin selectAdminById(Integer id);

    /**
     * 根据Id修改管理员信息
     * @param admin
     * @return
     */
    int updateAdminById(Admin admin);

    /**
     * 根据用户名查询admin信息
     * @param acount
     * @return
     */
    Admin selectAdminByAcount(String acount);
}
