package com.dyin.service.Impl;

import com.dyin.dao.DUserMapper;
import com.dyin.pojo.DUser;
import com.dyin.service.DUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "dUserService")
public class DUserServiceImpl implements DUserService {

    @Autowired
    private DUserMapper dUserMapper;

    @Override
    public int register(DUser record) {
        return dUserMapper.register(record);
    }

    @Override
    public DUser login(DUser user) {
        DUser user2 = dUserMapper.login(user);
        if(user.getPassword().equals(user2.getPassword())){
            return user2;
        }
        return null;
    }

    @Override
    public int addHead(DUser user) {
        if(dUserMapper.login(user) == null){
            return 0;
        }
        return dUserMapper.addHead(user);
    }

    @Override
    public int edit(DUser user) {
        return dUserMapper.edit(user);
    }
}