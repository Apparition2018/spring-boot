package com.ljh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljh.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * OrderMapper
 *
 * @author Arsenal
 * created on 2022/2/16 12:58
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
