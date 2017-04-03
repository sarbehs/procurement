package com.dao;

import java.util.List;

import com.model.Item;

public interface ItemMapper {
    int insert(Item record);

    int insertSelective(Item record);
    
    List<Item> getAll();
    
    Item selectById(int id);
    
    List<Item> select(Item record);
    
    void delete(Item record);
    
    void update(Item record);
}