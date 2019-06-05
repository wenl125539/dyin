package com.dyin.controller;

import com.dyin.VaildatedGroup.UserGroupLogin;
import com.dyin.VaildatedGroup.UserGroupRegister;
import com.dyin.pojo.DUser;
import com.dyin.service.DUserService;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/dyin")
public class UserController {

        @Autowired
        private DUserService dUserService;
        private Map<String,Object> map = null;

    /**
     * 注册
     * @param user
     * @return
     */
    @ResponseBody
        @RequestMapping(value = "/register",method = {RequestMethod.POST})
        public Map<String,Object> register(@RequestBody @Validated(value = {UserGroupRegister.class}) DUser user, BindingResult br){
            try {
                map = new HashMap<>();

                //校验数据
                //校验数据
                map = ValidatedErrors.getErrors(map,br);
                if(map.size()>0){
                    return map;
                }
                int result = dUserService.register(user);
                if(result == 0){
                    map.put("msg","0");//返回0 没储存成功
                }else{
                    map.put("msg","1");//返回1 储存成功
                }
            }catch (Exception e){
                //抛出异常
                map.put("msg","0");//返回0 没储存成功
                map.put("ERROR","用户已存在");
                return map;
            }
            return map;
        }

    /**
     * 登录
     * @param user
     * @return
     */
        @ResponseBody
        @RequestMapping(value = "/login",method = {RequestMethod.POST})
        public Map<String,Object> login(@RequestBody @Validated(value = {UserGroupLogin.class}) DUser user, BindingResult br, ModelAndView modelAndView){


            Map<String,Object> map = new HashMap<>();
            try{
                //校验数据
                map = ValidatedErrors.getErrors(map,br);
                if(map.size()>0){
                    return map;
                }
                //获取用户
                DUser user2 = dUserService.login(user);
                if(user2 == null){
                    map.put("msg","0");
                    map.put("ERROR","用户名或密码错误");
                    return map;
                }else{
                    System.out.println(user2.getUsername()+user2.getPassword()+"AaaaA");
                    map.put("msg","1");
                    map.put("user",user2);
                    return map;
                }
            }catch (Exception e){
                map.put("msg","0");
                map.put("ERROR","用户名或密码错误");
                return map;
            }

        }

    /**
     * 上传头像
     * @param file
     * @param username
     * @return
     */
        @ResponseBody
        @RequestMapping(value = "/upload",method = {RequestMethod.POST},produces = "application/json;charset=UTF-8")
        public Map<String,Object> upload(MultipartFile file, String username){
           try {
               map = null;
               map = new HashMap<>();

               if(file != null){
                   String path2 = UploadFileImg.store(file);

                   //保存数据库
                   DUser user = new DUser();
                   user.setUsername(username);
                   user.setHead(path2);
                   int i = dUserService.addHead(user);

                   if(i==0){
                       map.put("ERROR","保存出错");
                       return map;
                   }
                   map.put("msg","1");
               }
           }catch (Exception e){
               map.put("msg","0");
               map.put("ERROR","保存失败");

               return map;
           }
            return map;
        }


    /**
     * 用户修改资料
     */
    @ResponseBody
    @RequestMapping(value = "/edit",method = {RequestMethod.POST})
    public Map<String,Object> edit(@RequestBody  DUser user){
        try {
            map = null;
            map = new HashMap<>();
          /*  //校验数据
            map = ValidatedErrors.getErrors(map,br);
            if(map.size()>0){
                return map;
            }*/

            int result = dUserService.edit(user);
            if(result == 0){
                map.put("msg","0");//返回0 没储存成功
                map.put("ERROR","用户不存在");
            }else{
                map.put("msg","1");//返回1 储存成功
            }
        }catch (Exception e){
            //抛出异常
            map.put("msg","0");//返回0 没储存成功
            map.put("ERROR","修改失败");
            return map;
        }
        return map;
    }

}
