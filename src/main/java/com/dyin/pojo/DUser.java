package com.dyin.pojo;

import com.dyin.VaildatedGroup.UserGroupLogin;
import com.dyin.VaildatedGroup.UserGroupRegister;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class DUser {
    private Integer id;
    @NotBlank(message = "用户名不能为空"
            ,groups = {UserGroupRegister.class,UserGroupLogin.class})
    @Size(max = 20,min = 8,message = "用户名长度为8-20之间"
            ,groups = {UserGroupRegister.class,UserGroupLogin.class})
    private String username;
    @Size(max = 20,min = 8,message = "密码长度为8-20之间"
            ,groups = {UserGroupRegister.class,UserGroupLogin.class})
    @NotBlank(message = "密码不能为空"
            ,groups = {UserGroupRegister.class,UserGroupLogin.class})
    private String password;

    private String gender;

    private String introduce;

    private String head;

    private String nname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head == null ? null : head.trim();
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname == null ? null : nname.trim();
    }
}