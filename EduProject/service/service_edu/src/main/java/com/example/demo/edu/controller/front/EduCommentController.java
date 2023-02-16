package com.example.demo.edu.controller.front;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.JwtUtils;
import com.example.R;
import com.example.demo.edu.Client.UcenterClient;
import com.example.demo.edu.entity.Comment;
import com.example.demo.edu.service.CommentService;
import com.example.ordervo.UcenterMemberOrder;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author NutF4n
 * @since 2023-01-31
 */
@RestController
@RequestMapping("/eduservice/comment")
@CrossOrigin
public class EduCommentController {
    @Autowired
    private UcenterClient ucenterClient;

    @Autowired
    private CommentService commentService;
    @ApiOperation(value = "添加评论")
    @PostMapping("auth/save")
    public R addComment(@RequestBody Comment eduComment, HttpServletRequest request){
        //根据token获取用户信息
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if(StringUtils.isEmpty(memberId)){
            return R.error().message("请登录");
        }
        eduComment.setMemberId(memberId);
        UcenterMemberOrder member = ucenterClient.getinfo(memberId);
        eduComment.setNickname(member.getNickname());
        eduComment.setAvatar(member.getAvatar());
//        BeanUtils.copyProperties(member,eduComment);
        commentService.save(eduComment);
        return R.ok();
    }

    //根据课程id查询评论列表
    @ApiOperation(value = "评论分页列表")
    @GetMapping("{page}/{limit}")
    public R index(@PathVariable Long page,
                   @PathVariable Long limit,
                   String courseId) {
        Page<Comment> pageParam = new Page<>(page, limit);

        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);

        commentService.page(pageParam,wrapper);
        List<Comment> commentList = pageParam.getRecords();

        Map<String, Object> map = new HashMap<>();
        map.put("items", commentList);
        map.put("current", pageParam.getCurrent());
        map.put("pages", pageParam.getPages());
        map.put("size", pageParam.getSize());
        map.put("total", pageParam.getTotal());
        map.put("hasNext", pageParam.hasNext());
        map.put("hasPrevious", pageParam.hasPrevious());
        return R.ok().data(map);
    }
}



