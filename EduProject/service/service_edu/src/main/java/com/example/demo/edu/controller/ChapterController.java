package com.example.demo.edu.controller;


import com.example.R;
import com.example.demo.edu.entity.Chapter;
import com.example.demo.edu.entity.chapter.ChapterVo;
import com.example.demo.edu.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author NutF4n
 * @since 2023-01-22
 */
@RestController
@RequestMapping("/eduservice/chapter")
//@CrossOrigin
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    //课程大纲列表 根据课程id进行查询
    @GetMapping("getChapterVideo/{courseId}")
    public R get(@PathVariable String courseId){
        List<ChapterVo> list = chapterService.getChapterVideoByCourseId(courseId);

        return  R.ok().data("allChapterVideo", list);
    }
    //添加章节
    @PostMapping("addChapter")
    public R addChapter(@RequestBody Chapter chapter){
        chapterService.save(chapter);
        return R.ok();
    }

    //根据id查询
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId){
        Chapter byId = chapterService.getById(chapterId);

        return R.ok().data("chapter", byId);
    }

    //修改章节
    @PostMapping("updateChapter")
    public R UpdateChapter(@RequestBody Chapter chapter){
        chapterService.updateById(chapter);
        return R.ok();
    }

    //删除章节
    @DeleteMapping("{chapterId}")
    public R delete(@PathVariable String chapterId){
        boolean b = chapterService.deleteChapter(chapterId);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }

    }
}

