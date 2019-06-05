package com.dyin.service;

import com.dyin.pojo.Gz;

import java.util.List;

public interface GzService {
    /**
     * 删除根据id
     * @param id
     * @return
     */
    int deleteById(Long id);

    /***
     * 添加关注
     * @param record
     * @return
     */
    int insertGz(Gz record);

    /**
     *根据用户查询
     * @return
     */
    List<Gz> selectByUser(Gz gz);
}