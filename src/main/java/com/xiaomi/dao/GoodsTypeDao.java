package com.xiaomi.dao;

import com.xiaomi.domain.GoodsType;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

public interface GoodsTypeDao {

    List<GoodsType> findTypeByLevel(int i);

    GoodsType findById(@Param("typeid") int typeid);

    //List<GoodsType> findTypeAll( int pn, int ps, String condition);
    List<GoodsType> findTypeAlls( @Param("offset") int offset, @Param("ps") int ps, @Param("condition") String condition);

    long getCount(@Param("condition") String condition);

    void modifyById(GoodsType goodsType);

    List<GoodsType> findGoodsTypes() throws SQLException;

    void deleteByID(int id);

    void add(GoodsType goodsType);
}
