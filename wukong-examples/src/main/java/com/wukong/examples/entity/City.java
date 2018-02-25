package com.wukong.examples.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 *这个类是用来测试HelloController
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class City implements Serializable {

    private static final long serialVersionUID = 3221700752972709820L;

    private int id;
    private String name;
    private String code;
}
