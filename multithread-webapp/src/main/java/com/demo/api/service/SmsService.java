package com.demo.api.service;

import java.util.List;

import com.demo.api.pojo.SmsPojo;
import com.demo.api.response.dto.SmsResponse;

public interface SmsService extends AbstractService<SmsResponse, SmsPojo>{
boolean sendSms(List<SmsPojo>requestList);
}
