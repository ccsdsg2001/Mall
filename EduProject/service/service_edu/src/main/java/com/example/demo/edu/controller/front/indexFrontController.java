package com.example.demo.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.R;
import com.example.demo.edu.entity.EduCourse;
import com.example.demo.edu.entity.Teacher;
import com.example.demo.edu.service.EduCourseService;
import com.example.demo.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author cc
 * @date 2023年01月27日 15:41
 */
@RestController
@RequestMapping("/eduservice/indexfront")
//@CrossOrigin
public class indexFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private TeacherService teacherService;
    //select 8 course and 4 teacher
    @GetMapping("index")
    //写成两个方法加上注解  redis缓存   @Cacheable(key = "'course'",value = "banner")
    public R index(){
        QueryWrapper<EduCourse> wrapper=new QueryWrapper<>();

        wrapper.orderByDesc("id");
        wrapper.last("limit 8");

        List<EduCourse> list = courseService.list(wrapper);


//         @Cacheable(key = "'teacher'",value = "banner")
        QueryWrapper<Teacher> wrapper1=new QueryWrapper<>();

        wrapper1.orderByDesc("id");
        wrapper1.last("limit 4");

        List<Teacher> list1 = teacherService.list(wrapper1);
        return R.ok().data("eduList",list).data("teacherList",list1);
    }
}
