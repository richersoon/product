package com.company.exception;

import javax.ws.rs.core.Response.Status;

/**
 * Created by richersoon on 8/3/16.
 */
public enum ProductApplicationStatus implements ApplicationStatus {

    ITEM_NOT_FOUND("ITEM_NOT_FOUND", "Item does not exists.", Status.NOT_FOUND);

    private String code;
    private String message;
    private Status httpCode;

    private ProductApplicationStatus(String code, String message, Status httpCode){
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
