package org.csu.nekotalk.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @NotBlank(message = "请输入手机号：")
    @Size(max=11,min=11,message = "phoneNumber:11 characters")
    private String phoneNumber;

    @NotBlank(message = "username: Not blank")
    private String username;

    @NotBlank(message = "password: Not blank")
    private String password;

    @Size(max=50,min=0)
    private String avatar;//头像

    @Size(max=6,min=0,message = "sex:Between 0-6 characters")
    private String sex;

    @Size(max=50,min=0,message="sign: Between 0-50 characters")
    private String sign;

    @Size(max =50,min=0,message = "address:Between 0-50 characters")
    private String lastAddress;



}
