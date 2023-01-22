package com.example.demo.oss.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.example.demo.oss.service.OssService;
import com.example.demo.oss.utils.ConstantUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author cc
 * @date 2023年01月21日 14:53
 */
@Service
public class OssServiceImpl implements OssService {


    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String endPoint = ConstantUtils.END_POINT;
        String accessKeyId = ConstantUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantUtils.BUCKET_NAME;
        String uploadUrl = null;
        try {
            //判断oss实例是否存在：如果不存在则创建，如果存在则获取
            OSSClient ossClient = new OSSClient(endPoint, accessKeyId,
                    accessKeySecret);
            if (!ossClient.doesBucketExist(bucketName)) {
                //创建bucket
                ossClient.createBucket(bucketName);
                //设置oss实例的访问权限：公共读
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }

            //获取上传文件流
            InputStream inputStream = file.getInputStream();

            //构建日期路径：avatar/2019/02/26/文件名
            String filePath = new DateTime().toString("yyyy/MM/dd");

            //文件名：uuid.扩展名
            String original = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString();
            String fileType = original.substring(original.lastIndexOf("."));
            String newName = fileName + fileType;

            String datetime = new DateTime().toString("yyyy/MM/dd");


            String originalFilename =datetime+"/"+ UUID.randomUUID().toString().replaceAll("-", "")+file.getOriginalFilename();

            //文件上传至阿里云
            ossClient.putObject(bucketName, originalFilename, inputStream);

            //添加日期分类

            // 关闭OSSClient。
            ossClient.shutdown();

            //获取url地址
          uploadUrl = "http://" + bucketName + "." + endPoint + "/" + originalFilename;

        } catch (IOException e) {
            e.printStackTrace();

        }
        return  uploadUrl;
    }
}





