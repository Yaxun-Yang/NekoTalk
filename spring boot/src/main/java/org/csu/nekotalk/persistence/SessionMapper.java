package org.csu.nekotalk.persistence;

import org.csu.nekotalk.domain.Session;
import org.csu.nekotalk.domain.SessionJoin;
import org.csu.nekotalk.domain.SessionMessage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionMapper {

    //insert
    void insertSession(Session session);
    
    void insertSessionJoin(SessionJoin sessionJoin);
    
    void insertSessionMessage(SessionMessage sessionMessage);


    //delete
//    void deleteSession(Session session);
//
//    void deleteSessionJoin(SessionJoin sessionJoin);
//
//    void deleteSessionMessage(SessionMessage sessionMessage);
//
//    void deleteSessionPicture(SessionPicture sessionPicture);
    //update
    void updateSessionMessage(SessionMessage sessionMessage);




//get
    String getRecentSessionId(String phoneNumber);

    Session getSession(String sessionId);

    //获得某用户的所有会话id
    List<Integer> getUserSession(String phoneNumber);

    String getRecentSessionMessageId(String sessionId);

    List<SessionMessage> getSessionMessage(String sessionId);


    //获取某会话的参与人电话
    List<String> getSessionJoin(String sessionId);


}
