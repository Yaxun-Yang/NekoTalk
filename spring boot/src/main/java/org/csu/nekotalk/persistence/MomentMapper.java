package org.csu.nekotalk.persistence;

import org.csu.nekotalk.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MomentMapper {
    //insert
    void insertMoment(Moment moment);
    
    void insertMomentPicture(MomentPicture momentPicture);
    
    void insertLabel(Label label);
    
    void insertLabelInclude(LabelInclude labelInclude);

    void insertPower(Map<String, String> param);
    
    void insertComments(Comments comments);

    void insertFavour(Favour favour);

    void insertFork(ForkMoment forkMoment);

    void insertOriginalityMoment(OriginalityMoment originalityMoment);

    //delete
    void deleteMoment(String momentId);

    void deleteMomentPicture(String momentId);

    void deleteLabel(String labelId);

    void deleteLabelInclude(LabelInclude labelInclude);

    void deletePower(PowerUsers powerUsers);

    //删除某条动态的所有评论
    void deleteAllComments(String momentId);

    //删除某条动态的某条评论
    void deleteComment(String commentId);


    //删除某条评论的所有回复评论
    void deleteSomeReplyComment(String replyCommentId);

    //删除一条评论的一条回复评论
    void deleteOneReplyComment(Map<String, String> param);

    //删除某动态的所有点赞
    void deleteAllFavour(String momentId);

    //删除一个点赞
    void deleteFavour(Favour favour);

    //删除某条动态的所有转发
    void deleteAllFork(String forkForm);

    //删除某条转发
    void deleteFork(String momentId);

    void deleteOriginalityMoment(String momentId);

    //update
    void updateMoment(Moment moment);

    //获取关注人的动态
    List<Moment> getMomentByPhoneNumber(String phoneNumber);

    //获得已知描述的标签id
    int getLabelIdByDescription(String description);

    //获取有某标签的所有动态id
    List<Integer> getMomentIdByLabel(int LabelId);

    //由动态id获得动态
    Moment getMomentByMomentId(int momentId);

    //获得拥有某关键字的动态
    List<Moment> getMomentByText(String text);

    //获得某用户点赞的所有动态id
    List<Integer> getMomentIdByPhoneNumber(String phoneNumber);





}
