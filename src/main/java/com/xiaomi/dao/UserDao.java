package com.xiaomi.dao;

import com.xiaomi.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    List<User> findAll();
    User findById(Integer id);
    User findByUserNameAndPassword(@Param("username") String username, @Param("password") String password);
    User findByUserName(String username);
    void add(User user);
    void update(User user);
    void delete(Integer id);

    void updatePassword(@Param("uid") int uid, @Param("newpassword") String newpassword);

    long getCount(@Param("condition") String condition);

//    List<User> findByPage(int pageNum, int pageSize, String condition);

    //这里需要计算一下offset
    List<User> findByPages(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("condition") String condition);

    void updateFlag(@Param("uid") int uid, @Param("flag") int flag);

    List<User> findByPage(@Param("condition") String condition);
}
