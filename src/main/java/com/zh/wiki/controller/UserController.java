package com.zh.wiki.controller;

//?
import com.alibaba.fastjson.JSONObject;
import com.zh.wiki.req.UserLoginReq;
import com.zh.wiki.req.UserQueryReq;
import com.zh.wiki.req.UserResetPasswordReq;
import com.zh.wiki.req.UserSaveReq;
import com.zh.wiki.resp.CommonResp;
import com.zh.wiki.resp.PageResp;
import com.zh.wiki.resp.UserLoginResp;
import com.zh.wiki.resp.UserQueryResp;
import com.zh.wiki.service.UserService;
import com.zh.wiki.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//?
import org.springframework.data.redis.core.RedisTemplate;
//?
import org.springframework.util.DigestUtils;
//?
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @Resource
    private SnowFlake snowFlake;

    @Resource
    private RedisTemplate redisTemplate;

    @GetMapping("/list")
    public CommonResp list(@Valid UserQueryReq req) {
        CommonResp<PageResp<UserQueryResp>> resp = new CommonResp<>();
        PageResp<UserQueryResp> list = userService.list(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody UserSaveReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp<>();
        userService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        CommonResp resp = new CommonResp<>();
        userService.delete(id);
        return resp;
    }

    @PostMapping("/reset-password")
    public CommonResp resetPassword(@Valid @RequestBody UserResetPasswordReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp<>();
        userService.resetPassword(req);
        return resp;
    }

    @PostMapping("/login")
    public CommonResp login(@Valid @RequestBody UserLoginReq req) {
        // TODO: 密码加密
        //req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        LOG.info("req.getPassword：{}", req.getPassword());
        LOG.info("req.getPassword().getBytes()：{}", req.getPassword().getBytes());
        LOG.info(" DigestUtils.md5DigestAsHex：{}", DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp<UserLoginResp> resp = new CommonResp<>();
        UserLoginResp userLoginResp = userService.login(req);

        // ? id long类型
        // ?jwt 实现过程
        // 1. 生成tokens
        // 2. token存入redis
        // 3. 拦截器进行通过获取header token;
        // 4. 通过token从redis中获取用户信息;
        Long token = snowFlake.nextId();
        LOG.info("生成单点登录token：{}，并放入redis中", token);
        userLoginResp.setToken(token.toString());
        // !redis, 将用户信息放入redis中，便于登录查询
        redisTemplate.opsForValue().set(token.toString(), JSONObject.toJSONString(userLoginResp), 3600 * 24, TimeUnit.SECONDS);

        resp.setContent(userLoginResp);
        return resp;
    }

    @GetMapping("/logout/{token}")
    public CommonResp logout(@PathVariable String token) {
        CommonResp resp = new CommonResp<>();
        redisTemplate.delete(token);
        LOG.info("从redis中删除token: {}", token);
        return resp;
    }
}
