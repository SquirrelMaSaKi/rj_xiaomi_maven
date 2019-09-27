package com.xiaomi.dao;

import com.xiaomi.domain.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsDao {
    long getCount(@Param("condition") String condition);
    //List<Goods> findPageByWhere(int pageNum, int pageSize, String condition);
    List<Goods> findPageByWheres(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("condition") String condition);

    Goods findById(int gid);

    void add(Goods goods);

    List<Goods> findeByTypeId(int typeid);

    void update(Goods goods);

    void deleteByPid(@Param("pid") int pid);
}
