package com.bytes.and.dragons.fantasyauction.service;

import static com.bytes.and.dragons.fantasyauction.model.enums.LotStatus.DRAFT;

import com.bytes.and.dragons.fantasyauction.aop.InvocationLog;
import com.bytes.and.dragons.fantasyauction.mapper.ItemMapper;
import com.bytes.and.dragons.fantasyauction.mapper.LotMapper;
import com.bytes.and.dragons.fantasyauction.model.dto.LotDto;
import com.bytes.and.dragons.fantasyauction.model.entity.Item;
import com.bytes.and.dragons.fantasyauction.model.entity.Lot;
import com.bytes.and.dragons.fantasyauction.model.entity.User;
import com.bytes.and.dragons.fantasyauction.model.request.CreateLotRequest;
import com.bytes.and.dragons.fantasyauction.model.response.LotResponse;
import com.bytes.and.dragons.fantasyauction.repository.LotRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LotService {

    private final LotRepository lotRepository;
    private final LotMapper lotMapper;
    private final ItemMapper itemMapper;
    private final UserService userService;

    @Transactional
    @InvocationLog
    public LotResponse getLots() {
        List<LotDto> lots = lotRepository.findAll().stream()
                .map(lotMapper::toLotDto)
                .toList();

        log.info("Found {} lots", lots.size());
        return new LotResponse(lots);
    }

    @Transactional
    @InvocationLog
    public void createLot(CreateLotRequest request, Long userId) {
        User user = userService.getUserById(userId);

        Item item = itemMapper.toItemEntity(request.getItem());
        Lot lot = lotMapper.toLotEntity(request);
        lot.setItem(item);
        lot.getDetail().setStatus(DRAFT);

        // TODO separate item creation from lot creation
        user.addItem(item);
        user.addLot(lot);

        lotRepository.save(lot);
    }

}
