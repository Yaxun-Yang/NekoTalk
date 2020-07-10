package org.csu.nekotalk.persistence;

import org.csu.nekotalk.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionMapper {

    //insert
    void insertSession(Session session);
    
    void insertSessionJoin(SessionJoin sessionJoin);
    
    void insertSessionMessage(SessionMessage sessionMessage);
    
    void insertSessionPicture(SessionPicture sessionPicture);

    //delete
//    void deleteSession(Session session);
//
//    void deleteSessionJoin(SessionJoin sessionJoin);
//
//    void deleteSessionMessage(SessionMessage sessionMessage);
//
//    void deleteSessionPicture(SessionPicture sessionPicture);

    //get
    Session getSession(String sessionId);

    //获得某用户的所有会话id
    List<Integer> getUserSession(String phoneNumber);

    List<SessionMessage> getSessionMessage(String sessionId);

    List<SessionPicture> getSessionPicture(String sessionId);

    //获取某会话的参与人电话
    List<String> getSessionJoin(String sessionId);


}
