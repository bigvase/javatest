package com.test.controller;

import com.test.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/User")//Contoller下所有接口统一入口
public class UserController {

    //映射一个action
    @RequestMapping("/user")
    /*ResponseBody
     * 该注解用于读取Request请求的body部分数据，使用系统默认配置的HttpMessageConverter进行解析，
     * 然后把相应的数据绑定到要返回的对象上 ,
     * 再把HttpMessageConverter返回的对象数据绑定到 controller中方法的参数上
     */

//    @ResponseBody//表示直接输出返回内容，不进行jsp或html跳转，本例是为了写接口，这里直接返回json
    public String getUser() {
        String name = "jack";
        Integer age  = 12;
        //创建一个UserEntity，直接返回，之前在web.xml中配置的jackson会将user对象转为json输出
        UserEntity user = new UserEntity(name, age);
        return "test/user";
    }

    @RequestMapping("/list")
    public String list(Map<String,Object> map){
        String name = "jack";
        Integer age  = 12;
        UserEntity user = new UserEntity(name,age);
        map.put("user",user);
        return "User/list";
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/home")
    public ModelAndView home(){
        List<String> list=new ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        return new ModelAndView("User/home", "list", list);//直接将值和路径放到构造方法中返回
    }

    @RequestMapping(value = "/home1")//HttpServletRequest request, HttpServletResponse response
    public ModelAndView home1(){
        List<String> list=new ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        ModelAndView mav=new ModelAndView();
        mav.setViewName("User/home1");//返回路径
        mav.addObject("list", list);//使用ModelAndView的addObject方法将list放进去
        return mav;
    }

    @RequestMapping(value = "/home2")
    public ModelAndView home2(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("User/home2");//返回路径
        mav.addObject("msg", "我要返回值");
        return mav;
    }

    @RequestMapping(value = "/home3")
    public ModelAndView home3(){
        Map<String, String> map=new HashMap<String, String>();
        map.put("PK", "北京");
        map.put("SH", "上海");
        ModelAndView mav=new ModelAndView();
        mav.setViewName("User/home3");//返回路径
        mav.addObject("map", map);
        return mav;
    }

    @RequestMapping(value = "/home4")
    public ModelAndView home4(){
        Map<String, String> map=new HashMap<String, String>();
        map.put("PK", "北京");
        map.put("SH", "上海");
        return new ModelAndView("User/home4","map", map).addObject("msg", "我要返回值");
    }

}
