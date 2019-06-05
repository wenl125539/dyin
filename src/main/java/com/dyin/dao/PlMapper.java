package com.dyin.dao;

import com.dyin.pojo.Pl;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PlMapper {
    int deletePlById(Long id);

    int insertPl(Pl record);

    List<Pl> selectPlByVideoId(Pl record);

    int updateNum(Pl pl);

    Pl selectPlById(Pl record);
}
