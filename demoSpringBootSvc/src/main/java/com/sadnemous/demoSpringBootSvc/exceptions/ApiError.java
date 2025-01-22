package com.sadnemous.demoSpringBootSvc.exceptions;

public class ApiError {
    private int code;
    private String message;
    private String reason;
    private int type;

    public ApiError(int code, String message, String reason, int type) {
        this.code = code;
        this.message = message;
        this.reason = reason;
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
