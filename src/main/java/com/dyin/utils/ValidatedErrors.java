package com.dyin.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ValidatedErrors {

    public static Map<String,Object> getErrors(Map<String,Object> map, BindingResult br){
        //校验数据
        while (br.hasErrors()){
            //获取错误信息
            List<ObjectError> allErrors = br.getAllErrors();
            map.put("msg","0");//返回0 没储存成功
            List<String> listErrors = new ArrayList<>();
            for (int i = 0; i < allErrors.size(); i++) {
                listErrors.add(allErrors.get(i).getDefaultMessage());
            }
            map.put("ERROR",listErrors);//返回错误信息
            return map;
        }
        return map;
    }
}
