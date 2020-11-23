package com.demo.api.service;

import com.demo.api.dto.BaseResponse;

public interface AbstractService<S> {
BaseResponse save(S request) throws Exception;
}
