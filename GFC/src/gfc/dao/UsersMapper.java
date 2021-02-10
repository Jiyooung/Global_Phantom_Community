package gfc.dao;

import org.apache.ibatis.annotations.Mapper;

import gfc.dto.User;

@Mapper
public interface UsersMapper {
	public User getUser(String id);
	public int addUser(User user);
}

