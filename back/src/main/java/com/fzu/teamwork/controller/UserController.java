package com.fzu.teamwork.controller;


import com.fzu.teamwork.annoation.AdminLimit;
import com.fzu.teamwork.annoation.LoginToken;
import com.fzu.teamwork.dao.UserDao;
import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.service.LoginService;
import com.fzu.teamwork.service.UserService;
import com.fzu.teamwork.service.UserServiceImpl;
import com.fzu.teamwork.util.ErrorStatus;
import com.fzu.teamwork.view.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@RestController
public class UserController {


    @Resource(name = "userServiceImpl")
    UserServiceImpl userService;

    //获取所有用户
    @LoginToken//需要登录
    @AdminLimit//管理员权限
    @GetMapping("/users")
    public ArrayList<User> getUser()
    {
        return userService.getUsers();
    }

    //按id删除用户
    @LoginToken//需要登录
    @AdminLimit//管理员权限
    @DeleteMapping("/user/{id}")
    public @ResponseBody AjaxResponse deleteUser(@PathVariable int id){
        System.out.println(id);
        userService.deleteUsers(id);
        return AjaxResponse.success();
    }

    //按id数组批量删除用户
    @LoginToken//需要登录
    @AdminLimit//管理员权限
    @DeleteMapping("/users")
    public @ResponseBody AjaxResponse deleteUserAll_test(@RequestBody int[] userIdList){
        userService.deleteUsersAll(userIdList);
        return AjaxResponse.success();
    }

    //添加用户
    @PostMapping("/users")
    @LoginToken//需要登录
    @AdminLimit//管理员权限
    public @ResponseBody AjaxResponse addUser(@RequestBody User user){
        int code = userService.addUser(user);//添加是否成功标志位（0：成功 其他：对应错误状态码）
        String message = "";//错误描述信息
        if(code == 0){//添加成功
            return AjaxResponse.success();
        }else if(code == ErrorStatus.ACCOUNT_ILLEGAL){//账号非法
            message = "添加用户账号非法（应为9位字母数字串组成）";
        }else if(code == ErrorStatus.ID_ILLEGAL){
            message = "添加用户身份证非法";
        }else if(code == ErrorStatus.ACCOUNT_HAS_EXIT){
            message = "添加失败，账户（学号）已被注册";
        }else if(code == ErrorStatus.ID_HAS_EXIT){
            message = "添加失败，身份证已经被注册";
        }
        return AjaxResponse.error(code,message);
    }

    //批量添加用户
    @PostMapping("/userList")
    @LoginToken//需要登录
    @AdminLimit//管理员权限
    public @ResponseBody AjaxResponse addUsers(@RequestBody List<User> users){
        List<String> failedList = userService.addUsers(users);
        if(failedList.size() == 0){//全部添加成功
            return AjaxResponse.success();
        }else{//部分账户数据不合法
            return AjaxResponse.error(ErrorStatus.SOME_USER_ILLEGAL,"部分账户数据不合法",failedList);
        }
    }

    //更新密码
    @LoginToken//需要登录
    @PutMapping("/user")
    public @ResponseBody AjaxResponse updatePassword(@RequestBody User user){
        UserVO userVO = userService.changePassword(user);
        if(userVO == null){
            //密保验证没通过（身份证错误）
            System.out.println("验证错误");
            return AjaxResponse.error(ErrorStatus.CHANGE_PWD_FAILED, "身份证验证错误");
        }else{
            System.out.println("验证正确");
            return AjaxResponse.success(userVO);
        }
    }

    //重置密码
    @PutMapping("/resetPwd")
    public @ResponseBody AjaxResponse resetPassword(@RequestBody User user){
        int result = userService.resetPassword(user);
        if(result == -2){//账号不存在
            return AjaxResponse.error(ErrorStatus.ACCOUNT_NOT_EXIT, "账号不存在");
        }else if(result == -1){//验证信息错误（身份证错误）
            return AjaxResponse.error(ErrorStatus.CHANGE_PWD_FAILED, "身份证错误");
        }else{//重置成功
            return AjaxResponse.success();
        }
    }

}
