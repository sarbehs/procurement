package com.dao;

import java.util.List;

import com.model.OrderWithBLOBs;

public interface ProOrderMapper {
	
    int insert(OrderWithBLOBs record);

    int insertSelective(OrderWithBLOBs record);
    
    List<OrderWithBLOBs> select(OrderWithBLOBs record);
    
    void update(OrderWithBLOBs record);
    
    void delete(OrderWithBLOBs record);
}