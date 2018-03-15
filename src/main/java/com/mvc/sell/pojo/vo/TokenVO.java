package com.mvc.sell.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * token vo
 *
 * @author qiyichen
 * @create 2018/3/12 14:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenVO implements Serializable{


    private static final long serialVersionUID = -2962447657047492746L;

    private String token;
    private String refreshToken;
    private BigInteger id;
}
