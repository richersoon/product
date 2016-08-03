package com.company.service;

import com.company.dto.MultiplyDto;

import javax.inject.Named;

/**
 * Created by richersoon on 8/3/16.
 */
@Named
public class MultiplyService {

    public MultiplyDto multiplyService(final Integer numA, final Integer numB) {
        final Integer result = numA * numB;

        MultiplyDto multiplyDto = new MultiplyDto();
        multiplyDto.setResult(result);
        return multiplyDto;
    }
}
