package com.bytes.and.dragons.fantasyauction.unit.service;

import static com.bytes.and.dragons.fantasyauction.util.TestData.TEST_ID;
import static com.bytes.and.dragons.fantasyauction.util.TestData.getCreateItemRequest;
import static com.bytes.and.dragons.fantasyauction.util.TestData.getItem;
import static com.bytes.and.dragons.fantasyauction.util.TestData.getUser;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bytes.and.dragons.fantasyauction.mapper.ItemMapper;
import com.bytes.and.dragons.fantasyauction.model.dto.ItemDto;
import com.bytes.and.dragons.fantasyauction.model.entity.Item;
import com.bytes.and.dragons.fantasyauction.model.response.ItemResponse;
import com.bytes.and.dragons.fantasyauction.repository.ItemRepository;
import com.bytes.and.dragons.fantasyauction.service.ItemService;
import com.bytes.and.dragons.fantasyauction.service.UserService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;
    @Mock
    private ItemMapper itemMapper;

    @Mock
    private UserService userService;

    @InjectMocks
    private ItemService itemService;

    @Test
    void getItems_shouldReturnEmptyList_whenNoItemsPresentOnPage() {
        // given
        when(itemRepository.findAllByUserId(any(), any(Pageable.class))).thenReturn(Page.empty());

        // when
        ItemResponse response = itemService.getItems(Pageable.unpaged(), TEST_ID);

        // then
        assertNotNull(response);
        assertTrue(response.getItems().isEmpty());
    }

    @Test
    void getItems_shouldReturnItems_whenItemsPresentOnPage() {
        // given
        List<Item> items = List.of(getItem());
        when(itemRepository.findAllByUserId(any(), any(Pageable.class))).thenReturn(
                new PageImpl<>(items, Pageable.unpaged(), items.size()));
        when(itemMapper.toItemDto(any())).thenReturn(new ItemDto());

        // when
        ItemResponse response = itemService.getItems(Pageable.unpaged(), TEST_ID);

        // then
        assertNotNull(response);
        assertEquals(items.size(), response.getItems().size());
    }

    @Test
    void createItem_shouldCreateItem_whenRequestIsValid() {
        // given
        when(userService.getUserById(any())).thenReturn(getUser());
        when(itemMapper.toItemEntity(any())).thenReturn(getItem());

        // when
        assertDoesNotThrow(() -> itemService.createItem(getCreateItemRequest(), TEST_ID));

        // then
        verify(itemRepository).save(any(Item.class));
    }

}
