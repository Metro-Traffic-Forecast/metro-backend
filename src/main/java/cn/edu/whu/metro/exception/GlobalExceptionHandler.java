package cn.edu.whu.metro.exception;

import cn.edu.whu.metro.api.CommonResult;
import cn.edu.whu.metro.api.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * TODO 之后要针对每个异常进行单独处理
 *
 * @author thomas
 * @version 1.0
 * @date 2020/12/19 21:36 下午
 * 统一异常处理
 */
@Slf4j
@Component
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * NoHandlerFoundException 404 异常处理
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult<?> handleNotFoundException(NoHandlerFoundException e) throws Throwable {
        // 404不需要处理 直接返回就行
        return CommonResult.fail(ResultCode.NOT_FOUND);
    }

    @ExceptionHandler(value = NullPointerException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult<?> handleNullPointerException(NullPointerException e) throws Throwable {
        log.error(e.getMessage());
        return CommonResult.fail(ResultCode.NULL_POINTER_EXCEPTION);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) throws Throwable {
        log.error(e.getMessage());
        return CommonResult.fail(ResultCode.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult<?> handleValidException(MethodArgumentTypeMismatchException e) {

        return CommonResult.fail(ResultCode.METHOD_ARG_TYPE_MISMATCH);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult<?> handleException(Exception e) throws Throwable {
        log.error(e.getMessage());
        return CommonResult.fail(ResultCode.INTERNAL_SERVER_ERROR);
    }





}
