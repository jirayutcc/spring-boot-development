package com.spring.development.exception;

import com.spring.development.constant.ErrorMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomException extends Exception {

    private final String message;
    private String code;
    private String displayTitle;
    private String displayMessage;
    public CustomException(String message) {
        super(message);
        this.message = message;
    }

    public CustomException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public CustomException(String code, String message, String displayTitle, String displayMessage) {
        super(message);
        this.code = code;
        this.message = message;
        this.displayTitle = displayTitle;
        this.displayMessage = displayMessage;
    }

    public CustomException(ErrorMapping.ErrorInfo errorInfo) {
        this.code = errorInfo.getCode();
        this.message = errorInfo.getMessage();
        this.displayTitle = errorInfo.getTitle();
        this.displayMessage = errorInfo.getMessage();
    }
}
