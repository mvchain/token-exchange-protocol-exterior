package com.mvc.sell.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * ForgetDTO
 *
 * @author qiyichen
 * @create 2018/3/14 17:52
 */
@Data
public class ForgetDTO implements Serializable {
    private static final long serialVersionUID = -5383214751411489344L;

    private String email;
    private String emailCode;
    private String password;
}
