package com.dao;

import com.model.ProUser;

public interface ProUserMapper {
    int insert(ProUser record);

    int insertSelective(ProUser record);
}