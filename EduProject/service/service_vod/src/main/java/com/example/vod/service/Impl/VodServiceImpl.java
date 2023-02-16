package com.example.vod.service.Impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadFileStreamRequest;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadFileStreamResponse;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.example.R;

import com.example.vod.service.VodService;
import com.example.vod.utils.ConstantUtils;
import com.example.vod.utils.InitVodClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author cc
 * @date 2023年01月25日 23:34
 */
@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadVideoAly(MultipartFile file) {
        try {
            //accessKeyId, accessKeySecret
            //fileName：上传文件原始名称
            // 01.03.09.mp4
            String fileName = file.getOriginalFilename();
            //title：上传之后显示名称
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            //inputStream：上传文件输入流
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(ConstantUtils.ACCESS_KEY_ID,ConstantUtils.ACCESS_KEY_SECRET, title, fileName, inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
            }
            return videoId;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void removeMoreVideo(List videoIdList) {
        try{
            DefaultAcsClient defaultAcsClient = InitVodClient.initVodClient(ConstantUtils.ACCESS_KEY_ID, ConstantUtils.ACCESS_KEY_SECRET);
            DeleteVideoRequest deleteVideoRequest = new DeleteVideoRequest();
            String videIDs = StringUtils.join(videoIdList.toArray(), ",");


            deleteVideoRequest.setVideoIds(videIDs);
            defaultAcsClient.getAcsResponse(deleteVideoRequest);

        }catch (Exception e){
            throw new fuliexception(20001, "视频删除失败");
        }
    }
}
