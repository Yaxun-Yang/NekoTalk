package org.csu.nekotalk.service;

import org.csu.nekotalk.domain.Session;
import org.csu.nekotalk.domain.SessionJoin;
import org.csu.nekotalk.domain.SessionMessage;
import org.csu.nekotalk.domain.SessionPicture;
import org.csu.nekotalk.persistence.SessionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {

    @Autowired
    private SessionMapper sessionMapper;

    /**
     *insert
     */
    public void insertSession(Session session){
        sessionMapper.insertSession(session);
    }

    public void insertSessionJoin(SessionJoin sessionJoin){
        sessionMapper.insertSessionJoin(sessionJoin);
    }

    public void insertSessionMessage(SessionMessage sessionMessage){
        sessionMapper.insertSessionMessage(sessionMessage);
    }

    public void insertSessionPicture(SessionPicture sessionPicture){
        sessionMapper.insertSessionPicture(sessionPicture);
    }

    /**
     * delete 认为用户删除会话并不能直接对数据库进行修改，
     * 故逻辑实现不包括删除功能
     */

    /**
     * Get
     */
//    public String getRecentSessionId(){
//        return sessionMapper.getRecentSessionId();
//    }

    public String getRecentSessionId(String phoneNumber){
        return sessionMapper.getRecentSessionId(phoneNumber);
    }

    public Session getSessionBySessionId(String sessionId){
        return sessionMapper.getSession(sessionId);
    }

    //获得某个用户的所有会话ID
    public List<Integer> getUserAllSession(String phoneNumber){
        return sessionMapper.getUserSession(phoneNumber);
    }

    //返回某个会话的消息列表
    public List<SessionMessage> getAllSessionMessage(String sessionId){
        return sessionMapper.getSessionMessage(sessionId);
    }

    //返回某个会话的图片列表
    public List<SessionPicture> getAllSessionPicture(String sessionId){
        return sessionMapper.getSessionPicture(sessionId);
    }

    //获取群聊中所有用户电话号码
    public List<String> getAllSessionJoinNumber(String sessionId){
        return sessionMapper.getSessionJoin(sessionId);
    }


}
