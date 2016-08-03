package com.company.exception;

import javax.ws.rs.core.Response.Status;

public abstract class CustomException extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -902949262372207931L;

    /** The exception detail. */
    private ApplicationStatus applicationStatus;
    private Status status;

    public CustomException(ApplicationStatus applicationStatus, Status status) {
        this.applicationStatus = applicationStatus;
        this.status = status;
    }

    /**
     * Return the application error code associated with this exception.
     *
     * @return the application error code associated with this exception.
     */
    public String getErrorCode() {
        return applicationStatus.getCode();
    }

    /**
     * Return the application error message associated with this exception.
     * @return the application error message associated with this exception.
     */
    public String getErrorMessage() {
        return applicationStatus.getMessage();
    }

    /**
     * Return the application status associated with this exception.
     * @return the application status associated with this exception.
     */
    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }
}
