package com.syff.hospital.mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.syff.hospital.domain.User;

/**
 * 该接口中有两个方法，

    一个普通插入：直接用注解搞定
    一个插入返回主键：需要使用xml来搞定
 *
 */
@Component
@Mapper
public interface UserMapper {
    
    @Insert("INSERT INTO user(username, userpassword) VALUES(#{p1},#{p2})")
    public int insertUser(@Param("p1") String username, @Param("p2")  String password);
    
    /**
     * 插入用户，并将主键设置到user中
     * 注意：返回的是数据库影响条数，即1
     */
    public int insertUserWithBackId(User user);
}