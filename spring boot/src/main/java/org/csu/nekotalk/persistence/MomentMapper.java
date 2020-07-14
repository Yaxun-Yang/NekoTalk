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

    void insertPower(PowerUsers powerUsers);
    
    void insertComment(Comment comment);

    void insertFavour(Favour favour);

    void insertFork(ForkMoment forkMoment);

    void insertOriginalityMoment(OriginalityMoment originalityMoment);

    //delete
    void deleteMoment(String momentId);

    void deleteMomentPicture(String momentId);

    void deleteLabel(String labelId);

    void deleteLabelInclude(LabelInclude labelInclude);

    void deleteMomentLabelInclude(String momentId);

    void deletePower(PowerUsers powerUsers);

    void deleteMomentPower(String momentId);

    //删除某条动态的所有评论
    void deleteAllComments(String momentId);

    //删除某条评论
    void deleteComment(String commentId);


    //删除某条评论的所有回复评论
    void deleteReplyComment(String replyCommentId);


    //删除某动态的所有点赞
    void deleteAllFavour(String momentId);

    //删除一个点赞
    void deleteFavour(String phoneNumber,String momentId);

    //删除某条动态的所有转发
    void deleteAllFork(String forkForm);

    //删除某条转发
    void deleteFork(String momentId);

    void deleteOriginalityMoment(String momentId);

    //修改动态内容
    void updateMoment(Moment moment);

    //修改评论内容
    void updateComment(Comment comment);

    void updateMomentPicture(MomentPicture momentPicture);

    //获取某用户最近一条动态id
    String getRecentMomentId(String phoneNumber);

    //获取动态最新一张图片id
    String getRecentMomentPictureId(String momentId);

    List<MomentPicture> getMomentPictureListByMomentId(String momentId);

    Comment getCommentByCommentId(String commentId);

    //获取具体用户的动态
    List<Moment> getMomentByPhoneNumber(String phoneNumber);

    //获得已知描述的标签id
    String getLabelIdByDescription(String description);

    String getLabelDescriptionByMomentId(String momentId);

    //由动态id获得动态
    Moment getMomentByMomentId(String momentId);

    OriginalityMoment getOriginalityMomentByMomentId(String momentId);

    ForkMoment getForkMomentByMomentId(String momentId);

    //获得拥有某关键字的动态
    List<Moment> getMomentByText(String text);

    //获得某用户的所有动态id
    List<String> getMomentIdByPhoneNumber(String phoneNumber);

    //获取有某标签的所有动态id
    List<String> getMomentIdByLabel(String LabelId);

    //获取用户关注者所有动态id
    List<String> getMomentIdListByFollowing(String phoneNumber);

    //获取用户所有点赞id
    List<String> getMomentIdListByFavour(String phoneNumber);

    //获取所有动态按时间排序
    List<String> getMomentIdList();

    List<Comment> getMomentCommentList(String momentIId);

    List<Comment> getReplyCommentList(String commentId);

    int getCommentNumber(String momentId);

    //判断用户是否在权限列表中
    PowerUsers ifPowerUsers (String momentId, String phoneNumber);

}
