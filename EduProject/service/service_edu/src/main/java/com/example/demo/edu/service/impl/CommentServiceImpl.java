package com.example.demo.edu.service.impl;

import com.example.demo.edu.entity.Comment;
import com.example.demo.edu.mapper.CommentMapper;
import com.example.demo.edu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author NutF4n
 * @since 2023-01-31
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
