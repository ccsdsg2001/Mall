package com.example.demo.edu.controller;


import com.example.R;
import com.example.demo.edu.Client.VodClient;
import com.example.demo.edu.entity.Video;
import com.example.demo.edu.service.VideoService;
import com.example.exception.fuliexception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author NutF4n
 * @since 2023-01-22
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class VideoController {

    @Autowired
    private VodClient vodClient;

    @Autowired
    private VideoService videoService;

    //add
    @PostMapping("addVideo")
    public R add(@RequestBody Video video){
        videoService.save(video);
        return R.ok();
    }

    //deletebyid
    // TODO:  deleting the video when  seconde title was deleted
    @DeleteMapping("{id}")
    public R delevideo(@PathVariable String id){

        //get videoId by secondid and call method
        Video video = videoService.getById(id);
        String videoSourceId = video.getVideoSourceId();
        if(!StringUtils.isEmpty(videoSourceId)){
            R r = vodClient.removeVideo(videoSourceId);
            if(r.getCode() == 20001){
                throw new fuliexception(20001,"删除失败,熔断器..");

            }

        }

        videoService.removeById(id);
        return R.ok();
    }

    //update
    @PostMapping("update")
    public R update(@RequestBody Video video){
        videoService.updateById(video);
        return R.ok();
    }
}

