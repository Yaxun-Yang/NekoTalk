package org.csu.nekotalk.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.csu.nekotalk.domain.*;
import org.csu.nekotalk.persistence.AccountMapper;
import org.csu.nekotalk.persistence.MomentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MomentService {

    @Autowired
    private MomentMapper momentMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Transactional
    public void insertOriginalityMoment(Moment moment, OriginalityMoment originalityMoment) {

        momentMapper.insertMoment(moment);
        originalityMoment.setMomentId(momentMapper.getRecentMomentId(moment.getPhoneNumber()));
        momentMapper.insertOriginalityMoment(originalityMoment);
    }

    public void insertMomentPicture(MomentPicture momentPicture) {
        momentMapper.insertMomentPicture(momentPicture);
    }

    public void insertLabel(Label label) {
        momentMapper.insertLabel(label);
    }

    public void insertLabelIncluding(LabelInclude labelInclude) {
        momentMapper.insertLabelInclude(labelInclude);
    }

    @Transactional
    public void insertForkMoment(Moment moment, ForkMoment forkMoment) {
        momentMapper.insertMoment(moment);
        forkMoment.setMomentId(momentMapper.getRecentMomentId(moment.getPhoneNumber()));
        momentMapper.insertFork(forkMoment);
    }

    public void favour(Favour favour) {
        momentMapper.insertFavour(favour);
    }

    public void insertComment(Comment comment) {
        momentMapper.insertComment(comment);
    }

    public void insertPowerUser(PowerUsers powerUsers)
    {
        momentMapper.insertPower(powerUsers);
    }

    @Transactional
    public void deleteMoment(String momentId) {

       momentMapper.deleteAllComments(momentId);
       momentMapper.deleteMomentPicture(momentId);
       momentMapper.deleteMomentLabelInclude(momentId);
       momentMapper.deleteMomentPower(momentId);
        if(momentMapper.getMomentByMomentId(momentId).getOriginality().equals("Y"))
        {
            momentMapper.deleteOriginalityMoment(momentId);
        }
        else
        {
            momentMapper.deleteFork(momentId);
        }
        momentMapper.deleteMoment(momentId);
    }


    public void deleteFavour(String phoneNumber, String momentId)
    {
        momentMapper.deleteFavour(phoneNumber, momentId);
    }

    @Transactional
    public void deleteComment(String commentId)
    {
        momentMapper.deleteReplyComment(commentId);
        momentMapper.deleteComment(commentId);
    }

    public void updateMoment(Moment moment)
    {
        momentMapper.updateMoment(moment);
    }

    public void updateComment(Comment comment)
    {
        momentMapper.updateComment(comment);
    }

    public void updateMomentPicture(MomentPicture momentPicture)
    {
        momentMapper.updateMomentPicture(momentPicture);
    }

    public String getRecentMomentId(String phoneNumber) {
        return momentMapper.getRecentMomentId(phoneNumber);
    }

    public String getRecentPictureId(String momentId)
    {
        return  momentMapper.getRecentMomentPictureId(momentId);
    }

    public Moment getMomentByMomentId(String momentId)
    {
        return momentMapper.getMomentByMomentId(momentId);
    }

    public JSONObject getMomentShowByMomentId(String momentId) {
        JSONObject data = new JSONObject();
        Moment moment = momentMapper.getMomentByMomentId(momentId);
        data.put("momentId", momentId);
        data.put("phoneNumber", moment.getPhoneNumber());
        data.put("originality", moment.getOriginality());
        data.put("text", moment.getText());
        data.put("powerType", moment.getPowerType());
        data.put("momentTimeStamp", moment.getMomentTimeStamp());
        if (moment.getOriginality().equals("Y")) {
            OriginalityMoment originalityMoment = momentMapper.getOriginalityMomentByMomentId(momentId);
            data.put("address", originalityMoment.getAddress());
        } else {
            ForkMoment forkMoment = momentMapper.getForkMomentByMomentId(momentId);
            data.put("forkFrom", forkMoment.getForkFrom());
        }
        return data;
    }

    public Comment getCommentByCommentId(String commentId)
    {
        return momentMapper.getCommentByCommentId(commentId);
    }


    public String getLabelIdByLabelDescribe(String describe) {
        return momentMapper.getLabelIdByDescription(describe);
    }

    public JSONArray getMomentShowListByPhoneNumber(String phoneNumber) {
        List<String> momentIdList = momentMapper.getMomentIdByPhoneNumber(phoneNumber);
        JSONArray data = new JSONArray();
        for (int i = 0; i < momentIdList.size(); i++) {
            data.add(getMomentShowByMomentId(momentIdList.get(i)));
        }
        return data;
    }

    public JSONArray gerMomentShowListByLabelId(String labelId ,String  phoneNumber) {
        List<String> momentIdList = momentMapper.getMomentIdByLabel(labelId);
        JSONArray data = new JSONArray();
        for (int i = 0; i < momentIdList.size(); i++) {

            if(powerJudge(momentIdList.get(i),phoneNumber))
            data.add(getMomentShowByMomentId(momentIdList.get(i)));
        }
        return data;

    }


    public JSONArray getMomentShowListByFollowing(String phoneNumber) {

        List<String> momentIdList = momentMapper.getMomentIdListByFollowing(phoneNumber);
        JSONArray data = new JSONArray();
        for (int i = 0; i < momentIdList.size(); i++) {
            if (powerJudge(momentIdList.get(i), phoneNumber))
                data.add(getMomentShowByMomentId(momentIdList.get(i)));
        }

            return data;
        }

    public JSONArray getMomentShowListByFavour (String phoneNumber)
    {
            List<String> momentIdList = momentMapper.getMomentIdListByFavour(phoneNumber);
            JSONArray data = new JSONArray();
            for (int i = 0; i < momentIdList.size(); i++) {

                if (powerJudge(momentIdList.get(i), phoneNumber))
                    data.add(getMomentShowByMomentId(momentIdList.get(i)));
            }
            return data;
        }

    public JSONArray getMomentShowList ()
    {
            List<String> momentIdList = momentMapper.getMomentIdList();
            JSONArray data = new JSONArray();
            for (int i = 0; i < momentIdList.size(); i++) {
                Moment moment = momentMapper.getMomentByMomentId(momentIdList.get(i));
                data.add(getMomentShowByMomentId(moment.getMomentId()));
            }
            return data;
        }

    public JSONArray getCommentList(String momentId)
    {
        JSONArray data = new JSONArray();
        List<Comment>  commentList = momentMapper.getMomentCommentList(momentId);
        for(int i=0; i<commentList.size(); i++)
        {
            JSONObject momentComment = new JSONObject();
            momentComment.put("comment", commentList.get(i));
            momentComment.put("replyCommentList", momentMapper.getReplyCommentList(commentList.get(i).getCommentId()));
            data.add(momentComment);
        }
        return data;
    }

    public int getCommentNumber(String momentId)
    {
        return momentMapper.getCommentNumber(momentId);
    }


    private boolean powerJudge (String momentId, String phoneNumber)
    {

            Moment moment = momentMapper.getMomentByMomentId(momentId);
            boolean flag = true;
            switch (moment.getPowerType()) {
                case 2:
                    if (accountMapper.ifFollowing(new Following(moment.getPhoneNumber(), phoneNumber)) == null)
                        flag = false;
                    break;
                case 3:
                    if (momentMapper.ifPowerUsers(moment.getMomentId(), phoneNumber) == null)
                        flag = false;
                    break;
                case 4:
                    if (momentMapper.ifPowerUsers(moment.getMomentId(), phoneNumber) != null)
                        flag = false;
                    break;
                case 5:
                    if (!moment.getPhoneNumber().equals(phoneNumber))
                        flag = false;
                    break;
                default:
                    break;

            }

            if(phoneNumber.equals(moment.getPhoneNumber()))
                flag = true;

            return flag;
        }



}