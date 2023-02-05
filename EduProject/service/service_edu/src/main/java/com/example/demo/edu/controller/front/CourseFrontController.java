package com.example.demo.edu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.JwtUtils;
import com.example.R;
import com.example.demo.edu.Client.OrderClient;
import com.example.demo.edu.entity.EduCourse;
import com.example.demo.edu.entity.chapter.ChapterVo;
import com.example.demo.edu.entity.frontvo.CourseQueryVo;
import com.example.demo.edu.entity.frontvo.CourseWebVo;
import com.example.demo.edu.service.ChapterService;
import com.example.demo.edu.service.EduCourseService;
import com.example.ordervo.CourseWebVoOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author cc
 * @date 2023年01月30日 16:35
 */
@RestController
@RequestMapping("/eduservice/coursefront")
@CrossOrigin
public class CourseFrontController {
    
    @Autowired
    private EduCourseService courseService;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private OrderClient orderClient;

    //select by page
    @PostMapping("/getFrontCourseList/{page}/{limit}")
    public R geytpage(@PathVariable long page, @PathVariable long limit, @RequestBody(required = false) CourseQueryVo courseQueryVo){
        Page<EduCourse> pageCourse = new Page<>(page,limit);

        Map<String,Object> map =courseService.getCourseFrontList(pageCourse,courseQueryVo);
        return R.ok().data(map);
    }

    //course info
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R courseINo(@PathVariable String courseId, HttpServletRequest request){
        CourseWebVo courseWebVo=courseService.getBaseCourseInfo(courseId);


        //title by courseid
        List<ChapterVo> chapterVideoByCourseId = chapterService.getChapterVideoByCourseId(courseId);
        //get order status
        boolean isbuy = orderClient.isbuy(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoByCourseId).data("isBuy",isbuy);

    }


    //courseInfo by courseId
    @PostMapping("getCourseInfoOrder/{id}")
    public CourseWebVoOrder getinfo(@PathVariable String id){
        CourseWebVo baseCourseInfo = courseService.getBaseCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(baseCourseInfo, courseWebVoOrder);
        return courseWebVoOrder;
    }
    


}
