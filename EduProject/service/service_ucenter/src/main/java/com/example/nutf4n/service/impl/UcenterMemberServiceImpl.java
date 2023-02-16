package com.example.nutf4n.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.JwtUtils;
import com.example.MD5;

import com.example.nutf4n.entity.UcenterMember;
import com.example.nutf4n.entity.vo.RegisterVo;
import com.example.nutf4n.mapper.UcenterMemberMapper;
import com.example.nutf4n.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author NutF4n
 * @since 2023-01-28
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public String login(UcenterMember ucenterMember) {
        String mobile = ucenterMember.getMobile();
        String password = ucenterMember.getPassword();
        if(StringUtils.isEmpty(mobile)|| StringUtils.isEmpty(password)){
            throw new fuliexception(20001,"fail");
        }
        //verfiy phonenumber
        QueryWrapper<UcenterMember> wrapper= new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        UcenterMember ucenterMember1 = baseMapper.selectOne(wrapper);
        if(ucenterMember1==null){
            throw new fuliexception(20001,"login fail");
        }

        //judge password
        //encrypt password
        if(!MD5.encrypt(password).equals(ucenterMember1.getPassword())){
            throw new fuliexception(20001,"login fail");
        }

        if(ucenterMember1.getIsDisabled()){
            throw new fuliexception(20001,"login fail");
        }

        //get token after logining
        String jwtToken = JwtUtils.getJwtToken(ucenterMember1.getId(), ucenterMember1.getNickname());
        return jwtToken;
    }

    @Override
    public void register(RegisterVo registerVo) {
        //get data from register
        String code = registerVo.getCode();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();
        String mobile = registerVo.getMobile();
        //judge null or not
        if(StringUtils.isEmpty(mobile)|| StringUtils.isEmpty(password)
                || StringUtils.isEmpty(code) || StringUtils.isEmpty(nickname)
        ){
            throw new fuliexception(20001,"fail");
        }


        String rediscode = redisTemplate.opsForValue().get(mobile);
        if(!code.equals(rediscode)){
            throw new fuliexception(20001,"fail");
        }

        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);

        Integer integer = baseMapper.selectCount(wrapper);
        if(integer>0){
            throw new fuliexception(20001,"fail");
        }

        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setMobile(mobile);
        ucenterMember.setPassword(MD5.encrypt(password));
        ucenterMember.setNickname(nickname);
        ucenterMember.setIsDisabled(false);
        ucenterMember.setAvatar("http://nutf4n.oss-cn-guangzhou.aliyuncs.com/2023/01/26/393c0b233f734f068baa31f327b9051c1.jpg");
        baseMapper.insert(ucenterMember);




    }

    @Override
    public UcenterMember getOpenIdMember(Object openid) {
        QueryWrapper<UcenterMember>  wrapper =new QueryWrapper<>();

        wrapper.eq("openid", openid);
        UcenterMember member = baseMapper.selectOne(wrapper);

        return member;
    }

    @Override
    public Integer countRegister(String day) {





        return baseMapper.countRegister( day);
    }


}
