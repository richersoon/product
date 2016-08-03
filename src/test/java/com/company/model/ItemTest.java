package com.company.model;

import com.company.dto.ItemCreateRequestDto;
import com.company.dto.ItemUpdateRequestDto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by richersoon on 8/3/16.
 */
public class ItemTest {

    private Item initItem;

    @Before
    public void setUp() {
        ItemCreateRequestDto requestDto = new ItemCreateRequestDto();
        requestDto.setName("Init task");
        requestDto.setPriority(1);

        initItem = Item.create(requestDto);
    }

    @Test
    public void createSuccessfully() {
        ItemCreateRequestDto requestDto = new ItemCreateRequestDto();
        requestDto.setName("Some important task");
        requestDto.setPriority(2);

        final Item actual = Item.create(requestDto);
        assertNotNull(actual.getId());
        assertEquals(requestDto.getName(), actual.getName());
        assertEquals(requestDto.getPriority(), actual.getPriority());
    }

    @Test
    public void updateSuccessfully() throws Exception {
        ItemUpdateRequestDto requestDto = new ItemUpdateRequestDto();
        requestDto.setName("Updated name");
        requestDto.setPriority(initItem.getPriority() + 1);

        final Item actual = initItem.update(requestDto);
        assertEquals(initItem.getId(), actual.getId());
        assertEquals(initItem.getName(), actual.getName());
        assertEquals(initItem.getPriority(), actual.getPriority());
    }
}