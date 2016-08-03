package com.company.exception;

import javax.ws.rs.core.Response;

/**
 * Created by richersoon on 8/3/16.
 */
public class ProductNotFoundException extends CustomException {

    public ProductNotFoundException() {
        super(ProductApplicationStatus.ITEM_NOT_FOUND, Response.Status.NOT_FOUND);
    }
}
