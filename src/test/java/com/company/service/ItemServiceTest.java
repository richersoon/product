package com.company.service;

import com.company.Application;
import com.company.FongoConfig;
import com.company.dto.ItemCreateRequestDto;
import com.company.dto.ItemDto;
import com.company.dto.ItemUpdateRequestDto;
import com.company.exception.ProductNotFoundException;
import com.company.model.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class, FongoConfig.class})
public class ItemServiceTest {

    @Inject
    private MongoTemplate mongoTemplate;

    @Inject
    private ItemService unitUnderTest;

    private Item initItem;

    @Before
    public void setUp() {
        mongoTemplate.dropCollection(Item.class);

        ItemCreateRequestDto itemUpdateRequestDto = new ItemCreateRequestDto();
        itemUpdateRequestDto.setName("New task");
        itemUpdateRequestDto.setPriority(1);

        initItem = Item.create(itemUpdateRequestDto);
        mongoTemplate.insert(this.initItem);
    }

    @Test
    public void createSuccessfully() {
        final ItemCreateRequestDto expected = new ItemCreateRequestDto();
        expected.setName("Some task");
        expected.setPriority(2);
        final ItemDto actual = unitUnderTest.create(expected);

        assertNotNull(actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getPriority(), actual.getPriority());
    }

    @Test
	public void getSuccessfully() {
        final ItemDto actual = unitUnderTest.get(initItem.getId().toHexString());
        assertEquals(initItem.getId(), actual.getId());
        assertEquals(initItem.getName(), actual.getName());
        assertEquals(initItem.getPriority(), actual.getPriority());
    }

    @Test(expected = ProductNotFoundException.class)
    public void getShouldThrowProductNotFoundWhenItemNotExist() {
        final ItemDto actual = unitUnderTest.get("DUMMY");
        assertNull(actual);
    }

    @Test
    public void updateSuccessfully() {
        ItemUpdateRequestDto requestDto = new ItemUpdateRequestDto();
        requestDto.setId(initItem.getId().toHexString());
        requestDto.setName("Updated name");
        requestDto.setPriority(initItem.getPriority() + 1);

        final ItemDto actual = unitUnderTest.update(requestDto);
        assertEquals(requestDto.getId(), actual.getId());
        assertEquals(requestDto.getName(), actual.getName());
        assertEquals(requestDto.getPriority(), actual.getPriority());
    }

    @Test(expected = ProductNotFoundException.class)
    public void updateShouldThrowProductNotFoundWhenItemNotExist() {
        ItemUpdateRequestDto updateRequest = new ItemUpdateRequestDto();
        updateRequest.setId("DUMMY");
        updateRequest.setName("Updated name");
        updateRequest.setPriority(3);

        unitUnderTest.update(updateRequest);
    }

    @Test
    public void deleteSuccessfully() {
        unitUnderTest.delete(initItem.getId().toHexString());
    }

    @Test(expected = ProductNotFoundException.class)
    public void deleteShouldThrowProductNotFoundWhenItemNotExist() {
        unitUnderTest.delete(initItem.getId().toHexString());
        unitUnderTest.delete(initItem.getId().toHexString());
    }

    @Test
    public void getMaximumPrioritySuccessfully() {
        final Integer actual = unitUnderTest.getMaximumPriority();
        assertEquals(initItem.getPriority(), actual);
    }

    @Test
    public void getMaximumPriorityShouldReturnZeroWhenNoResult() {
        mongoTemplate.dropCollection(Item.class);

        final Integer actual = unitUnderTest.getMaximumPriority();
        assertEquals(0, actual.intValue());
    }
}
