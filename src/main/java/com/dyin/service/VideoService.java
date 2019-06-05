package com.dyin.service;

import com.dyin.pojo.Video;

import java.util.List;

public interface VideoService {
    int deleteVideoById(Video video);

    int insertVideo(Video record);

    Video selectVideoById(Long videoid);

    List<Video> selectAllVideo();

    List<Video> selectVideoByUserID(Video record);

    int updateNum(Video record);

}