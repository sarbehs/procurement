package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dao.ProUserMapper;
import com.model.ProUser;

import java.security.MessageDigest;

@Component("UserService")
public class UserServiceImp implements UserService {
	
	@Autowired
	ProUserMapper userMapper;
	
	@Override
	public List<ProUser> getAll() {
		return userMapper.getAll();
	}

	@Override
	public ProUser getUserById(Integer id) {
		ProUser i = new ProUser();
		i.setId(id);
		
		List<ProUser> users = userMapper.select(i);
		return (users.size() == 0) ? null : users.get(0);
	}

	@Override
	public ProUser getUserByUserName(String username) {
		ProUser i = new ProUser();
		i.setUsername(username);
		
		List<ProUser> users = userMapper.select(i);
		return (users.size() == 0) ? null : users.get(0);
	}
	
	@Override
	public void updateUser(Integer id, ProUser user) {
		ProUser i = new ProUser();
		i.setId(id);
		ProUser record = userMapper.select(i).get(0);
		
		record.setUsername(user.getUsername());
		record.setPhone(user.getPhone());
		record.setEmail(user.getEmail());
		//record.setPasswd(user.getPasswd());
		record.setPasswd(MD5(user.getPasswd()));
		record.setDepartment(user.getDepartment());
		
		userMapper.update(record);
	}

	@Override
	public void removeUserById(Integer id) {
		ProUser i = new ProUser();
		i.setId(id);
		userMapper.delete(i);
	}

	@Override
	public int createUser(ProUser user) {
		if (getUserByUserName(user.getUsername()) != null)
			return -1;
		user.setPasswd(MD5(user.getPasswd()));
		return (Integer)userMapper.insertSelective(user);
	}
	
	@Override
	public boolean loginUser(String username, String passwd){
		ProUser i = new ProUser();
		i = getUserByUserName(username);
		if (i == null | passwd == null)
			return false;
		
		return MD5(passwd).equals(i.getPasswd());
	}
	
	public String MD5(String md5) {
		   try {
		        MessageDigest md = MessageDigest.getInstance("MD5");
		        byte[] array = md.digest(md5.getBytes());
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < array.length; ++i) {
		          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		       }
		        return sb.toString();
		    } catch (java.security.NoSuchAlgorithmException e) {
		    }
		    return null;
	}
}
