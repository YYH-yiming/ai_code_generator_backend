package com.yyh.ai_code_generator.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PasswordSalt {
    private String salt;
    private String password;

    public PasswordSalt(String password, String salt) {
        this.salt = salt;
        this.password = password;
    }
}
