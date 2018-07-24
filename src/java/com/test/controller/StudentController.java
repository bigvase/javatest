package com.test.controller;

import com.test.core.BaseService;
import com.test.dao.StudentDao;
import com.test.entity.StudentEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController extends BaseService {
    //映射一个action
    @RequestMapping("/studentList")
    @ResponseBody
    public List<StudentEntity> getUser() {
        StudentDao dao = new StudentDao();
        //查询stage表的所有数据，返回json
        return dao.query();
    }

}
