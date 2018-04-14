package com.mvc.sell.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * login dto
 *
 * @author qiyichen
 * @create 2018/3/14 17:41
 */
@Data
public class LoginDTO implements Serializable {
    private static final long serialVersionUID = 5343369467760246626L;

    @NotNull(message = "{USERNAME_EMPTY}")
    private String username;

    @NotNull(message = "{PWD_EMPTY}")
    private String password;

    private String imageCode;
}
