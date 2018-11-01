package com.neuedu.controller.backend;

import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 后台用户控制器类
 * */
@RestController
@RequestMapping(value = "/guanli/user")
public class UserManagerController {
    /**
     * 管理员登录
     * */
    @Autowired
    IUserService userService;
    @RequestMapping(value = "/login.do")
    public ServerResponse login(HttpSession session, String username, String password) {
        ServerResponse serverResponse=userService.login(username,password);
        if(serverResponse.isSuccess()){//登录成功
            UserInfo userInfo= (UserInfo) serverResponse.getData();
            if (userInfo.getRole()==Const.RoleEnum.ROLE_CUSTOMER.getCode()){
                return ServerResponse.serverResponseByError("无权限登录");
            }
            session.setAttribute(Const.CURRENTUSER,userInfo);

        }

        return serverResponse;
    }







}
