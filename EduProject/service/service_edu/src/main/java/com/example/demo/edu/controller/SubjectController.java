package com.example.demo.edu.controller;


import com.example.R;
import com.example.demo.edu.entity.subject.OneSubject;
import com.example.demo.edu.service.EduSubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author NutF4n
 * @since 2023-01-21
 */
@RestController
@RequestMapping("/eduservice/subject")
//@CrossOrigin
public class SubjectController {

    @Autowired
    private EduSubjectService edusubjectSevice;

    //课程分类
    //获取上传文件,读取内容
    @PostMapping("addSubject")
    public R addsubject(MultipartFile file){
        edusubjectSevice.saveSubject(file,edusubjectSevice);
        return R.ok();
    }

    //课程分类管理(树形)
    @GetMapping("getAllSubject")
    public R get(){

        List<OneSubject> list =  edusubjectSevice.getAllOneTwoSubject();
        return R.ok().data("list",list);
    }



}

