package com.xiaomi.dao;

import com.xiaomi.domain.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartDao {
    Cart findByUidAndPid(@Param("uid") int uid, @Param("pid") int pid);

    void add(Cart cart);

    void update(Cart cart);

    List<Cart> findByUid(@Param("uid") int id);

    void delete(@Param("id") int id, @Param("pid") int pid);

    void deleteByUid(int id);

    List<Cart> findByPid(int pid);
}
