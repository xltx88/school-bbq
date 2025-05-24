package com.kyriewang.kkbbs.mapper;

import com.kyriewang.kkbbs.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user WHERE name = #{username} or phonenb = #{username}")
    public List<User> findByUsername2(String username);
    @Select("SELECT * FROM user WHERE name = #{name}")
    User findByUsername(String name);


    @Select("select * from where phonenb = #{phonenb}")
    List<User> findByUserphone(String phonenb);


    @Select("select * from user where id=#{id}")
    User getUser(String id);
    @Insert("insert into user (name,account_id,password,salt,gmt_create,gmt_modified,role,register_way,avatar_url) values (#{name},#{account_id},#{password},#{salt},#{gmt_create},#{gmt_modified},#{role},#{register_way},#{avatar_url})")
    void insertUser(User user);

    @Update("update user set avatar_url=#{avatar_url},gmt_modified=#{gmt_modified} where id=#{id}")
    void updateUser(User user);
    @Select("select * from user where account_id=#{account_id}")
    User selectByaccountId(@Param(value="account_id") String account_id);

    @Select("SELECT * FROM user")
    List<User> getusr();
    
    @Insert("INSERT INTO user (name, password,avatarUrl,phonenb) VALUES (#{name}, #{password},#{avatarUrl},#{phonenb})")
    void saveUser(User user);
//    更新用户信息
    @Update("update user set name = #{name},avatarUrl = #{avatarUrl} where id = #{id}")
    int  updUser(String name,String avatarUrl,Long id);
//    修改密码
    @Update("update user set password = #{newPassword} where  id = #{id} ")
    int upPaSS(String newPassword,Long id);
}