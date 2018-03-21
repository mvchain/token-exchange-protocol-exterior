package com.mvc.sell.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * PwdDTO
 *
 * @author qiyichen
 * @create 2018/3/14 18:20
 */
@Data
public class PwdDTO implements Serializable {

    private String password;
    private String newPassword;
    private String emailCode;

}
