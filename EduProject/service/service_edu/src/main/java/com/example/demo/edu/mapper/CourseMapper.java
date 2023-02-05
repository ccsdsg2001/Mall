package com.example.demo.edu.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.edu.entity.EduCourse;
import com.example.demo.edu.entity.frontvo.CourseWebVo;
import com.example.demo.edu.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author NutF4n
 * @since 2023-01-22
 */
public interface CourseMapper extends BaseMapper<EduCourse> {
    public CoursePublishVo getPublishCourseInfo(String courseId);

    CourseWebVo getBaseCourseInfo(String courseId);
}
