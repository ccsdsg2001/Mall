package com.example.demo.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.R;
import com.example.demo.edu.entity.EduCourse;
import com.example.demo.edu.entity.Teacher;
import com.example.demo.edu.service.EduCourseService;
import com.example.demo.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author cc
 * @date 2023年01月30日 15:11
 */
@RestController
@RequestMapping("/eduservice/teacherfront")
//@CrossOrigin
public class TeacherForntController {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private EduCourseService courseService;

    //select teacher by page
    @PostMapping("getTeacherFrontList/{page}/{limit}")
    public R get(@PathVariable long page,@PathVariable long limit){
        Page<Teacher> pageTeacher = new Page<>(page,limit);
        Map<String,Object> map =teacherService.getTeacherFrontByPAGE(pageTeacher);
        return R.ok().data(map);
    }

    //teacher detail
    @GetMapping("getTeacherFrontInfo/{teacherId}")
    public R getbyid(@PathVariable long teacherId){
        Teacher teacher = teacherService.getById(teacherId);
        QueryWrapper<EduCourse> wrapper= new QueryWrapper<>();
        wrapper.eq("teacher_id", teacherId);
        List<EduCourse> list = courseService.list(wrapper);

        return  R.ok().data("teacher",teacher).data("courseList",list);
    }


}
