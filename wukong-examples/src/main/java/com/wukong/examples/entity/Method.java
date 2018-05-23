package com.wukong.examples.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;

@Data
public class Method {
    @Length(min = 6,max = 50)
    private String name;

    @Email
    private String email;
    private String cellPhone;
}
