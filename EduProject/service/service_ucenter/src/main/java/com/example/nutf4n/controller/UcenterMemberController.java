package com.example.nutf4n.controller;


import com.example.JwtUtils;
import com.example.R;
import com.example.nutf4n.entity.UcenterMember;
import com.example.nutf4n.entity.vo.RegisterVo;
import com.example.nutf4n.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author NutF4n
 * @since 2023-01-28
 */
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    //login
    @PostMapping("login")
    public R loginUser(@RequestBody UcenterMember ucenterMember){
        //call service
      String token =  memberService.login(ucenterMember);

      return R.ok().data("token", token);


    }


    //register
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }
    //getinfo by token
    @GetMapping("getMemberInfo")
    public R Info(HttpServletRequest request){
        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        //select database by id
        UcenterMember member = memberService.getById(memberIdByJwtToken);
    return R.ok().data("userInfo",member);

    }


}

