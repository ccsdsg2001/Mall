package com.example.demo.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.edu.entity.Chapter;
import com.example.demo.edu.entity.Video;
import com.example.demo.edu.entity.chapter.ChapterVo;
import com.example.demo.edu.entity.chapter.VideoVo;
import com.example.demo.edu.mapper.ChapterMapper;
import com.example.demo.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.edu.service.VideoService;
import com.example.exception.fuliexception;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author NutF4n
 * @since 2023-01-22
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoService videoService;


    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {

        //根据课程id查询所有章节
        QueryWrapper<Chapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id", courseId);
        List<Chapter> chapterlist = baseMapper.selectList(wrapperChapter);


        //根据id查询所有小节
        QueryWrapper<Video> wrapperVideo = new QueryWrapper<>();
        wrapperChapter.eq("course_id", courseId);
        List<Video> videoList = videoService.list(wrapperVideo);

        //创建list集合 用于最终封装数据
        List<ChapterVo> finallist =new ArrayList<>();
        //遍历所有list集合进行封装
        for (int i = 0; i < chapterlist.size(); i++) {
            Chapter chapter = chapterlist.get(i);
            //chapter对象复制到chptervo中
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            finallist.add(chapterVo);

            //遍历小节集合进行封装
            List<VideoVo> videoVoList =new ArrayList<>();
            for (int m = 0; m < videoList.size(); m++) {
                Video video = videoList.get(m);
                //判断小节id和章节id是否一样
                if(video.getChapterId().equals(chapter.getId())){
                    //封装
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video,videoVo);
                    videoVoList.add(videoVo);
                }

            }

            chapterVo.setChildren(videoVoList);


        }


        return finallist;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        //根据chapterid查询小节表，如查询数据则不删除
        QueryWrapper<Video> wrapper =new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        int count = videoService.count(wrapper);
        if(count>0) { //查询出小节 不删除
            throw new fuliexception(20001, "不删除");
        }else {
            //删除
            int i = baseMapper.deleteById(chapterId);
            return i>0;
        }
    }
}
