package com.service;

import java.util.List;

import com.model.ProUser;

public interface UserService {
	List<ProUser> getAll();
	
	ProUser getUserById(Integer id);
	
	ProUser getUserByUserName(String username);
	
	void updateUser(Integer id, ProUser user);
	
	void removeUserById(Integer id);
	
	int createUser(ProUser user);
	
	boolean loginUser(String username, String passwd);
	
}
