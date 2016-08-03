package com.company.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public interface ExceptionDetailResponseDto {

    /**
     * The error code associated with this status.
     *
     * @return the error code
     */
    @JsonProperty("code")
    String getErrorCode();

    /**
     * The message associated with this status.
     *
     * @return the message
     */
    @JsonProperty("message")
    String getErrorMessage();

    /**
     * Additional error details.
     *
     * @return the error details
     */
    @JsonProperty("details")
    Map<String, String> getDetails();
}