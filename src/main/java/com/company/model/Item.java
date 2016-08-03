package com.company.model;

import com.company.dto.ItemCreateRequestDto;
import com.company.dto.ItemUpdateRequestDto;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Item.COLLECTION_NAME)
public class Item {

    public static final String COLLECTION_NAME = "tasks";

    @Id
    private ObjectId id;
	private String name;
	private Integer priority;

    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPriority() {
        return priority;
    }

    public static Item create(final ItemCreateRequestDto requestDto) {
        Item item = new Item();
        item.id = new ObjectId();
        item.name = requestDto.getName();
        item.priority = requestDto.getPriority();

        return item;
    }

    public Item update(final ItemUpdateRequestDto requestDto) {
        name = requestDto.getName();
        priority = requestDto.getPriority();
        return this;
    }
}
