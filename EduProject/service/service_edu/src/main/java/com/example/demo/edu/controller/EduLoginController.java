package com.example.demo.edu.controller;

import com.example.R;
import org.springframework.web.bind.annotation.*;

/**
 * @author cc
 * @date 2023年01月20日 14:12
 */
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin //解决跨域问题
public class EduLoginController {

    //login
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");

    }


    @GetMapping("info")
    public R info(){
        return R.ok().data("roles", "[admin]").data("name","admin").data("avatar", "https://img.alicdn.com/imgextra/i4/2201508629245/O1CN01RB8qbg2IAGQUkaUyi_!!2201508629245.jpg");
    }
}
