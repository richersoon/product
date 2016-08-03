package com.company.exceptionmapper;

import com.company.exception.ApplicationStatus;
import com.company.exception.CustomException;
import com.company.exception.ErrorResponseBuilder;
import com.company.exception.ExceptionDetailResponseDto;
import com.company.exception.IntegrationApplicationStatus;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DefaultExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        ApplicationStatus applicationStatus = IntegrationApplicationStatus.UNKNOWN_ERROR;

        if(exception instanceof CustomException) {
            final CustomException custom = (CustomException) exception;
            applicationStatus = custom.getApplicationStatus();
        }

        final ExceptionDetailResponseDto exceptionDetailResponseDto = ErrorResponseBuilder
                .applicationStatus(applicationStatus)
                .build();

        final Response response = Response.status(applicationStatus.getHttpCode())
                .entity(exceptionDetailResponseDto).build();
        return response;
    }

}