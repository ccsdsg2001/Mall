package com.example.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author cc
 * @date 2023年01月25日 23:34
 */
public interface VodService {
    String uploadVideoAly(MultipartFile file);
}
