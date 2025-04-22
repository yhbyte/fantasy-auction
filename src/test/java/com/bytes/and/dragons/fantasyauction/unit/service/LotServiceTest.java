package com.bytes.and.dragons.fantasyauction.unit.service;

import static com.bytes.and.dragons.fantasyauction.util.TestData.TEST_ID;
import static com.bytes.and.dragons.fantasyauction.util.TestData.getCreateLotRequest;
import static com.bytes.and.dragons.fantasyauction.util.TestData.getItem;
import static com.bytes.and.dragons.fantasyauction.util.TestData.getLot;
import static com.bytes.and.dragons.fantasyauction.util.TestData.getUser;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bytes.and.dragons.fantasyauction.mapper.LotMapper;
import com.bytes.and.dragons.fantasyauction.model.dto.LotDto;
import com.bytes.and.dragons.fantasyauction.model.entity.Item;
import com.bytes.and.dragons.fantasyauction.model.entity.Lot;
import com.bytes.and.dragons.fantasyauction.model.response.LotResponse;
import com.bytes.and.dragons.fantasyauction.repository.LotRepository;
import com.bytes.and.dragons.fantasyauction.service.ItemService;
import com.bytes.and.dragons.fantasyauction.service.LotService;
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
class LotServiceTest {

    @Mock
    private LotRepository lotRepository;
    @Mock
    private LotMapper lotMapper;

    @Mock
    private ItemService itemService;
    @Mock
    private UserService userService;

    @InjectMocks
    private LotService lotService;

    @Test
    void getLots_shouldReturnEmptyLots_whenNoLotsPresentOnPage() {
        // given
        when(lotRepository.findAll(any(Pageable.class))).thenReturn(Page.empty());

        // when
        LotResponse response = lotService.getLots(Pageable.unpaged());

        // then
        assertNotNull(response);
        assertTrue(response.getLots().isEmpty());
    }

    @Test
    void getLots_shouldReturnLots_whenLotsPresentOnPage() {
        // given
        List<Lot> lots = List.of(getLot());
        when(lotRepository.findAll(any(Pageable.class))).thenReturn(
                new PageImpl<>(lots, Pageable.unpaged(), lots.size()));
        when(lotMapper.toLotDto(any())).thenReturn(new LotDto());

        // when
        LotResponse response = lotService.getLots(Pageable.unpaged());

        // then
        assertNotNull(response);
        assertEquals(lots.size(), response.getLots().size());
    }

    @Test
    void createLot_shouldThrowException_whenLotAlreadyExists() {
        // given
        Item item = getItem();
        item.setLot(getLot());
        when(lotRepository.existsByItemId(any())).thenReturn(true);

        // then
        assertThrows(IllegalStateException.class, () -> lotService.createLot(getCreateLotRequest(), TEST_ID));
    }

    @Test
    void createLot_shouldCreateLot_whenRequestIsValid() {
        // given
        Item item = getItem();
        when(lotRepository.existsByItemId(any())).thenReturn(false);
        when(userService.getUserById(any())).thenReturn(getUser());
        when(itemService.getItemById(any())).thenReturn(item);
        when(lotMapper.toLotEntity(any())).thenReturn(getLot());

        // when
        assertDoesNotThrow(() -> lotService.createLot(getCreateLotRequest(), TEST_ID));

        // then
        verify(lotRepository).save(any(Lot.class));
    }

}
