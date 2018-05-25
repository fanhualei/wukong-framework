package com.wukong.examples.entity;

import com.wukong.core.validator.Phone;
import lombok.Data;

@Data
public class User {
    @Phone
    private String phone;
}
