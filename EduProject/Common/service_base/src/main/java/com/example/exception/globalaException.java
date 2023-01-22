package com.example.exception;





import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cc
 * @date 2023年01月16日 20:58
 */
@ControllerAdvice
@Slf4j
public class globalaException {

    //异常处理 1.全局异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行全局异常处理");
    }

    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R errorcondition(Exception e){
        e.printStackTrace();
        return R.error().message("执行全局异常处理");
    }


    //自定义异常
    @ExceptionHandler(fuliexception.class) //自己写的异常
    @ResponseBody
    public R errorbyself(fuliexception e){
        log.error(e.getMsg());
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }



}
