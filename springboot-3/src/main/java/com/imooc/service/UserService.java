package com.imooc.service;

import com.imooc.pojo.SysUser;

import java.util.List;

/**
 * @author Arsenal
 * created on 2019/2/21 22:46
 */
public interface UserService {

    void saveUser(SysUser user);

    void updateUser(SysUser user);

    void deleteUser(String userId);

    SysUser queryUserById(String userId);

    List<SysUser> queryUserList(SysUser user);

    List<SysUser> queryUserListPaged(SysUser user, Integer page, Integer pageSize);

    SysUser queryUserByIdCustom(String userId);

    void saveUserTransactional(SysUser user);

}
