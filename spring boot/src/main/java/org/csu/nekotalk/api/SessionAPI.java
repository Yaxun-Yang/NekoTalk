package org.csu.nekotalk.api;



import com.alibaba.fastjson.JSONObject;
import net.sf.json.JSONArray;
import org.csu.nekotalk.domain.*;
import org.csu.nekotalk.service.PictureService;
import org.csu.nekotalk.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/session")
public class SessionAPI {

    @Autowired
    private SessionService sessionService;

    /**
     * POST
     */
    @PostMapping("/session")
    public ResponseTemplate insertSession(@RequestBody JSONObject req){
        Session session = new Session();
        session.setMore(req.getString("more"));
        session.setCreateTimeStamp(new Timestamp(System.currentTimeMillis()));
        sessionService.insertSession(session);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @PostMapping("/sessionJoin")
    public ResponseTemplate insertSessionJoin(@RequestBody JSONObject req){
        SessionJoin sessionJoin = new SessionJoin();
        sessionJoin.setSessionId(req.getString("sessionId"));
        sessionJoin.setPhoneNumber(req.getString("phoneNumber"));
        sessionService.insertSessionJoin(sessionJoin);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @PostMapping("/sessionMessage")
    public ResponseTemplate insertSessionMessage(@RequestBody JSONObject req){
        SessionMessage sessionMessage = new SessionMessage();
        sessionMessage.setSessionId(req.getString("sessionId"));
        sessionMessage.setPhoneNumber(req.getString("phoneNumber"));
        sessionMessage.setText(req.getString("text"));
        sessionMessage.setSessionTimeStamp(new Timestamp(System.currentTimeMillis()));
        sessionService.insertSessionMessage(sessionMessage);

        if(req.get("sessionPicture") != null)
        {
            String key = "message"+sessionService.getRecentMessageId(sessionMessage.getSessionId());
            String pictureName = req.getJSONObject("sessionPicture").getJSONObject("response").getString("data");
            String fileType = pictureName.substring(pictureName.lastIndexOf(".")).toLowerCase();
            key+=fileType;
            File file = new File(pictureName);
            PictureService.uploadImage(file, key);
            sessionMessage.setPicture(PictureService.domain+key);
            sessionService.updateSessionMessage(sessionMessage);
        }

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }


    /**
     * delete
     */
    /**
     * fetch
     */
    /**
     * put
     */
    /**
     * get
     */
    @GetMapping("/session")
    public ResponseTemplate getSession(@RequestParam String sessionId){
        JSONObject data = new JSONObject();
        data.put("session",sessionService.getSessionBySessionId(sessionId));

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .data(data)
                .build();
    }

    @GetMapping("/sessionList")
    public ResponseTemplate getUserSession(@RequestParam String phoneNumber){
        JSONObject data = new JSONObject();

        data.put("userSession",sessionService.getUserAllSession(phoneNumber));

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .data(data)
                .build();
    }

    @GetMapping("/messageList")
    public ResponseTemplate getMessageList(@RequestParam String sessionId){
        JSONObject data = new JSONObject();

        data.put("messageList",sessionService.getAllSessionMessage(sessionId));

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .data(data)
                .build();
    }


    @GetMapping("/phoneNumberList")
    public ResponseTemplate getPhoneNumberList(@RequestParam String sessionId){
        JSONObject data = new JSONObject();

        data.put("phoneNumberList",sessionService.getAllSessionJoinNumber(sessionId));

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .data(data)
                .build();
    }

    @PostMapping("/return")
    public ResponseTemplate justReturn(@RequestParam JSONObject req)
    {
        req .put("time", new Timestamp(System.currentTimeMillis()));

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .data(req)
                .build();
    }





}
