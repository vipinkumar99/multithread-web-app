package com.demo.api.response.dto;

import com.demo.api.pojo.EmailPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailResponse extends EmailPojo {
private String status;
}
