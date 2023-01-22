package com.example.demo.oss.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author cc
 * @date 2023年01月21日 14:52
 */

public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
