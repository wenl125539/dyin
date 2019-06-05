package com.dyin.service.Impl;

import com.dyin.dao.PlMapper;
import com.dyin.pojo.Pl;
import com.dyin.service.PlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "PlServiceImpl")
public class PlServiceImpl implements PlService {

    @Autowired
    private PlMapper plMapper;

    @Override
    public int deletePlById(Pl pl) {
        int i = 0;
        if(pl.getUserid().equals(plMapper.selectPlById(pl).getUserid())){
            i = plMapper.deletePlById(pl.getId());
        }
        return i;
    }

    @Override
    public int insertPl(Pl record) {
        return plMapper.insertPl(record);
    }

    @Override
    public List<Pl> selectPlByVideoId(Pl record) {
        return plMapper.selectPlByVideoId(record);
    }

    @Override
    public int updateNum(Pl record) {
        return plMapper.updateNum(record);
    }
}