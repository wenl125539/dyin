package com.dyin.dao;

import com.dyin.pojo.Gz;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GzMapper {
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