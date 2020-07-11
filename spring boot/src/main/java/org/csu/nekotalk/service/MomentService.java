package org.csu.nekotalk.service;

import org.csu.nekotalk.domain.ForkMoment;
import org.csu.nekotalk.domain.Moment;
import org.csu.nekotalk.domain.OriginalityMoment;
import org.csu.nekotalk.persistence.MomentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MomentService {

    @Autowired
    private MomentMapper momentMapper;

    public void insertMoment(Moment moment)
    {
        momentMapper.insertMoment(moment);
    }

    public void insertOriginalityMoment(OriginalityMoment originalityMoment)
    {
        momentMapper.insertOriginalityMoment(originalityMoment);
    }

    public void insertForkMoment(ForkMoment forkMoment)
    {
        momentMapper.insertFork(forkMoment);
    }

    public void deleteMoment(String momentId)
    {
        momentMapper.deleteMoment(momentId);
    }

    public void deleteOriginalityMoment(String momentId)
    {
        momentMapper.deleteOriginalityMoment(momentId);
    }

    public void deleteForkMoment(String momentId)
    {
        momentMapper.deleteFork(momentId);
    }

    public String getRecentMomentId(String phoneNumber)
    {
            return momentMapper.getRecentMomentId(phoneNumber);
    }

    public Moment getMomentByMomentId(String momentId)
    {
        return momentMapper.getMomentByMomentId(momentId);
    }

    public List<Moment> getMomentListByPhoneNumber(String phoneNumber)
    {
        return momentMapper.getMomentByPhoneNumber(phoneNumber);
    }

    public List<Moment> gerMomentListByLabelId(String labelId)
    {
         List <String> momentIdList =momentMapper.getMomentIdByLabel(labelId);
         List <Moment> momentList = new ArrayList();
         for (int i =0; i<momentIdList.size(); i++)
         {
             momentList.add(momentMapper.getMomentByMomentId(momentIdList.get(i)));
         }
         return momentList;

    }

    public List<Moment> getMomentList()
    {
        return momentMapper.getMomentList();
    }


}
