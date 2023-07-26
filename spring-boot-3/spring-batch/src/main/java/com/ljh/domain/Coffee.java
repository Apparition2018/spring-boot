package com.ljh.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Coffee
 *
 * @author ljh
 * @since 2023/7/25 14:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {
    private String brand;
    private String origin;
    private String characteristics;
}
