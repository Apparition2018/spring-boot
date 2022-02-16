package com.ljh.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ljh.dao.BrokerMessageLogMapper;
import com.ljh.entity.BrokerMessageLog;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * BrokerMessageLogService
 *
 * @author Arsenal
 * created on 2022/2/16 13:02
 */
@Service
public class BrokerMessageLogService {

    private final BrokerMessageLogMapper brokerMessageLogMapper;

    public BrokerMessageLogService(BrokerMessageLogMapper brokerMessageLogMapper) {
        this.brokerMessageLogMapper = brokerMessageLogMapper;
    }

    /**
     * 查找投递中（status=0）且 已到达下一次投递时间（next_retry_time < now()）的消息
     */
    public List<BrokerMessageLog> selectRetry() {
        QueryWrapper<BrokerMessageLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", "0").le("next_retry_time", new Date());
        return brokerMessageLogMapper.selectList(queryWrapper);
    }

    /**
     * 重试次数+1
     */
    public void tryCountPlusOne(String messageId) {
        UpdateWrapper<BrokerMessageLog> updateWrapper = new UpdateWrapper<>();
        updateWrapper.setSql("try_count = try_count + 1").eq("message_id", messageId);
        brokerMessageLogMapper.update(null, updateWrapper);
    }

    /**
     * 更新投递状态（stats）
     */
    public void updateStatusByMessageId(String messageId, Integer status) {
        UpdateWrapper<BrokerMessageLog> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", status).eq("message_id", messageId);
        brokerMessageLogMapper.update(null, updateWrapper);
    }
}
