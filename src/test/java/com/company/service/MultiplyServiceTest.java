package com.company.service;

import com.company.Application;
import com.company.dto.MultiplyDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
public class MultiplyServiceTest {

    @Inject
    private MultiplyService unitUnderTest;

    @Test
    public void multiplySuccessfullyWhenBothNumberIsPositive() {
        final MultiplyDto multiplyDto = unitUnderTest.multiplyService(3, 3);
        assertEquals(9, multiplyDto.getResult().intValue());
    }

    @Test
    public void multiplySuccessfullyWhenBothNumberIsNegative() {
        final MultiplyDto multiplyDto = unitUnderTest.multiplyService(-3, -3);
        assertEquals(9, multiplyDto.getResult().intValue());
    }

    @Test
    public void multiplySuccessfullyWhenDifferentSignedNumber() {
        final MultiplyDto multiplyDto = unitUnderTest.multiplyService(3, -3);
        assertEquals(-9, multiplyDto.getResult().intValue());
    }
}
