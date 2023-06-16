package com.spring.development.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HeaderResponse implements Serializable {

    @JsonAlias({"reqID", "reqId"})
    private String reqID;
    private String reqDtm;

    @JsonAlias({"txnRefID", "txnRefId"})
    private String txnRefID;
    private String service;

    @Builder.Default
    @JsonAlias({"statusCd", "statusCode"})
    private String statusCd = "0000";
    @Builder.Default
    private String statusDesc = "SUCCESS";

    private ErrorDisplay errorDisplay = ErrorDisplay.builder().build();

    public HeaderResponse(HeaderRequest headerRequest) {
        this.reqID = headerRequest.getReqID();
        this.reqDtm = headerRequest.getReqDtm();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ErrorDisplay {
        @Builder.Default
        private String message = "";
        @Builder.Default
        private String title = "";

    }

    public static HeaderResponse mapResponse(HeaderRequest headerRequest) {
        return HeaderResponse.builder()
                .statusCd("0000")
                .statusDesc("SUCCESS")
                .reqDtm(headerRequest.getReqDtm())
                .reqID(headerRequest.getReqID())
                .txnRefID(headerRequest.getTxnRefId())
                .service(headerRequest.getService())
                .build();
    }
}
