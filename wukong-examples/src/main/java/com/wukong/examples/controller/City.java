package com.wukong.examples.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *这个类是用来测试HelloController
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {
    private int id;
    private String name;
    private String code;
}
