package com.example.demo.edu.controller;




import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.R;
import com.example.demo.edu.entity.Teacher;
import com.example.demo.edu.entity.vo.TeacherQuery;
import com.example.demo.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author NutF4n
 * @since 2023-01-15
 */
@RestController
@RequestMapping("/eduservice/teacher")
//@CrossOrigin
public class TeacherController {

    @Autowired
    public TeacherService teacherService;

    //1.查询讲师数据
    @GetMapping("findAll")
    public R sle(){
        List<Teacher> list = teacherService.list(null);

        return R.ok().data("items",list);

    }

    //删除
    @DeleteMapping("{id}")
    public R removeTeacher(@PathVariable String id){
        boolean b = teacherService.removeById(id);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }


    //3 分页查询讲师的方法
    //current 当前页
    //limit 每页记录数
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit) {
        //创建page对象
        Page<Teacher> pageTeacher = new Page<>(current,limit);

        try {
            int i = 10/0;
        }catch(Exception e) {
            //执行自定义异常
//            throw Exception(e.toString());
        }


        //调用方法实现分页
        //调用方法时候，底层封装，把分页所有数据封装到pageTeacher对象里面
        teacherService.page(pageTeacher,null);

        long total = pageTeacher.getTotal();//总记录数
        List<Teacher> records = pageTeacher.getRecords(); //数据list集合

//        Map map = new HashMap();
//        map.put("total",total);
//        map.put("rows",records);
//        return R.ok().data(map);

        return R.ok().data("total",total).data("rows",records);
    }

    //4 条件查询带分页的方法
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,@PathVariable long limit,
                                  @RequestBody(required = false)  TeacherQuery teacherQuery) {
        //创建page对象
        Page<Teacher> pageTeacher = new Page<>(current,limit);

        //构建条件
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)) {
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);
        }

        //排序
        wrapper.orderByDesc("gmt_create");

        //调用方法实现条件查询分页
        teacherService.page(pageTeacher,wrapper);

        long total = pageTeacher.getTotal();//总记录数
        List<Teacher> records = pageTeacher.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

//添加讲师
    @PostMapping("addTeacher")
    public R add(@RequestBody Teacher teacher){

        boolean save = teacherService.save(teacher);
        if(save){
            return R.ok();
        }else {
            return  R.error();
        }
    }
    //修改讲师 根据id
    @GetMapping("/getTeacher/{id}")
    public R GETandedit(@PathVariable String id){
        Teacher teacher = teacherService.getById(id);
        return R.ok().data("teacher",teacher);
    }
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody Teacher teacher){
        boolean b = teacherService.updateById(teacher);
        if(b){
            return R.ok();
        }else {
            return  R.error();
        }
    }


}


