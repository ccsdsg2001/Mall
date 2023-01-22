package com.example.demo.edu.service.impl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.edu.entity.CourseDescription;
import com.example.demo.edu.entity.EduCourse;
import com.example.demo.edu.entity.vo.CourseInfoVo;

import com.example.demo.edu.mapper.CourseMapper;
import com.example.demo.edu.service.CourseDescriptionService;


import com.example.demo.edu.service.EduCourseService;
import com.example.exception.fuliexception;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author NutF4n
 * @since 2023-01-22
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<CourseMapper, EduCourse> implements EduCourseService {

    //课程描述注入
    @Autowired
    private CourseDescriptionService courseDescriptionService;

    //添加课程基本信息的方法
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1 向课程表添加课程基本信息
        //CourseInfoVo对象转换eduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if(insert == 0) {
            //添加失败
            throw new fuliexception(20001,"添加课程信息失败");
        }

        //获取添加之后课程id
        String cid = eduCourse.getId();

        //2 向课程简介表添加课程简介
        //edu_course_description
       CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        //设置描述id就是课程id
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);

        return cid;
    }
}
