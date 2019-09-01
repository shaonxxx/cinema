package com.woniu.woniuticket.cinema.controller;


import com.woniu.woniuticket.cinema.pojo.Category;
import com.woniu.woniuticket.cinema.pojo.Result;
import com.woniu.woniuticket.cinema.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/category")
    public Result<List<Category>> getAllCateiry(){
        List<Category> list = categoryService.selectAllCategory();
        Result<List<Category>> result=new Result<>();
        result.setData(list);
        result.setCode("200");
        return result;
    }

}
