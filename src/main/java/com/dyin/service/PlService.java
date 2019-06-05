package com.dyin.service;

import com.dyin.pojo.Pl;

import java.util.List;

public interface PlService {
    int deletePlById(Pl pl);

    int insertPl(Pl record);

    List<Pl> selectPlByVideoId(Pl record);

    int updateNum(Pl record);

}