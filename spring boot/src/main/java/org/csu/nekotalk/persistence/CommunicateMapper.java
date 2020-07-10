package org.csu.nekotalk.persistence;

import org.csu.nekotalk.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommunicateMapper {

    void insertFollowing(Following following);

    void deleteFollowing(Following following);

    //获取用户关注的所有用户电话
    List<String> getFollowingList(String userPhoneNumber);

    //获取用户粉丝电话
    List<String> getFollowedList(String followingPhoneNumber);





}
