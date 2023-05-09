package com.spring.development.exception;

import com.jirayutcc.webflux.webservice.constant.ErrorMapping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ClientException extends RuntimeException {

    String statusCd;
    String statusDesc;

    public ClientException(ErrorMapping.ErrorInfo errorInfo) {
        this.statusCd = errorInfo.getCode();
        this.statusDesc = errorInfo.getMessage();
    }
}
