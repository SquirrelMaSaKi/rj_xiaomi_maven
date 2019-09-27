package com.xiaomi.service.impl;

import com.xiaomi.dao.GoodsTypeDao;
import com.xiaomi.domain.GoodsType;
import com.xiaomi.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service("goodsTypeService")
public class GoodsTypeServiceImpl implements GoodsTypeService {
    @Autowired
    private GoodsTypeDao goodsTypeDao;
    @Autowired
    private GoodsTypeService goodsTypeService;

    @Override
    public List<GoodsType> findTypeByLevel(int i) {
        return goodsTypeDao.findTypeByLevel(i);
    }

    @Override
    public List<GoodsType> findTypeAll(int pn, int ps, String condition) {
        int offset = (pn-1)*ps;
        List<GoodsType> goodsTypeList = goodsTypeDao.findTypeAlls(offset, ps, condition);

        for (GoodsType goodsType : goodsTypeList) {
            int parent = goodsType.getParent();
            if (parent == 0) {
                goodsType.setParentName(goodsType.getName());
            } else {
                GoodsType goodsType1 = goodsTypeService.findTypeById(parent);
                goodsType.setParentName(goodsType1.getName());
            }
        }

        return goodsTypeList;
    }

    @Override
    public long getCount(String condition) {
        return goodsTypeDao.getCount(condition);
    }

    @Override
    public GoodsType findTypeById(int id) {
        return goodsTypeDao.findById(id);
    }

    @Override
    public void modifyById(GoodsType goodsType) {
        goodsTypeDao.modifyById(goodsType);
    }

    @Override
    public List<GoodsType> findGoodsTypes() {
        try {
            return goodsTypeDao.findGoodsTypes();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询失败", e);
        }
    }

    @Override
    public void deleteByID(int id) {
        goodsTypeDao.deleteByID(id);
    }

    @Override
    public void add(GoodsType goodsType) {
        goodsTypeDao.add(goodsType);
    }
}
