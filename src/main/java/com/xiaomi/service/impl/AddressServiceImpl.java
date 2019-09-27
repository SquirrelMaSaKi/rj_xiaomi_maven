package com.xiaomi.service.impl;

import com.xiaomi.dao.AddressDao;
import com.xiaomi.domain.Address;
import com.xiaomi.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("addressService")
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressDao addressDao;
    @Override
    public List<Address> findByUid(int uid) {
        return addressDao.findByUid(uid);
    }

    @Override
    public void add(Address address) {
        addressDao.add(address);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDefault(int aid, int uid) {
            addressDao.updateDefault1(uid);
            addressDao.updateDefault2(aid);
    }

    @Override
    public void delete(int id) {
        addressDao.delete(id);
    }

    @Override
    public void update(Address address) {
        addressDao.update(address);
    }
}
