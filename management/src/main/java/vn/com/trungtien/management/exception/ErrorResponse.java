package vn.com.trungtien.management.exception;

import lombok.*;

@Getter
@Setter
public class ErrorResponse {
    private String code;
    private String message;
    private String detailMessage;
    private Object errors;

    public ErrorResponse(String code, String message, String detailMessage) {
        this.code = code;
        this.message = message;
        this.detailMessage = detailMessage;
        moreInformation = "http://localhost:8080api/exceptions" + code;
    }

    public ErrorResponse(String code, String message, String detailMessage, Object errors) {
        this.code = code;
        this.message = message;
        this.detailMessage = detailMessage;
        this.errors = errors;
    }

    private String moreInformation;
}
