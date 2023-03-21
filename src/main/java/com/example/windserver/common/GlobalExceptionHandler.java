package com.example.windserver.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(DuplicateKeyException.class)
    public R<String> handleCustomException(DuplicateKeyException ex) {
        log.error(ex.getMessage());
        return R.error("用户名重复");
    }
    @ExceptionHandler(Exception.class)
    public R<String> handleAllExceptions(Exception ex) {
        log.error(ex.getMessage());
        // 在这里处理所有其他未捕获的异常
        return R.error("未知错误");
    }

}

