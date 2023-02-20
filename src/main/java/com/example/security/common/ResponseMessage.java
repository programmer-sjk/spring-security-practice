package com.example.security.common;

public class ResponseMessage {
    private String message;

    public static ResponseMessage unauthorized() {
        return new ResponseMessage("인증이 필요합니다.");
    }

    private ResponseMessage() {}

    private ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
