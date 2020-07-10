package org.csu.nekotalk.persistence;

import org.csu.nekotalk.domain.*;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountMapper {

    void insertUser(Users users);

    void updateUser(Users users);

    Users getUserByPhoneNumber(String phoneNumber);

    Users getUserByPhoneNumberAndPassword(String phoneNumber, String password);

    String getUserPassword(String phoneNumber);

    void deleteUser(String phoneNumber);



}
