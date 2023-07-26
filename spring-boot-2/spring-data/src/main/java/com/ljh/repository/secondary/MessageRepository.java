package com.ljh.repository.secondary;

import com.ljh.entity.secondary.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * MessageRepository
 *
 * @author ljh
 * created on 2021/8/30 17:57
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
}
