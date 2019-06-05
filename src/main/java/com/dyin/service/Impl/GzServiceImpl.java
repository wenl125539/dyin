package com.dyin.service.Impl;

import com.dyin.dao.GzMapper;
import com.dyin.pojo.Gz;
import com.dyin.service.GzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "gzService")
public class GzServiceImpl implements GzService {

    @Autowired
    private GzMapper gzMapper;

    @Override
    public int deleteById(Long id) {
        return gzMapper.deleteById(id);
    }

    @Override
    public int insertGz(Gz record) {
        List<Gz> list = selectByUser(record);
        if(list != null){
            for (Gz gz : list) {
                if(gz.getUserid().equals(record.getUserid())&&gz.getUserid2().equals(record.getUserid2())){
                    return 0;
                }
            }
        }
        return gzMapper.insertGz(record);
    }

    @Override
    public List<Gz> selectByUser(Gz gz) {
        return gzMapper.selectByUser(gz);
    }
}