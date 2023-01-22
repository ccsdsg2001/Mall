package com.example.demo.edu.service;

import com.example.demo.edu.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.edu.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author NutF4n
 * @since 2023-01-21
 */
public interface EduSubjectService extends IService<EduSubject> {
    //添加课程分类
    void saveSubject(MultipartFile file,EduSubjectService edusubjectSevice);

    List<OneSubject> getAllOneTwoSubject();
}
