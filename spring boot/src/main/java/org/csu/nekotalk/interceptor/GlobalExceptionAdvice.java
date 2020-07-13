package org.csu.nekotalk.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.csu.nekotalk.annotation.exception.ParameterErrorException;
import org.csu.nekotalk.domain.ResponseTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Slf4j
@ControllerAdvice("org.csu.nekotalk")
public class GlobalExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseTemplate handleException(Exception e) {

        String msg = e.getMessage();
        if (msg == null || msg.equals("")) {
            e.printStackTrace();
            return ResponseTemplate
                    .builder()
                    .status(500)
                    .statusText("服务器出错")
                    .build();
        }
        else {
            e.printStackTrace();
            return ResponseTemplate
                    .builder()
                    .status(401)
                    .statusText(msg)
                    .build();
        }

    }

    //忽略参数的异常处理
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseTemplate parameterMissingExceptionHandler(MissingServletRequestParameterException e) {

        e.printStackTrace();
        return ResponseTemplate
                .builder()
                .status(422)
                .statusText(e.getMessage())
                .build();
    }

    //请求体为空的异常处理
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseTemplate parameterBodyMissingExceptionHandler(HttpMessageNotReadableException e) {
        e.printStackTrace();
        return ResponseTemplate
                .builder()
                .status(422)
                .statusText(e.getMessage())
                .build();
    }
    //参数校验异常处理
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseTemplate parameterExceptionHandler(MethodArgumentNotValidException e) {
    // 获取异常信息
        BindingResult exceptions = e.getBindingResult();

        e.printStackTrace();


        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);

                return ResponseTemplate
                        .builder()
                        .status(422)
                        .statusText(fieldError.getDefaultMessage())
                        .build();
            }
        }
        return ResponseTemplate
                .builder()
                .status(422)
                .statusText(e.getMessage())
                .build();
    }

    //自定义异常处理
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ParameterErrorException.class})
    public ResponseTemplate parameterExceptionHandler(ParameterErrorException e) {

        e.printStackTrace();
        return ResponseTemplate
                .builder()
                .status(422)
                .statusText(e.getMessage())
                .build();
    }
}
