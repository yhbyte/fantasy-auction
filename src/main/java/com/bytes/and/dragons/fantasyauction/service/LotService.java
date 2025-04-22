package com.bytes.and.dragons.fantasyauction.service;

import static com.bytes.and.dragons.fantasyauction.model.enums.LotStatus.DRAFT;

import com.bytes.and.dragons.fantasyauction.aop.InvocationLog;
import com.bytes.and.dragons.fantasyauction.mapper.LotMapper;
import com.bytes.and.dragons.fantasyauction.model.dto.LotDto;
import com.bytes.and.dragons.fantasyauction.model.entity.Item;
import com.bytes.and.dragons.fantasyauction.model.entity.Lot;
import com.bytes.and.dragons.fantasyauction.model.entity.User;
import com.bytes.and.dragons.fantasyauction.model.request.CreateLotRequest;
import com.bytes.and.dragons.fantasyauction.model.response.LotResponse;
import com.bytes.and.dragons.fantasyauction.repository.LotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LotService {

    private final LotRepository repository;
    private final LotMapper lotMapper;

    private final ItemService itemService;
    private final UserService userService;

    @Transactional
    @InvocationLog
    public LotResponse getLots(Pageable pageable) {
        Page<Lot> lotsPage = repository.findAll(pageable);
        log.info("Found {} lots", lotsPage.getNumberOfElements());

        Page<LotDto> lotsDtoPage = lotsPage.map(lotMapper::toLotDto);
        return new LotResponse(lotsDtoPage.getContent());
    }

    @Transactional
    @InvocationLog
    public void createLot(CreateLotRequest request, Long userId) {
        if (repository.existsByItemId(request.getItemId())) {
            log.warn("Lot for item with id [{}] is already created", request.getItemId());
            throw new IllegalStateException("Cannot create lot with the item because it already exists");
        }

        User user = userService.getUserById(userId);
        Item item = itemService.getItemById(request.getItemId());

        Lot lot = lotMapper.toLotEntity(request);
        lot.setItem(item);
        lot.setStatus(DRAFT);
        user.addLot(lot);

        lot = repository.save(lot);
        log.info("Created new lot {}", lot);
    }

}
