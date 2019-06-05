package com.dyin.dao;

import com.dyin.pojo.DUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DUserMapper {

    int register(DUser record);

    DUser login(DUser user);

    int addHead(DUser user);

    int edit(DUser user);
}