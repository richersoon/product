package com.company.exception;

import javax.ws.rs.core.Response.Status;

/**
 * Created by richersoon on 8/3/16.
 */
public enum IntegrationApplicationStatus implements ApplicationStatus {

    VALIDATION_ERROR("VALIDATION_ERROR", "One or more fields are missing or is invalid.", Status.BAD_REQUEST),
    UNKNOWN_ERROR("UNKNOWN_ERROR", "Unexpected error", Status.INTERNAL_SERVER_ERROR);

    private String code;
    private String message;
    private Status httpCode;

    private IntegrationApplicationStatus(String code, String message, Status httpCode){
        this.code = code;
        this.message = message;
        this.httpCode = httpCode;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Status getHttpCode() {
        return httpCode;
    }
}
