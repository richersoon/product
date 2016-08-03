package com.company.service;

import com.company.dto.ItemCreateRequestDto;
import com.company.dto.ItemDto;
import com.company.dto.ItemUpdateRequestDto;
import com.company.exception.ProductNotFoundException;
import com.company.model.Item;
import com.company.repo.ItemRepository;
import ma.glasnost.orika.MapperFacade;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ItemService {

    private MapperFacade mapperFacade;
    private ItemRepository itemRepository;

    @Inject
    public ItemService(ItemRepository itemRepository, MapperFacade mapperFacade) {
        this.itemRepository = itemRepository;
        this.mapperFacade = mapperFacade;
    }

    public ItemDto create(final ItemCreateRequestDto requestDto) {
        if(null == requestDto.getPriority()) {
            requestDto.setPriority(getMaximumPriority() + 1);
        }

        Item item = Item.create(requestDto);
        item = itemRepository.insert(item);
        final ItemDto dto = mapperFacade.map(item, ItemDto.class);
        return dto;
    }

    public ItemDto get(final String id) {
        final Item item = itemRepository.findOne(id);

        if(null == item) {
            throw new ProductNotFoundException();
        }

        final ItemDto dto = mapperFacade.map(item, ItemDto.class);
        return dto;
    }

    public ItemDto update(final ItemUpdateRequestDto requestDto) {
        if(null == requestDto.getPriority()) {
            requestDto.setPriority(getMaximumPriority() + 1);
        }

        Item item = itemRepository.findOne(requestDto.getId());

        if(null == item) {
            throw new ProductNotFoundException();
        }

        item.update(requestDto);
        item = itemRepository.save(item);
        final ItemDto dto = mapperFacade.map(item, ItemDto.class);
        return dto;
    }

    public void delete(final String id) {
        Item item = itemRepository.findOne(id);

        if(null == item) {
            throw new ProductNotFoundException();
        }

        itemRepository.delete(id);
    }

    public Integer getMaximumPriority() {
        final PageRequest request = new PageRequest(0, 1, new Sort(Sort.Direction.DESC, "priority"));
        final Integer priority = itemRepository.findAll(request).getContent().stream()
                .findFirst()
                .map(Item::getPriority)
                .orElse(0);

        return priority;
    }
}