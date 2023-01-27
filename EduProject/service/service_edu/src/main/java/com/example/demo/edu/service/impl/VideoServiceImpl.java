package com.example.demo.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.edu.Client.VodClient;
import com.example.demo.edu.entity.Video;
import com.example.demo.edu.mapper.VideoMapper;
import com.example.demo.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.xml.bind.v2.TODO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author NutF4n
 * @since 2023-01-22
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    private VodClient vodClient;

//todo:delete title and delete video
    @Override
    public void removeVideoBycourseID(String courseId) {
        QueryWrapper<Video> videowrapper =new QueryWrapper<>();
        videowrapper.eq("course_id", courseId);
        videowrapper.select("video_source_id");
        List<Video> videos = baseMapper.selectList(videowrapper);


        List<String > vid = new ArrayList<>();
        for (int i = 0; i <videos.size() ; i++) {
            Video video = videos.get(i);
            String videoSourceId = video.getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId)){
                vid.add(videoSourceId);
            }

        }
    if(vid.size()>0){
        vodClient.delte(vid);
    }


        QueryWrapper<Video> wrapper =new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        baseMapper.delete(wrapper);
    }
}
