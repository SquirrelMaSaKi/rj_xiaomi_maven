package com.xiaomi.service.impl;

import com.xiaomi.dao.GoodsDao;
import com.xiaomi.dao.GoodsTypeDao;
import com.xiaomi.domain.Goods;
import com.xiaomi.domain.GoodsType;
import com.xiaomi.domain.PageBean;
import com.xiaomi.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("goodsService")
public class GoodsServiceIMpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private GoodsTypeDao goodsTypeDao;

    @Override
    public PageBean<Goods> findPageBywhere(int pageNum, int pageSize, String condition) {
        long totalSize = goodsDao.getCount(condition);
        int offset = (pageNum - 1)*pageSize;
        List<Goods> data = goodsDao.findPageByWheres(offset, pageSize, condition);
        for (Goods goods : data) {
            GoodsType goodsType = goodsTypeDao.findById(goods.getTypeid());
            goods.setGoodsType(goodsType);
        }
        return new PageBean<>(pageNum, pageSize, totalSize, data);
    }

    @Override
    public Goods findByID(int gid) {
        Goods goods =  goodsDao.findById(gid);
        //根据商品类型id，查询商品类型
        GoodsType goodsType = goodsTypeDao.findById(goods.getTypeid());
        goods.setGoodsType(goodsType);
        return goods;
    }

    @Override
    public void add(Goods goods) {
        goodsDao.add(goods);
    }

    @Override
    public List<Goods> findByTypeId(int typeid) {
        return goodsDao.findeByTypeId(typeid);
    }

    @Override
    public void modify(Goods goods) {
        goodsDao.update(goods);
    }

    @Override
    public void deleteByPid(int pid) {
        goodsDao.deleteByPid(pid);
    }
}
