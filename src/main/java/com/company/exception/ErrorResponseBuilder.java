package com.company.exception;

import java.util.Map;

public class ErrorResponseBuilder {

    private String errorCode;
    private String errorMessage;
    private Map<String, String> details;

    /**
     * Sets the error code.
     *
     * @param newErrorCode the error code.
     * @return this.
     */
    public static ErrorResponseBuilder errorCode(String newErrorCode) {
        ErrorResponseBuilder errorResponseBuilder = new ErrorResponseBuilder();
        errorResponseBuilder.errorCode = newErrorCode;
        return errorResponseBuilder;
    }

    /**
     * Sets the applicationStatus.
     *
     * @param applicationStatus the applicationStatus.
     * @return this.
     */
    public static ErrorResponseBuilder applicationStatus(ApplicationStatus applicationStatus) {
        ErrorResponseBuilder errorResponseBuilder = new ErrorResponseBuilder();
        errorResponseBuilder.errorCode = applicationStatus.getCode();
        errorResponseBuilder.errorMessage = applicationStatus.getMessage();
        return errorResponseBuilder;
    }

    /**
     * Sets the errorMessage.
     *
     * @param newErrorMessage the errorMessage.
     * @return this.
     */
    public ErrorResponseBuilder errorMessage(String newErrorMessage) {
        this.errorMessage = newErrorMessage;
        return this;
    }

    /**
     * Sets the details.
     *
     * @param newDetails the details.
     * @return this.
     */
    public ErrorResponseBuilder details(Map<String, String> newDetails) {
        this.details = newDetails;
        return this;
    }

    /**
     * Return a new instance of {@link ExceptionDetailResponseDto}.
     *
     * @return see {@link ExceptionDetailResponseDto}.
     */
    public ExceptionDetailResponseDto build() {
        return new ExceptionDetailResponseDto() {

            @Override
            public String getErrorMessage() {
                return errorMessage;
            }

            @Override
            public String getErrorCode() {
                return errorCode;
            }

            @Override
            public Map<String, String> getDetails() {
                return details;
            }
        };
    }

}