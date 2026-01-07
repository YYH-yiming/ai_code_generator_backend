package com.yyh.ai_code_generator.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 删除请求包装类 以id为主键
 * */
@Data
public class DeleteRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}
