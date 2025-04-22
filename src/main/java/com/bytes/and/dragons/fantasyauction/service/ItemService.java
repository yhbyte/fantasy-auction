package com.bytes.and.dragons.fantasyauction.service;

import com.bytes.and.dragons.fantasyauction.aop.InvocationLog;
import com.bytes.and.dragons.fantasyauction.exception.NotFoundException;
import com.bytes.and.dragons.fantasyauction.mapper.ItemMapper;
import com.bytes.and.dragons.fantasyauction.model.dto.ItemDto;
import com.bytes.and.dragons.fantasyauction.model.entity.Item;
import com.bytes.and.dragons.fantasyauction.model.entity.User;
import com.bytes.and.dragons.fantasyauction.model.request.CreateItemRequest;
import com.bytes.and.dragons.fantasyauction.model.response.ItemResponse;
import com.bytes.and.dragons.fantasyauction.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final UserService userService;

    @Transactional
    @InvocationLog
    public ItemResponse getItems(Pageable pageable, Long userId) {
        Page<Item> itemsPage = itemRepository.findAllByUserId(userId, pageable);
        log.info("Found {} items", itemsPage.getNumberOfElements());

        Page<ItemDto> itemsDtoPage = itemsPage.map(itemMapper::toItemDto);
        return new ItemResponse(itemsDtoPage.getContent());
    }

    @Transactional
    @InvocationLog
    public void createItem(CreateItemRequest request, Long userId) {
        User user = userService.getUserById(userId);
        Item item = itemMapper.toItemEntity(request);
        user.addItem(item);

        item = itemRepository.save(item);
        log.info("Created new item {}", item);
    }

    @Transactional
    @InvocationLog
    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Item with id %s not found".formatted(itemId)));
    }

}
