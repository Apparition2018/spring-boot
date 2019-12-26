package com.imooc.mapper;

import com.imooc.pojo.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserMapperCustom {

    List<SysUser> queryUserSimplyInfoById(String id);
}