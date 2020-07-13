package org.csu.nekotalk.api;


import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.QiniuException;
import org.csu.nekotalk.annotation.label.UserLoginToken;
import org.csu.nekotalk.domain.Following;
import org.csu.nekotalk.domain.ResponseTemplate;
import org.csu.nekotalk.domain.Users;
import org.csu.nekotalk.service.AccountService;
import org.csu.nekotalk.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//import org.csu.nekotalk.annotation.label.UserLoginToken;


@RestController
@RequestMapping("/users")
public class AccountAPI {

    @Autowired
    private AccountService accountService;

    @PostMapping("/user")
    public ResponseTemplate userRegister (@RequestBody JSONObject req)
    {
        Users user = new Users();
        user.setPhoneNumber(req.getString("phoneNumber"));
        user.setUsername(req.getString("username"));
        user.setPassword(req.getString("password"));
        user.setAvatar(req.getString("avatar"));
        user.setSex(req.getString("sex"));
        user.setSign(req.getString("sign"));
        user.setLastAddress(req.getString("lastAddress"));
        accountService.insertUser(user);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }


    @PostMapping("/token")
    @ResponseBody
    public ResponseTemplate login(@RequestBody  JSONObject req, HttpServletRequest httpServletRequest)
    {
        String phoneNumber = req.getString("phoneNumber");
        String password = req.getString("password");
        String verifyCode = req.getString("verifyCode");
        JSONObject data=new JSONObject();
        HttpSession session=  httpServletRequest.getSession();
        String code =(String) session.getAttribute("code");
        //String code="188234";
        session.setAttribute("code",null);
        if(verifyCode.equals(code))
        {
            Users user = accountService.getUserByPhoneNumberAndPassword(phoneNumber,password);
            if(user != null)
            {
                String token = TokenService.getToken(phoneNumber, password);

                data.put("user", user);
                data.put("token", token);
                return ResponseTemplate.builder()
                        .status(206)
                        .statusText("用户登录成功")
                        .data(data)
                        .build();
            }
            else
            {
                return ResponseTemplate.builder()
                        .status(423)
                        .statusText("用户名或密码错误")
                        .build();
            }
        }
        else {

            return ResponseTemplate.builder()
                    .status(424)
                    .statusText("验证码错误")
                    .build();
        }


    }


    @PostMapping("/verifyCode")
    public ResponseTemplate sendVerificationCode(@RequestBody JSONObject req, HttpServletRequest httpServletRequest)
    {

        String phoneNumber = req.getString("phoneNumber");

        //此处仅为模拟短信发送
        String code = "188234";
        System.out.println("成功发送短信给"+phoneNumber+"，验证码为"+code);
        //此处为真实的短信发送
        //String code= SmsService.sendSms(phoneNumber);

        httpServletRequest.getSession().setAttribute("code",code);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();

    }


    @PostMapping("/following")
    public ResponseTemplate following (@RequestBody JSONObject req)
    {
        Following following=new Following();
        following.setUserPhoneNumber(req.getString("userPhoneNumber"));
        following.setFollowingPhoneNumber(req.getString("followingPhoneNumber"));
        accountService.insertFollowing(following);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }



    @DeleteMapping("/user/{phoneNumber}")
    public ResponseTemplate deleteUser(@PathVariable String phoneNumber)
    {
        System.out.println(phoneNumber);

        accountService.deleteUser(phoneNumber);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }


    @DeleteMapping("/following")
    public ResponseTemplate deleteFollowing(@RequestBody JSONObject req)
    {
        Following following=new Following();
        following.setUserPhoneNumber(req.getString("userPhoneNumber"));
        following.setFollowingPhoneNumber(req.getString("followingPhoneNumber"));
        accountService.deleteFollowing(following);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }



    //用户退出，清除用户缓存，返回登录页面
    @UserLoginToken
    @DeleteMapping("/token")
    public ResponseTemplate logOut(SessionStatus sessionStatus)
    {
        sessionStatus.setComplete();
        return ResponseTemplate.builder()
                .status(204)
                .statusText("删除成功")
                .build();

    }



    @PutMapping("/user")
    @UserLoginToken
    public ResponseTemplate updateUser (@RequestBody JSONObject req)throws QiniuException
    {

        Users user = new Users();
        user.setPhoneNumber(req.getString("phoneNumber"));
        user.setUsername(req.getString("username"));
        user.setPassword(req.getString("password"));
        user.setAvatar(req.getString("avatar"));
        user.setSex(req.getString("sex"));
        user.setSign(req.getString("sign"));
        user.setLastAddress(req.getString("lastAddress"));


        accountService.updateUser(user);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }


    @GetMapping("/user/{phoneNumber}")
    public ResponseTemplate getUser(@PathVariable String phoneNumber)
    {
        JSONObject data = new JSONObject();

        data.put("user", accountService.getUserByPhoneNumber(phoneNumber));

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .data(data)
                .build();
    }



    @GetMapping("/followingList/{phoneNumber}")
    public ResponseTemplate getFollowingList(@PathVariable String phoneNumber)
    {
        JSONObject data = new JSONObject();
        System.out.print(phoneNumber);

        data.put("followingList", accountService.getFollowingList(phoneNumber));

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .data(data)
                .build();
    }

    @GetMapping("/followedList/{phoneNumber}")
    public ResponseTemplate getFollowedList(@PathVariable String phoneNumber)
    {
        JSONObject data = new JSONObject();

        data.put("followedList", accountService.getFollowedList(phoneNumber));

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .data(data)
                .build();
    }





}

