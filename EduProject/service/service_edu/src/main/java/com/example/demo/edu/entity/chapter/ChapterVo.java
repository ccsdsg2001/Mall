package com.example.demo.edu.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cc
 * @date 2023年01月23日 23:46
 */
@Data
public class ChapterVo {

    private String id;
    private String title;

    private List<VideoVo> children =new ArrayList<>();
}
