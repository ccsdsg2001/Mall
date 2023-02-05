package com.example.demo.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author NutF4n
 * @since 2023-01-15
 */
public interface TeacherService extends IService<Teacher> {

    Map<String, Object> getTeacherFrontByPAGE(Page<Teacher> pageTeacher);
}
