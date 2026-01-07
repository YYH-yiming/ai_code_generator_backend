package com.yyh.ai_code_generator.exception;

import com.yyh.ai_code_generator.common.BaseResponse;
import com.yyh.ai_code_generator.common.ResultUtils;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 
 * 增强异常处理的健壮性
 * 
 * @Hidden
 *  如果springboot > 3.4 @RestControllerAdvice会冲突，需要加上@Hidden
 * @RestControllerAdvice
 *  帮助捕获controller中的异常
 * @Slf4j
 *  log的注解
 * */
@Hidden
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "系统错误");
    }
}
