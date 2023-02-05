package com.example.demo.edu.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author NutF4n
 * @since 2023-01-31
 */
@RestController
@RequestMapping("/edu/comment")
public class CommentController {
//TODO:1.分页查询课程评论
//    2.添加评论 (1) 课程评论内容:输入内容,提交到接口 (2) 课程id 和讲师id 进入课程详情页面根据id查询出来 (3)用户id 和用户昵称和头像
// 做评论之前必须先登录 (1)从header中获取token字符串(从request获取)(2) 根据token字符串获取用户id(使用jwt获取)(3) 根据用户id查询用户表,把需要数据获取出来
// 调用ucenter模块判断登录

}

