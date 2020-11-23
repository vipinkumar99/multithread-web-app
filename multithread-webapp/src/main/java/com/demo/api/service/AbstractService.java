package com.demo.api.service;

import java.util.List;

public interface AbstractService<R,S> {
R save(S request);
R getById(Long id);
List<R> getAll();
}
