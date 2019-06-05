package com.dyin.service;

import com.dyin.pojo.DUser;



public interface DUserService {

    int register(DUser record);

    DUser login(DUser user);

    int addHead(DUser user);

    int edit(DUser user);
}