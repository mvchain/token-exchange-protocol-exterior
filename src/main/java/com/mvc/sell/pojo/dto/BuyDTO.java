package com.mvc.sell.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * BuyDTO
 *
 * @author qiyichen
 * @create 2018/3/15 14:21
 */
@Data
public class BuyDTO implements Serializable {

    private static final long serialVersionUID = 3760227948938685745L;

    private BigInteger projectId;
    private BigDecimal ethNumber;

}
