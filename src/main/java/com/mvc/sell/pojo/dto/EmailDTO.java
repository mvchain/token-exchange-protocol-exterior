package com.mvc.sell.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * EmailDTO
 *
 * @author qiyichen
 * @create 2018/3/14 18:16
 */
@Data
public class EmailDTO implements Serializable {

    private String email;
    private String emailCode;

}
