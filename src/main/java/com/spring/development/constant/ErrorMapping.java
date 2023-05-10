package com.spring.development.constant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class ErrorMapping {

    public static final ErrorInfo CODE0000 = ErrorInfo.builder()
            .code("0000")
            .build();

    public static final ErrorInfo CODE9999 = ErrorInfo.builder()
            .code("9999")
            .message("System error")
            .build();

	public static final ErrorInfo CODE9400 = ErrorInfo.builder()
		.code("9400")
		.message("Bad Request")
		.build();

    @Data
    @Builder
    @AllArgsConstructor
    public static class ErrorInfo {
        @Builder.Default
        private String code = "";
        @Builder.Default
        private String title = "";
        @Builder.Default
        private String message = "";
    }
}
