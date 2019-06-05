package com.dyin.controller;

import com.dyin.pojo.Gz;
import com.dyin.pojo.Pl;
import com.dyin.service.GzService;
import com.dyin.service.PlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/dyin")
public class GzController {

        @Autowired
        private GzService gzService;
        private Map<String,Object> map = null;

    /**
     * 添加评论
     * @param gz
     * @return
     */
    @ResponseBody
        @RequestMapping(value = "/addGz",method = {RequestMethod.POST})
        public Map<String,Object> addGz(@RequestBody Gz gz){
            try {
                map = new HashMap<>();


                int result = gzService.insertGz(gz);
                if(result == 0){
                    map.put("msg","0");//返回0 没储存成功
                }else{
                    map.put("msg","1");//返回1 储存成功
                }
            }catch (Exception e){
                //抛出异常
                map.put("msg","0");//返回0 没储存成功
                map.put("ERROR","评论失败");
                return map;
            }
            return map;
        }




    /**
     * 根据视频 获取评论
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findByUser",method = {RequestMethod.POST},produces = "application/json;charset=UTF-8")
    public Map<String,Object> selectByUser(@RequestBody  Gz gz){
        Map<String,Object> map = new HashMap<>();
        try {
            List<Gz> lostTypes = gzService.selectByUser(gz);
            if(lostTypes.size() == 0){
                map.put("msg",0);
                map.put("error","没有关注");
                return map;
            }
            map.put("msg",1);
            map.put("lostType",lostTypes);
        }catch (Exception e){
            map.put("msg",0);
            map.put("error","没有关注");
        }
        return map;
    }

    /**
     * 根据用户取关
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/removeGz",method = {RequestMethod.POST},produces = "application/json;charset=UTF-8")
    public Map<String,Object> removeGz(@RequestBody Gz gz){
        Map<String,Object> map = new HashMap<>();
        try {
            int i = gzService.deleteById(gz.getId());
            if(i == 0){
                map.put("msg",0);
                map.put("error","取消关注失败");
                return map;
            }
            map.put("msg",1);
        }catch (Exception e){
            map.put("msg",0);
            map.put("error","取消关注失败");

        }
        return map;
    }

}
