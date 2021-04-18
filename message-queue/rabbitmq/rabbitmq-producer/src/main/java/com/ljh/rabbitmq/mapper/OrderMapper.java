package com.ljh.rabbitmq.mapper;

import com.ljh.rabbitmq.entity.Order;
import org.springframework.stereotype.Repository;

/**
 * OrderMapper
 *
 * @author Arsenal
 * created on 2021/4/17 17:51
 */
@Repository
public interface OrderMapper {
    
    int deleteByPrimaryKey(String id);
    
    int insert(Order record);
    
    int insertSelective(Order record);
    
    Order selectByPrimaryKey(String id);
    
    int updateByPrimaryKeySelective(Order record);
    
    int updateByPrimaryKey(Order record);
}
