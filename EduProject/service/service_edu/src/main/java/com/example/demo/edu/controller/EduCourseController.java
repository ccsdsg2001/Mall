package com.example.demo.edu.controller;


import com.example.R;
import com.example.demo.edu.entity.vo.CourseInfoVo;
import com.example.demo.edu.entity.vo.CoursePublishVo;
import com.example.demo.edu.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author NutF4n
 * @since 2023-01-22
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    //添加课程基本信息的方法
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        //返回添加之后课程id，为了后面添加大纲使用
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId",id);
    }



    //根据课程查询基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId){
       CourseInfoVo courseInfoVo= courseService.getCourseInfo(courseId);

        return  R.ok().data("courseInfoVo",courseInfoVo);
    }

    //修改课程信息
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    //select info by courseId
    @GetMapping("getPublishCourseInfo/{id}")
    public R get(@PathVariable String id){
       CoursePublishVo coursePublishVo= courseService.publishCourseInfo(id);
        return R.ok().data("publishCourse",coursePublishVo);
    }

}
