package org.csu.nekotalk.api;



import com.alibaba.fastjson.JSONObject;
import org.csu.nekotalk.domain.*;
import org.csu.nekotalk.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        session.setCreateTimestamp(new Timestamp(System.currentTimeMillis()));
        sessionService.insertSession(session);

        session.setSessionId(sessionService.getRecentSessionId(session.getSessionId()));

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
        sessionMessage.setTime(new Timestamp(System.currentTimeMillis()));

        sessionService.insertSessionMessage(sessionMessage);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @PostMapping("/sessionPicture")
    public ResponseTemplate insertSessionPicture(@RequestBody JSONObject req){
        SessionPicture sessionPicture = new SessionPicture();
        sessionPicture.setSessionId(req.getString("sessionId"));
        sessionPicture.setPhoneNumber(req.getString("phoneNumber"));
        sessionPicture.setUrl(req.getString("url"));
        sessionPicture.setTime(new Timestamp(System.currentTimeMillis()));
        sessionService.insertSessionPicture(sessionPicture);

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

    @GetMapping("/pictureList")
    public ResponseTemplate getPictureList(@RequestParam String sessionId){
        JSONObject data = new JSONObject();

        data.put("pictureList",sessionService.getAllSessionPicture(sessionId));

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






}
