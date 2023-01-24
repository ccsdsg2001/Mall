package com.example.demo.edu.service;

import com.example.demo.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.edu.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author NutF4n
 * @since 2023-01-22
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);
}
