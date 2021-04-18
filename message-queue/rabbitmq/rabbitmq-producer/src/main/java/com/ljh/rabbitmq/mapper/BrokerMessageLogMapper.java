package com.ljh.rabbitmq.mapper;

import com.ljh.rabbitmq.entity.BrokerMessageLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * BrokerMessageLogMapper
 *
 * @author Arsenal
 * created on 2021/4/17 17:52
 */
@Repository
public interface BrokerMessageLogMapper {

    int deleteByPrimaryKey(String messageId);

    int insert(BrokerMessageLog record);

    int insertSelective(BrokerMessageLog record);

    BrokerMessageLog selectByPrimaryKey(String messageId);

    int updateByPrimaryKeySelective(BrokerMessageLog record);

    int updateByPrimaryKey(BrokerMessageLog record);

    /**
     * 查询消息状态为发送中(0)且已经超时的消息集合
     */
    List<BrokerMessageLog> query4StatusAndTimeoutMessage();

    /**
     * 重新发送统计 count 发送次数 + 1
     */
    void update4ReSend(@Param("messageId") String messageId);

    /**
     * 更新最终消息发送结果 成功 or 失败
     */
    void changeBrokerMessageLogStatus(@Param("messageId") String messageId, @Param("status") String status);
}
