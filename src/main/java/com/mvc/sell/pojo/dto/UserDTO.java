package com.mvc.sell.pojo.dto;

import com.mvc.sell.constants.MessageConstants;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * UserDTO
 *
 * @author qiyichen
 * @create 2018/3/14 16:49
 */
@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 8254042571577899391L;
    @NotNull(message = MessageConstants.EMAIL_NULL)
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = MessageConstants.EMAIL_WRONG)
    private String email;
    @NotNull(message = MessageConstants.CODE_NULL)
    private String emailCode;
    @NotNull(message = MessageConstants.PASSWORD_NULL)
    private String password;
    @NotNull(message = MessageConstants.TRANS_PASSWORD_NULL)
    private String transactionPassword;

}
