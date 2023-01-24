package com.example.demo.edu.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.edu.entity.EduCourse;
import com.example.demo.edu.entity.vo.CourseInfoVo;
import com.example.demo.edu.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author NutF4n
 * @since 2023-01-22
 */
public interface EduCourseService extends IService<EduCourse> {

    //添加课程基本信息的方法
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String courseId);
}
