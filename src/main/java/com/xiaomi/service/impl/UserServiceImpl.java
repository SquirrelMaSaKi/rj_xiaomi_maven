package com.xiaomi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaomi.dao.UserDao;
import com.xiaomi.domain.PageBean;
import com.xiaomi.domain.User;
import com.xiaomi.service.UserService;
import com.xiaomi.utils.EmailUtils;
import com.xiaomi.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void register(User user) {
        //密码加密+发送邮件的业务
        user.setPassword(MD5Utils.md5(user.getPassword()));
        userDao.add(user);

        //发送邮件
        EmailUtils.sendEmail(user);
    }

    @Override
    public User checkUserName(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public User login(String username, String password) {
        //把密码加密后再比对
        password = MD5Utils.md5(password);
        User user = userDao.findByUserNameAndPassword(username, password);
        /*if (user != null) {
            if (user.getFlag() != 1) {
                throw new RuntimeException("此用户未激活或者已经失效");
            }
        }*/
        return user;
    }

    @Override
    public void modifyPassword(int uid, String newpassword) {
        newpassword = MD5Utils.md5(newpassword);
        userDao.updatePassword(uid, newpassword);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public PageBean<User> findByPage(int pageNum, int pageSize, String condition) {
        long totalSize = userDao.getCount(condition);
        int offset = (pageNum-1)*pageSize;
        List<User> data = userDao.findByPages(offset, pageSize, condition);

        PageBean<User> pageBean = new PageBean<>(pageNum, pageSize, totalSize, data);

        return pageBean;
    }

    @Override
    public PageInfo<User> findByPages(int pageNum, int pageSize, String condition) {
        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy("id");
        //userDao.getCount(condition);
        List<User> users = userDao.findByPage(condition);
        return PageInfo.of(users);
    }


    @Override
    public void updateFlag(int uid, int flag) {
        userDao.updateFlag(uid,flag);
    }
}
