package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.common.DateUtil;
import com.atguigu.crowd.common.EncodeByMD5;
import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.AdminExample;
import com.atguigu.crowd.exception.EditErrorException;
import com.atguigu.crowd.exception.InputErrorException;
import com.atguigu.crowd.exception.LoginFailedException;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.service.inf.AdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.atguigu.crowd.constant.ExceptionConstant.*;

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
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> adminList = mapper.selectAdminListByKeyWord(keyWord);
        PageInfo<Admin> pageInfo = new PageInfo<>(adminList);
        return pageInfo;
    }

    @Override
    public int removeAdminByid(Integer id) {
        int count = mapper.deleteAdminById(id);
        return count;
    }

    @Override
    public int addAdmin(Admin admin) {

        String tips = checkAdmin(admin);
        int count = 0;

        if (!StringUtils.isEmpty(tips)) {
            throw new InputErrorException(tips);
        }

        // 将明文密码用md5加密
        String encodedPsw = EncodeByMD5.encode(admin.getUserPswd());
        admin.setUserPswd(encodedPsw);

        //日期
        admin.setCreateTime(DateUtil.formatDate(new Date()));

        try {
            count = mapper.insertAdmin(admin);
        } catch (Exception e) {
            e.printStackTrace();
            // 账号重复
            if (e instanceof DuplicateKeyException) {
                throw new InputErrorException(DOPLICATION_LOGIN_ACCOUNT);
            }
            throw e;
        }
        return count;
    }

    @Override
    public Admin selectAdminById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateAdminById(Admin admin) {
        if (Objects.isNull(admin)){
            throw new EditErrorException("修改信息为空");
        }

        if (StringUtils.isEmpty(admin.getLoginAcct())&&StringUtils.isEmpty(admin.getUserName())&&StringUtils.isEmpty(admin.getEmail())&&StringUtils.isEmpty(admin.getUserPswd())){
            throw new EditErrorException("修改信息为空");
        }

        //更新日期
        admin.setCreateTime(DateUtil.formatDate(new Date()));
        return mapper.updateAdminById(admin);
    }

    /**
     * 根据用户名查询admin信息
     * @param acount
     * @return
     */
    @Override
    public Admin selectAdminByAcount(String acount) {
        return mapper.selectByAcount(acount);
    }

    /**
     * 新增管理员信息检查
     *
     * @param admin
     * @return
     */
    private String checkAdmin(Admin admin) {
        if (Objects.isNull(admin) || StringUtils.isEmpty(admin.getLoginAcct())) {
            return ACOUNT_ISNULL;
        }

        if (StringUtils.isEmpty(admin.getEmail())) {
            return EAMAI_ISNULL;
        }
        if (StringUtils.isEmpty(admin.getUserPswd())) {
            return PASSWORD_ISNULL;
        }
        if (StringUtils.isEmpty(admin.getUserName())) {
            return USERNAME_ISNULL;
        }
        return "";
    }


}
