package com.company.exception;

import javax.ws.rs.core.Response.Status;

public interface ApplicationStatus {

    /**
     * The code associated with this status.
     *
     * @return the code
     */
    String getCode();

    /**
     * The message associated with this status.
     *
     * @return the message
     */
    String getMessage();

    /**
     * The http code associated wit this status.
     *
     * @return the http code
     */
    Status getHttpCode();
}