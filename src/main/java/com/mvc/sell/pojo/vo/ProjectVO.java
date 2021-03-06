package com.mvc.sell.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * ProjectVO
 *
 * @author qiyichen
 * @create 2018/3/13 11:28
 */
@Data
public class ProjectVO implements Serializable {
    private static final long serialVersionUID = -42092415251737822L;

    private BigInteger id;
    private String title;
    private String tokenName;
    private String contractAddress;
    private BigDecimal ethNumber;
    private Float ratio;
    private Date startTime;
    private Date stopTime;
    private String homepage;
    private String whitePaperAddress;
    private String whitePaperName;
    private String projectImageAddress;
    private String projectImageName;
    private String projectCoverAddress;
    private String projectCoverName;
    private String leaderImageAddress;
    private String leaderImageName;
    private String leaderName;
    private String position;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private Integer status;
    private String coin;

}
