package com.dyin.controller;

import com.dyin.pojo.Pl;
import com.dyin.pojo.Video;
import com.dyin.service.PlService;
import com.dyin.service.VideoService;
import com.dyin.utils.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/dyin")
public class PlController {

        @Autowired
        private PlService plService;
        private Map<String,Object> map = null;

    /**
     * 添加评论
     * @param pl
     * @return
     */
    @ResponseBody
        @RequestMapping(value = "/addPl",method = {RequestMethod.POST})
        public Map<String,Object> addPl(@RequestBody Pl pl){
            try {
                map = new HashMap<>();

                if(pl.getComment().trim().equals("")){
                    map.put("msg","0");//返回0 没储存成功
                    map.put("ERROR","评论内容不能为空");//返回0 没储存成功
                    return map;
                }
              /*  //校验数据
                //校验数据
                map = ValidatedErrors.getErrors(map,br);
                if(map.size()>0){
                    return map;
                }*/
                int result = plService.insertPl(pl);
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
     * 点赞
     * @param pl
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updatePlNum",method = {RequestMethod.POST})
    public Map<String,Object> updateNum(@RequestBody Pl pl){

        try {
            map = new HashMap<>();

              /*  //校验数据
                //校验数据
                map = ValidatedErrors.getErrors(map,br);
                if(map.size()>0){
                    return map;
                }*/
            int result = plService.updateNum(pl);
            if(result == 0){
                map.put("msg","0");//返回0 没储存成功
            }else{
                map.put("msg","1");//返回1 储存成功
            }
        }catch (Exception e){
            //抛出异常
            map.put("msg","0");//返回0 没储存成功
            map.put("ERROR","点赞失败");
            return map;
        }
        return map;
    }



    /**
     * 根据视频 获取评论
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectPlByVideoId",method = {RequestMethod.POST},produces = "application/json;charset=UTF-8")
    public Map<String,Object> findVideoByUserID(@RequestBody  Pl pl){
        Map<String,Object> map = new HashMap<>();
        try {
            List<Pl> lostTypes = plService.selectPlByVideoId(pl);
            if(lostTypes.size() == 0){
                map.put("msg",0);
                map.put("ERROR","没有评论");
                return map;
            }
            map.put("msg",1);
            map.put("lostType",lostTypes);
        }catch (Exception e){
            map.put("msg",0);
            map.put("ERROR","没有评论");
        }
        return map;
    }

    /**
     * 根据用户删除指定评论
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/removePl",method = {RequestMethod.POST},produces = "application/json;charset=UTF-8")
    public Map<String,Object> removeVideo(@RequestBody Pl pl){
        Map<String,Object> map = new HashMap<>();
        try {
            int i = plService.deletePlById(pl);
            if(i == 0){
                map.put("msg",0);
                map.put("ERROR","删除失败");
                return map;
            }
            map.put("msg",1);
        }catch (Exception e){
            map.put("msg",0);
            map.put("ERROR","删除失败");

        }
        return map;
    }

}
