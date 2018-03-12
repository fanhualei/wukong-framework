package com.wukong.examples.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

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

    @Range(min = 1, max = 20, message = "id只能从1-20")
    private int id;
    @Length(min = 6,max = 50)
    private String name;
    private String code;
}
