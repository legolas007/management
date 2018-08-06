package com.primeton.user_manager.dao;

import com.primeton.user_manager.base.IBaseDao;
import com.primeton.user_manager.entity.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Mapper
@Repository
public interface IUserDao extends IBaseDao<TUser> {
	@Update("UPDATE t_user SET islock = #{islock} WHERE id = #{id}")
	public void updateStatus(Map<String, Object> info);
	
	
}
