package com.yyh.ai_code_generator.service;

import com.mybatisflex.core.service.IService;
import com.yyh.ai_code_generator.model.dto.PasswordSalt;
import com.yyh.ai_code_generator.model.entity.User;

/**
 * 用户 服务层。
 *
 * @author <a href="https://github.com/YYH-yiming/ai_code_generator_backend">YYH</a>
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    PasswordSalt getEncryptPassword(String userPassword);
}
