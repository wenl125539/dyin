package com.dyin.service.Impl;

import com.dyin.dao.VideoMapper;
import com.dyin.pojo.Video;
import com.dyin.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "VideoService")
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;


    @Override
    public int deleteVideoById(Video video) {
        int i = 0;
        if(video.getUserid().equals(videoMapper.selectVideoById(video.getVideoid()).getUserid())){
            i = videoMapper.deleteVideoById(video.getVideoid());
        }
        return i;
    }

    @Override
    public int insertVideo(Video record) {
        return videoMapper.insertVideo(record);
    }

    @Override
    public Video selectVideoById(Long videoid) {
        return videoMapper.selectVideoById(videoid);
    }

    @Override
    public List<Video> selectAllVideo() {
        return videoMapper.selectAllVideo();
    }

    @Override
    public List<Video> selectVideoByUserID(Video record) {
        return videoMapper.selectVideoByUserID(record);
    }

    @Override
    public int updateNum(Video record) {

        return videoMapper.updateNum(record);
    }

}