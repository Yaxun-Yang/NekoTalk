package org.csu.nekotalk.domain;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;




public class QQUtil {
    private static final Logger log = LoggerFactory.getLogger(QQUtil.class);

    private static final String QQ_APP_ID="XXX";//改成自己的
    private static final String QQ_APP_SECRET="XXX";//改成自己的
    private static final String LOGIN_REDIRECT_URI="https://www.zwqh.top/account/qqLogin";	//改成自己的
    private static final String BIND_REDIRECT_URI="https://www.zwqh.top/account/qqBind";									  //改成自己的
    private static final String AUTH_CODE_URL="https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id="+QQ_APP_ID+"&redirect_uri=REDIRECT_URI&state=STATE";
    private static final String ACCESS_TOKEN_URL="https://graph.qq.com/oauth2.0/token?client_id="+QQ_APP_ID+"&client_secret="+QQ_APP_SECRET+"&code=CODE&grant_type=authorization_code&redirect_uri=REDIRECT_URI";
    private static final String REFRESH_TOKEN_URL="https://graph.qq.com/oauth2.0/token?client_id="+QQ_APP_ID+"&client_secret="+QQ_APP_SECRET+"&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
    private static final String OPEN_ID_URL="https://graph.qq.com/oauth2.0/me?access_token=ACCESS_TOKEN";
    private static final String USER_INFO_URL="https://graph.qq.com/user/get_user_info?access_token=ACCESS_TOKEN&oauth_consumer_key="+QQ_APP_ID+"&openid=OPENID";

    public static JSONObject getJsonStrByQueryUrl(String paramStr){
        String[] params = paramStr.split("&");
        JSONObject obj = new JSONObject();
        for (int i = 0; i < params.length; i++) {
            String[] param = params[i].split("=");
            if (param.length >= 2) {
                String key = param[0];
                String value = param[1];
                for (int j = 2; j < param.length; j++) {
                    value += "=" + param[j];
                }
                try {
                    obj.put(key,value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }
    /**
     * 获取授权登录页码url
     * @return
     */
    public static String getLoginConnectUrl(String state) {
        String url=null;
        try{
            url=AUTH_CODE_URL.replace("REDIRECT_URI", URLEncoder.encode(LOGIN_REDIRECT_URI, "utf-8")).replace("STATE", state);
        }catch (Exception e) {
            log.error(e.toString());
        }
        return url;
    }

    /**
     * 获取授权绑定页码url
     * @return
     */
    public static String getBindConnectUrl() {
        String url=null;
        try{
            url=AUTH_CODE_URL.replace("REDIRECT_URI", URLEncoder.encode(BIND_REDIRECT_URI, "utf-8"));
        }catch (Exception e) {
            log.error(e.toString());
        }
        return url;
    }

    /**
     * 获取AccessToken
     * @return 返回拿到的access_token及有效期
     */
    public static QQAccessToken getQQLoginAccessToken(String code) throws ClientProtocolException, IOException{
        QQAccessToken token = new QQAccessToken();
        String url = ACCESS_TOKEN_URL.replace("CODE", code).replace("REDIRECT_URI", URLEncoder.encode(LOGIN_REDIRECT_URI, "utf-8"));
        log.info("这是请求路径："+url);
        String result = HttpClientUtils.httpGet(url).toString();
        JSONObject jsonObject=getJsonStrByQueryUrl(result);
        log.info("这是返回结果："+jsonObject);
        if(jsonObject!=null){ //如果返回不为空，将返回结果封装进AccessToken实体类
            token.setAccessToken(jsonObject.getString("access_token"));//接口调用凭证
            token.setExpiresIn(jsonObject.getInteger("expires_in"));//access_token接口调用凭证超时时间，单位（秒）
            token.setRefreshToken(jsonObject.getString("refresh_token"));
        }
        return token;
    }

    /**
     * 获取AccessToken
     * @return 返回拿到的access_token及有效期
     */
    public static QQAccessToken getQQBindAccessToken(String code) throws ClientProtocolException, IOException{
        QQAccessToken token = new QQAccessToken();
        String url = ACCESS_TOKEN_URL.replace("CODE", code).replace("REDIRECT_URI", URLEncoder.encode(BIND_REDIRECT_URI, "utf-8"));
        log.info("这是请求路径："+url);
        String result = HttpClientUtils.httpGet(url).toString();
        JSONObject jsonObject=getJsonStrByQueryUrl(result);
        log.info("这是返回结果："+jsonObject);
        if(jsonObject!=null){ //如果返回不为空，将返回结果封装进AccessToken实体类
            token.setAccessToken(jsonObject.getString("access_token"));//接口调用凭证
            token.setExpiresIn(jsonObject.getInteger("expires_in"));//access_token接口调用凭证超时时间，单位（秒）
            token.setRefreshToken(jsonObject.getString("refresh_token"));
        }
        return token;
    }

    /**
     * 刷新或续期access_token使用
     * @return 返回拿到的access_token及有效期
     */
    public static QQAccessToken refreshQQAccessToken(String refreshToken) throws ClientProtocolException, IOException{
        QQAccessToken token = new QQAccessToken();
        String url = REFRESH_TOKEN_URL.replace("REFRESH_TOKEN",refreshToken);
        log.info("这是请求路径："+url);
        String result = HttpClientUtils.httpGet(url).toString();
        log.info("这是返回结果："+result);
        JSONObject jsonObject=getJsonStrByQueryUrl(result);
        log.info("这是转为json的结果："+jsonObject);
        if(jsonObject!=null){ //如果返回不为空，将返回结果封装进AccessToken实体类
            token.setAccessToken(jsonObject.getString("access_token"));//接口调用凭证
            token.setExpiresIn(jsonObject.getInteger("expires_in"));//access_token接口调用凭证超时时间，单位（秒）
            token.setRefreshToken(jsonObject.getString("refresh_token"));
        }
        return token;
    }
    /**
     * 获取QQopenId
     * @return QQopenId
     */
    public static String getQQOpenId(String accessToken) throws ClientProtocolException, IOException{
        String url = OPEN_ID_URL.replace("ACCESS_TOKEN",accessToken);
        log.info("这是请求路径："+url);
        String result = HttpClientUtils.httpGet(url).toString().replace("callback(", "").replace(");", "");
        log.info("这是返回结果："+result);
        JSONObject jsonObject=JSON.parseObject(result);
        log.info("这是转为json的结果："+jsonObject);
        if(jsonObject!=null&&jsonObject.getString("openid")!=null){ //如果返回不为空
            return jsonObject.getString("openid");
        }
        return null;
    }
    /**
     * 获取QQ用户信息
     * @param accessToken
     * @param openId
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     */
    public static JSONObject getUserInfo(String accessToken, String openId) throws ClientProtocolException, IOException {
        // 拼接请求地址
        String url = USER_INFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        log.info("这是请求路径："+url);
        String result = HttpClientUtils.httpGet(url).toString();
        log.info("这是返回结果："+result);
        JSONObject jsonObject=JSONObject.parseObject(result);
        log.info("这是转为json的结果："+jsonObject);
        JSONObject json=new JSONObject();
        if (jsonObject!=null&&jsonObject.getInteger("ret").equals(0)) {
            try {
                Users user= new Users();
                // 用户的标识
                user.setSign(openId);
                // 昵称
                user.setUsername(jsonObject.getString("username"));
                if(jsonObject.getString("figureurl_2")!=null&&!jsonObject.getString("figureurl_2").isEmpty()) {
                    // 用户头像
                    user.setAvatar(jsonObject.getString("figureurl_qq_2"));
                }else {
                    // 用户头像
                    user.setAvatar(jsonObject.getString("figureurl_qq_1"));
                }
                json.put("success", true);
                json.put("msg", "success");
                json.put("user", user);
            } catch (Exception e) {
                int errorCode = jsonObject.getInteger("ret");
                String errorMsg = jsonObject.getString("msg");
                log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, e.toString());
                json.put("success", false);
                json.put("msg", errorMsg);
                json.put("user", null);
            }
        }else {
            json.put("success", false);
            json.put("msg", "请先登录");
            json.put("user", null);
        }
        return json;
    }
}