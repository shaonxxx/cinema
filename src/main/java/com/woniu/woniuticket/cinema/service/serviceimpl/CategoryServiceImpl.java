package com.woniu.woniuticket.cinema.service.serviceimpl;

import com.woniu.woniuticket.cinema.dao.CategoryMapper;
import com.woniu.woniuticket.cinema.pojo.Category;
import com.woniu.woniuticket.cinema.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;


    @Override
    public List<Category> selectAllCategory() {

        return categoryMapper.selectAllCategory();
    }
}
