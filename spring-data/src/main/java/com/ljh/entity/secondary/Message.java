package com.ljh.entity.secondary;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Message
 *
 * @author ljh
 * created on 2021/8/30 17:56
 */
@Entity
@Data
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String message;

    public Message(String title, String message) {
        this.title = title;
        this.message = message;
    }
}
