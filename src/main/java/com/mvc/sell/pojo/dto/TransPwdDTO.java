package com.mvc.sell.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * TransPwdDTO
 *
 * @author qiyichen
 * @create 2018/3/14 18:26
 */
@Data
public class TransPwdDTO implements Serializable {
    private static final long serialVersionUID = 2611482740809051863L;

    private String transactionPassword;
    private String emailCode;
}
