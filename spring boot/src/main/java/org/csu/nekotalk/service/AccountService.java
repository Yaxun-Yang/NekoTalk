package org.csu.nekotalk.service;


import org.csu.nekotalk.domain.Following;
import org.csu.nekotalk.domain.Users;
import org.csu.nekotalk.persistence.AccountMapper;
import org.csu.nekotalk.persistence.MomentMapper;
import org.csu.nekotalk.persistence.SessionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;



    public void insertUser(Users user){
        accountMapper.insertUser(user);
    }

    @Transactional
    public void deleteUser(String phoneNUmber) { accountMapper.deleteUser(phoneNUmber); }

    public void updateUser(Users user){ accountMapper.updateUser(user); }

    public Users getUserByPhoneNumber(String phoneNUmber){ return accountMapper.getUserByPhoneNumber(phoneNUmber); }

    public Users getUserByPhoneNumberAndPassword(String phoneNUmber, String password){ return accountMapper.getUserByPhoneNumberAndPassword(phoneNUmber,password); }

    public String getUserPassword(String phoneNUmber){ return accountMapper.getUserPassword(phoneNUmber); }

    public void insertFollowing(Following following){ accountMapper.insertFollowing(following);}

    public void deleteFollowing(Following following){ accountMapper.deleteFollowing(following);}

    public boolean ifFollowing (String userPhoneNumber, String followingPhoneNumber)
    {
        Following following = new Following(userPhoneNumber, followingPhoneNumber);
        return (accountMapper.ifFollowing(following) == null)? false:true;
    }

    public boolean ifFollowed(String userPhoneNumber, String followedPhoneNumber)
    {
        Following following = new Following(followedPhoneNumber, userPhoneNumber);
        return (accountMapper.ifFollowing(following) == null)?false : true;
    }

    //获取关注列表
    public List<Users> getFollowingList(String userPhoneNumber){
        List<String> followingPhoneNumberList= accountMapper.getFollowingList(userPhoneNumber);
        List<Users> followingList=new ArrayList<Users>();
        for(int i=0; i<followingPhoneNumberList.size(); i++)
        {
            followingList.add(accountMapper.getUserByPhoneNumber(followingPhoneNumberList.get(i)));
        }
        return followingList;

    }

    //获取粉丝列表
    public List<Users> getFollowedList(String userPhoneNumber){
        List<String> followedPhoneNumberList= accountMapper.getFollowedList(userPhoneNumber);
        List<Users> followedList=new ArrayList<>();
        for(int i=0; i<followedPhoneNumberList.size(); i++)
        {
            followedList.add(accountMapper.getUserByPhoneNumber(followedPhoneNumberList.get(i)));
        }
        return followedList;
    }




}
