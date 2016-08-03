package com.company.exceptionmapper;

import com.company.exception.ApplicationStatus;
import com.company.exception.ErrorResponseBuilder;
import com.company.exception.ExceptionDetailResponseDto;
import com.company.exception.IntegrationApplicationStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        Map<String, String> messages = getExceptionMessage(exception);
        final ApplicationStatus applicationStatus = IntegrationApplicationStatus.VALIDATION_ERROR;
        ExceptionDetailResponseDto errorResponse = ErrorResponseBuilder
                .applicationStatus(applicationStatus)
                .details(messages)
                .build();
        
        return Response.status(applicationStatus.getHttpCode()).entity(errorResponse).build();
    }

    private Map<String, String> getExceptionMessage(ConstraintViolationException exception) {
        
        Set<ConstraintViolation<?>> interpolatedMessage = exception.getConstraintViolations();
        
        Map<String, String> messages = new HashMap<>();

        for (ConstraintViolation<?> con : interpolatedMessage) {

            String propertyPath = con.getPropertyPath().toString();
            int index = propertyPath.lastIndexOf(".") + 1;
            String field = propertyPath.substring(index, propertyPath.length());
            String message = con.getMessage();
            messages.put(field, message);
        }
        return messages;
    }
}
