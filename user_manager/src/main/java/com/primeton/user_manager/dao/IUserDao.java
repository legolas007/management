package com.primeton.user_manager.dao;

import com.primeton.user_manager.base.IBaseDao;
import com.primeton.user_manager.entity.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Mapper
@Repository
public interface IUserDao extends IBaseDao<TUser> {
	@Update("UPDATE t_user SET islock = #{islock} WHERE id = #{id}")
	public void updateStatus(Map<String, Object> info);

   // @Update("UPDATE t_user SET islock = 0 WHERE id = #{id}")
    void updateLockStatus(int id);

/*    @Select("SELECT id, username, password, email, isLock FROM t_user LIMIT #{index}, #{size}")
    List<TUser> findAll(Map<String, Object> map);*/
     List<TUser> findAll(Map<String, Object> map);

    //void delete(Integer userId);
}
