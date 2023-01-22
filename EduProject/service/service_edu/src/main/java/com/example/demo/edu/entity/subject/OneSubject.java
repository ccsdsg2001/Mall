package com.example.demo.edu.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cc
 * @date 2023年01月22日 17:15
 */
//一级分类
@Data
public class OneSubject {
    private String id;
    private String title;

    //一级分类包含多个二级分类
    private List<TwoSubject> children = new ArrayList<>();



}
