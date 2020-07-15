package org.csu.nekotalk.api;

import com.alibaba.fastjson.JSONObject;
import org.csu.nekotalk.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@RequestMapping("/account")
public class QQAPI extends BaseAction {

    /**
     *
     */
    private static final long serialVersionUID = 1729415442021645693L;

    @Resource
    private RedisHandle redisHandle;

    @Autowired
    private UserService userService;

    /**
     * 跳转至QQ登录界面
     */
    @RequestMapping("/qqConnect")
    @ResponseBody
    public void qqConnect() {
        try {
            String referer = getRequest().getHeader("REFERER");
            String state = DateUtil.formatUserDefineDate(new Date(), "yyyyMMddHHmmssSSS");
            redisHandle.set(state, referer, 60 * 30L);
            getResponse().sendRedirect(QQUtil.getLoginConnectUrl(state));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * QQ第三方登录
     *
     * @throws Exception
     */
    @RequestMapping("/qqLogin")
    @ResponseBody
    public void qqLogin(@RequestParam String code,@RequestParam String state) throws Exception {
//        String code = getRequest().getParameter("code");
//        String state = getRequest().getParameter("state");
        System.out.println("code = " + code + ", state = " + state);
        if (code != null && !"".equals(code)) {
            QQAccessToken qqAccessToken = QQUtil.getQQLoginAccessToken(code);
            if (qqAccessToken.getAccessToken().equals("")) {
                // 我们的网站被CSRF攻击了或者用户取消了授权
                // 做一些数据统计工作
                System.out.print("没有获取到响应参数");
                // 跳转返回地址
                outJsonFailure("未获取到AccessToken，请重新进行QQ授权登录");
            } else {
                QQAccessToken qqAccessToken2 = QQUtil.refreshQQAccessToken(qqAccessToken.getRefreshToken());
                String accessToken = qqAccessToken2.getAccessToken();
                String referer = redisHandle.get(state).toString();
                redisHandle.set(accessToken, referer, 60 * 30L);
                redisHandle.remove(state);
                getResponse().sendRedirect("https://www.zwqh.top/account/getQQUserInfo?qqAccessToken=" + accessToken);

            }
        } else {
            outJsonFailure("缺少code参数");
        }
    }

    /**
     * 获取QQ用户信息
     *
     * @param qqAccessToken
     * @throws Exception
     */
    @GetMapping("/getQQUserInfo")
    public String getQQUserInfo(String qqAccessToken) throws Exception {
        System.out.println("accessToken = " + qqAccessToken);
        String referer = redisHandle.get(qqAccessToken).toString();
        if (qqAccessToken != null && !"".equals(qqAccessToken)) {
            try {
                String qqOpenId = QQUtil.getQQOpenId(qqAccessToken);
                if (qqOpenId != null) {
                    System.out.println("**************qq登录成功 qqOpenId = " + qqOpenId);
                    // 获取QQ用户信息
                    JSONObject object = QQUtil.getUserInfo(qqAccessToken, qqOpenId);
                    // 数据库中判断qqOpenId是否存在，存在则登录，不存在则注册
                    Users user = userService.getUserByQQOpenId(qqOpenId);
                    if (user != null) {
                        user.setAvatar(object.getJSONObject("user").getString("avatar"));
                        user.setNickname(object.getJSONObject("user").getString("nickname"));
                        user.setLastLoginTime(DateUtil.formatDateTime(new Date()));
                        userService.updateUser(user);
                        SessionUser suser = SessionUser.getInstance(user);
                        String token = UUID.randomUUID().toString();
                        redisHandle.set(token, suser, 60 * 60L * 24 * 7);// 设置用户缓存及过期时间（一星期）
                        JSONObject data = new JSONObject();
                        data.put("userId", user.getId());
                        data.put("nickname", user.getNickname());
                        data.put("avatar", user.getAvatar());
                        data.put("token", token);
                        CookieUtil.setValue(getResponse(), "loginUser", data.toString());
                    } else {
                        user = new Users();
                        user.setAvatar(object.getJSONObject("user").getString("avatar"));
                        user.setNickname(object.getJSONObject("user").getString("nickname"));
                        user.setLastLoginTime(DateUtil.formatDateTime(new Date()));
                        user.setRegisterTime(DateUtil.formatDateTime(new Date()));
                        user.setQqId(qqOpenId);
                        userService.insertUser(user);
                        SessionUser suser = SessionUser.getInstance(user);
                        String token = UUID.randomUUID().toString();
                        redisHandle.set(token, suser, 60 * 60L * 24 * 7);// 设置用户缓存及过期时间（一星期）
                        JSONObject data = new JSONObject();
                        data.put("userId", user.getId());
                        data.put("nickname", user.getNickname());
                        data.put("avatar", user.getAvatar());
                        data.put("token", token);
                        CookieUtil.setValue(getResponse(), "loginUser", data.toString());
                    }

                } else {
                    putInRequest("error", "未获取到用户openid，请重新QQ授权登录");
                }
            } catch (Exception e) {
                e.printStackTrace();
                putInRequest("error", "登录异常");
            }
        } else {
            putInRequest("error", "缺少code参数");
        }
        return "redirect:" + referer;
    }
    /**
     * 退出登录
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        String referer = getRequest().getHeader("REFERER");
        String data= CookieUtil.getCookieValue(getRequest(), "loginUser");
        if(data!=null&&data!="") {
            JSONObject user=JSONObject.parseObject(EscapeUnescape.unescape(data));
            String token=user.getString("token");
            redisHandle.remove(token);
            CookieUtil.deleteValue("loginUser",getResponse());
        }
        return "redirect:" + referer;
    }
}


