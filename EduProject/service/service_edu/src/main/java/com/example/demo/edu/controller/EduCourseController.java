package com.example.demo.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.R;
import com.example.demo.edu.entity.EduCourse;
import com.example.demo.edu.entity.Teacher;
import com.example.demo.edu.entity.vo.CourseInfoVo;
import com.example.demo.edu.entity.vo.CoursePublishVo;
import com.example.demo.edu.entity.vo.CourseQuery;
import com.example.demo.edu.entity.vo.TeacherQuery;
import com.example.demo.edu.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
//@CrossOrigin
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

    //update
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");//set status
        courseService.updateById(eduCourse);
        return R.ok();
    }

    //Course List todo:bypage
    @GetMapping
    public R getCourseList(){
        List<EduCourse> list = courseService.list(null);
        return R.ok().data("list",list);
    }


    //delete course
    @DeleteMapping("delete/{courseId}")
    public R delete(@PathVariable String courseId){
        courseService.removeCourse(courseId);
        return R.ok();
    }

    //CourseByPage
    @PostMapping("CourseByPage/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,@PathVariable long limit,
                                  @RequestBody(required = false) CourseQuery CourseQuery) {
        //创建page对象
        Page<EduCourse> pageTeacher = new Page<>(current,limit);

        //构建条件
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String title = CourseQuery.getTitle();

        String status = CourseQuery.getStatus();
        String begin = CourseQuery.getBegin();
        String end = CourseQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(title)) {
            //构建条件
            wrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(status)) {
            wrapper.eq("status",status);
        } //?


        if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);
        }

        //排序
        wrapper.orderByDesc("gmt_create");

        //调用方法实现条件查询分页
        courseService.page(pageTeacher,wrapper);

        long total = pageTeacher.getTotal();//总记录数
        List<EduCourse> records = pageTeacher.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }
}
