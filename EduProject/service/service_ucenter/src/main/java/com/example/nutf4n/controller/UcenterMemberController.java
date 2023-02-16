package com.example.nutf4n.controller;


import com.example.JwtUtils;
import com.example.R;
import com.example.nutf4n.entity.UcenterMember;
import com.example.nutf4n.entity.vo.RegisterVo;
import com.example.nutf4n.service.UcenterMemberService;
import com.example.ordervo.UcenterMemberOrder;
import org.springframework.beans.BeanUtils;
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
//@CrossOrigin
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


    @PostMapping("getUserInfoOrder/{id}")
    public UcenterMemberOrder getuseinfo(@PathVariable String id){
        UcenterMember member = memberService.getById(id);
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(member,ucenterMemberOrder);

        return ucenterMemberOrder;

    }



    //show one day register
    @GetMapping("conuntRegister/{day}")
    public R countRegister(@PathVariable String day){
        Integer count=memberService.countRegister(day);
        return R.ok().data("countRegister",count);
    }


    @PostMapping("getInfoUc/{id}")
    public UcenterMemberOrder getinfo(@PathVariable String id){
        //get user info by id
        UcenterMember byId = memberService.getById(id);
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(byId, ucenterMemberOrder);
        return ucenterMemberOrder;
    }


}

