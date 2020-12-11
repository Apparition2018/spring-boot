package com.ljh.transaction.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * User
 *
 * @author Arsenal
 * created on 2020/1/2 17:48
 */
@Entity
@Getter
@Setter
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String name;
    
    private String company;
    
}
