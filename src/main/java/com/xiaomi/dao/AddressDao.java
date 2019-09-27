package com.xiaomi.dao;

import com.xiaomi.domain.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressDao {
    public List<Address> findByUid(int uid);

    void add(Address address);

    //void updateDefault(@Param("aid") int aid, @Param("uid") int uid);

    void updateDefault2(@Param("aid") int aid);

    void updateDefault1(@Param("uid") int uid);

    void delete(int id);

    void update(Address address);

    Address findById(int aid);
}
