package com.demo.api.service;

import org.springframework.web.multipart.MultipartFile;

import com.demo.api.pojo.UserPojo;
import com.demo.api.response.dto.UserResponse;

public interface UserService extends AbstractService<UserResponse, UserPojo>{
boolean save(MultipartFile file);
boolean sendMsg();
boolean updateList();
}
