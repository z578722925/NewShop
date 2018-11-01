package com.neuedu.controller.backend;


import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/guanli/category")
public class CategoryManagerController {

    @Autowired
    ICategoryService categoryService;
    /**
     * 获取品类的子节点
     * */
    @RequestMapping("/get_category.do")
    public ServerResponse get_category(HttpSession session,Integer categoryId){
        UserInfo userInfo= (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.serverResponseByError(Const.ResponseCodeEnum.NEED_LOGIN.getCode(),Const.ResponseCodeEnum.NEED_LOGIN.getDesc());
        }
        //判断用户权限
        if (userInfo.getRole()!=Const.RoleEnum.ROLE_ADMIN.getCode()){
            return ServerResponse.serverResponseByError(Const.ResponseCodeEnum.NO_PRIVILEGE.getCode(),Const.ResponseCodeEnum.NO_PRIVILEGE.getDesc());
        }

        return categoryService.get_category(categoryId);
    }


    /**
     * 增加节点（平级）
     * */
    @RequestMapping("/add_category.do")
    public ServerResponse add_category(HttpSession session,@RequestParam(required = false,defaultValue = "0") Integer parentId,String categoryName){
        UserInfo userInfo= (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.serverResponseByError(Const.ResponseCodeEnum.NEED_LOGIN.getDesc());
        }
        //判断用户权限
        if (userInfo.getRole()!=Const.RoleEnum.ROLE_ADMIN.getCode()){
            return ServerResponse.serverResponseByError(Const.ResponseCodeEnum.NO_PRIVILEGE.getCode(),Const.ResponseCodeEnum.NO_PRIVILEGE.getDesc());
        }

        return categoryService.add_category(parentId,categoryName);
    }

    /**
     * 修改节点
     * */
    @RequestMapping("/set_category_name.do")
    public ServerResponse set_category_name(HttpSession session,Integer categoryId,String categoryName){
        UserInfo userInfo= (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.serverResponseByError(Const.ResponseCodeEnum.NEED_LOGIN.getDesc());
        }
        //判断用户权限
        if (userInfo.getRole()!=Const.RoleEnum.ROLE_ADMIN.getCode()){
            return ServerResponse.serverResponseByError(Const.ResponseCodeEnum.NO_PRIVILEGE.getCode(),Const.ResponseCodeEnum.NO_PRIVILEGE.getDesc());
        }

        return categoryService.set_category_name(categoryId,categoryName);
    }



    /**
     * 获取当前分类id及递归子节点categoryId
     * */
    @RequestMapping("/get_deep_categroy.do")
    public ServerResponse get_deep_categroy(HttpSession session,Integer categoryId){
        UserInfo userInfo= (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.serverResponseByError(Const.ResponseCodeEnum.NEED_LOGIN.getDesc());
        }
        //判断用户权限
        if (userInfo.getRole()!=Const.RoleEnum.ROLE_ADMIN.getCode()){
            return ServerResponse.serverResponseByError(Const.ResponseCodeEnum.NO_PRIVILEGE.getCode(),Const.ResponseCodeEnum.NO_PRIVILEGE.getDesc());
        }

        return categoryService.get_deep_category(categoryId);
    }


}
