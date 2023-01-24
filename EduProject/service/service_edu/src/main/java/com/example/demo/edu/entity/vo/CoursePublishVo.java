package com.example.demo.edu.entity.vo;

import lombok.Data;

/**
 * @author cc
 * @date 2023年01月25日 5:10
 */
@Data
public class CoursePublishVo {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
