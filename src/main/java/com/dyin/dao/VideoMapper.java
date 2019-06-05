package com.dyin.dao;

import com.dyin.pojo.Video;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface VideoMapper {

    int deleteVideoById(Long videoid);

    int insertVideo(Video record);

    Video selectVideoById(Long videoid);

    List<Video> selectVideoByUserID(Video record);

    List<Video> selectAllVideo();

    int updateNum(Video video);
}