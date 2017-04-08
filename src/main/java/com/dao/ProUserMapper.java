package com.dao;

import java.util.List;

import com.model.ProUser;

public interface ProUserMapper {
    int insert(ProUser record);

    int insertSelective(ProUser record);
    
    List<ProUser> getAll();
    
    List<ProUser> select(ProUser record);
    
    void delete(ProUser record);
    
    void update(ProUser record);
}