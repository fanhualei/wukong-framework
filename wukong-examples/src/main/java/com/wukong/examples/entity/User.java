package com.wukong.examples.entity;

import com.wukong.examples.validator.Phone;
import lombok.Data;

@Data
public class User {
    @Phone
    private String phone;
}
