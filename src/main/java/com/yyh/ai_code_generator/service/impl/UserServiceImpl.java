package com.yyh.ai_code_generator.service.impl;

import cn.hutool.core.util.StrUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.yyh.ai_code_generator.exception.BusinessException;
import com.yyh.ai_code_generator.exception.ErrorCode;
import com.yyh.ai_code_generator.model.dto.PasswordSalt;
import com.yyh.ai_code_generator.model.entity.User;
import com.yyh.ai_code_generator.mapper.UserMapper;
import com.yyh.ai_code_generator.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * 用户 服务层实现。
 *
 * @author <a href="https://github.com/YYH-yiming/ai_code_generator_backend">YYH</a>
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>  implements UserService{

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        //健壮性检验
        if(StrUtil.hasBlank(userAccount, userPassword, checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if(userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账户太短");
        }
        if(userPassword.length() < 8 || checkPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户密码太短");
        }
        if(!userPassword.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次输入密码不一致");
        }

        //是否已经存在
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userAccount", userAccount);
        long count = this.mapper.selectCountByQuery(queryWrapper);
        if(count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号重复");
        }
        //开始注册

        PasswordSalt passwordSalt = getEncryptPassword(userPassword);
        String encryptPassword = passwordSalt.getPassword();
        String salt = passwordSalt.getSalt();
        String defaultName = "user" + System.currentTimeMillis();
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        user.setUserName(defaultName);
        user.setSalt(salt);

        boolean isSaved = this.save(user);
        if(!isSaved){
            throw  new BusinessException(ErrorCode.OPERATION_ERROR, "注册失败，数据库错误");
        }

        return user.getId();
    }

    @Override
    public PasswordSalt getEncryptPassword(String userPassword) {
        // 盐值，混淆密码
        String SALT = UUID.randomUUID().toString().replaceAll("-", "");
        String Password = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes(StandardCharsets.UTF_8));
        return new PasswordSalt(Password, SALT);
    }

}
