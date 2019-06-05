package com.dyin.controller;

import com.dyin.VaildatedGroup.UserGroupLogin;
import com.dyin.VaildatedGroup.UserGroupRegister;
import com.dyin.pojo.DUser;
import com.dyin.pojo.Video;
import com.dyin.service.DUserService;
import com.dyin.service.VideoService;
import com.dyin.utils.UploadFile;
import com.dyin.utils.UploadFileImg;
import com.dyin.utils.ValidatedErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/dyin")
public class VideoController {

        @Autowired
        private VideoService videoService;
        private Map<String,Object> map = null;

    /**
     * 添加视频
     * @param userID
     * @return
     */
    @ResponseBody
        @RequestMapping(value = "/addVideo",method = {RequestMethod.POST})
        public Map<String,Object> addVideo(MultipartFile file, String userID,String videoIntro){
            try {
                map = new HashMap<>();

                Video video = new Video();
                video.setUserid(userID);
                video.setVideoIntro(videoIntro);
                String path = UploadFile.store(file);
                video.setVideopath(path);
              /*  //校验数据
                //校验数据
                map = ValidatedErrors.getErrors(map,br);
                if(map.size()>0){
                    return map;
                }*/
                int result = videoService.insertVideo(video);
                if(result == 0){
                    map.put("msg","0");//返回0 没储存成功
                }else{
                    map.put("msg","1");//返回1 储存成功
                }
            }catch (Exception e){
                //抛出异常
                map.put("msg","0");//返回0 没储存成功
                map.put("ERROR","上传失败");
                return map;
            }
            return map;
        }

    /**
     * 点赞
     * @param video
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateVideoNum",method = {RequestMethod.POST})
    public Map<String,Object> updateNum(@RequestBody Video video){

        try {
            map = new HashMap<>();

              /*  //校验数据
                //校验数据
                map = ValidatedErrors.getErrors(map,br);
                if(map.size()>0){
                    return map;
                }*/
            int result = videoService.updateNum(video);
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
     * 查询所有视频
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findAllVideo",method = {RequestMethod.POST},produces = "application/json;charset=UTF-8")
    public Map<String,Object> findAllVideo(){
        Map<String,Object> map = new HashMap<>();
        try {
            List<Video> lostTypes = videoService.selectAllVideo();
            if(lostTypes.size() == 0){
                map.put("msg",0);
                map.put("ERROR","没有数据");
                return map;
            }
            map.put("msg",1);
            map.put("lostType",lostTypes);
        }catch (Exception e){
            map.put("msg",0);
            map.put("ERROR","没有数据");
        }
        return map;
    }

    /**
     * 根据用户查询视频
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findVideoByUserID",method = {RequestMethod.POST},produces = "application/json;charset=UTF-8")
    public Map<String,Object> findVideoByUserID(@RequestBody Video video){
        Map<String,Object> map = new HashMap<>();
        try {
            List<Video> lostTypes = videoService.selectVideoByUserID(video);
            if(lostTypes.size() == 0){
                map.put("msg",0);
                map.put("ERROR","没有视频");
                return map;
            }
            map.put("msg",1);
            map.put("lostType",lostTypes);
        }catch (Exception e){
            map.put("msg",0);
            map.put("ERROR","没有视频");
        }
        return map;
    }

    /**
     * 根据视频id删除指定视频
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/removeVideo",method = {RequestMethod.POST},produces = "application/json;charset=UTF-8")
    public Map<String,Object> removeVideo(@RequestBody Video video){
        Map<String,Object> map = new HashMap<>();
        try {
            int i = videoService.deleteVideoById(video);
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
