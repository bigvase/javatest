package com.test.controller;

import com.test.dao.AddresslistDao;
import com.test.entity.AddresslistEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/address")
public class AddresslistController {

    @RequestMapping("/list")
    public List<AddresslistEntity> list(){
        AddresslistDao dao = new AddresslistDao();
        return dao.query();
    }
}
