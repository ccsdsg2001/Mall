package com.example.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.example.R;
import com.example.exception.fuliexception;
import com.example.vod.service.VodService;
import com.example.vod.utils.ConstantUtils;
import com.example.vod.utils.InitVodClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author cc
 * @date 2023年01月25日 23:33
 */
@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    //上传视频到阿里云
    @PostMapping("uploadAlyiVideo")
    public R upload(MultipartFile file){
        String videoId=vodService.uploadVideoAly(file);



        return R.ok().data("videoId", videoId);
    }


    //delete video by id
    @DeleteMapping("removeVideo/{id}")
    public R remove(@PathVariable String id){
        try{
            DefaultAcsClient defaultAcsClient = InitVodClient.initVodClient(ConstantUtils.ACCESS_KEY_ID, ConstantUtils.ACCESS_KEY_SECRET);
            DeleteVideoRequest deleteVideoRequest = new DeleteVideoRequest();
            deleteVideoRequest.setVideoIds(id);
            defaultAcsClient.getAcsResponse(deleteVideoRequest);
            return  R.ok();
        }catch (Exception e){
            throw new fuliexception(20001, "视频删除失败");
        }
    }

    //delete batch video
    @DeleteMapping("delete-batch")
    public R delte(@RequestParam("videoIdList") List<String> videoIdList){
        vodService.removeMoreVideo(videoIdList);
        return  R.ok();
    }
}
