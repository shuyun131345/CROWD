package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.common.EncodeByMD5;
import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.AdminExample;
import com.atguigu.crowd.exception.LoginFailedException;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.service.inf.AdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.atguigu.crowd.constant.ExceptionConstant.LOGIN_FAILED;

/**
 * @author shuyun
 * @date 2024-08-17 15:49:25
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper mapper;

    /**
     * 管理员登录验证
     *
     * @param admin
     * @return
     */
    @Override
    public Admin checkAdminLogin(Admin admin) {

        // 根据账号去查管理员信息
        Admin select = mapper.selectByAcount(admin.getLoginAcct());
        if (Objects.isNull(select)) {
            throw new LoginFailedException(LOGIN_FAILED);
        }

        // 检验该账号的密码进行md5加密后是否与数据库的密码相同
        String encodedPsw = EncodeByMD5.encode(admin.getUserPswd());

        boolean equals = Objects.equals(encodedPsw, select.getUserPswd());
        // 不相同则抛出异常,相同则返回数据库查询的管理员信息
        if (equals) {
            return select;
        } else {
            throw new LoginFailedException(LOGIN_FAILED);
        }
    }

    @Override
    public List<Admin> selectAdminList() {
        List<Admin> adminList = mapper.selectByExample(new AdminExample());
        return adminList;
    }

    @Override
    public PageInfo<Admin> selectAdminByKeyWord(String keyWord, Integer pageNum, Integer pageSize) {

        if (pageNum == null || pageNum.equals(0)) {
            pageNum = 1;
        }

        if (pageSize == null || pageSize.equals(0)) {
            pageSize = 10;
        }
        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> adminList = mapper.selectAdminListByKeyWord(keyWord);
        PageInfo<Admin> pageInfo = new PageInfo<>(adminList);
        return pageInfo;
    }

}
