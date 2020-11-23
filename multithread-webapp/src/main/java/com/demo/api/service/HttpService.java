package com.demo.api.service;

import com.demo.api.external.response.ExternalResponse;

public interface HttpService {
ExternalResponse postRequest(String url,Object request);
}
