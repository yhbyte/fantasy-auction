package com.bytes.and.dragons.fantasyauction.service;

import static com.bytes.and.dragons.fantasyauction.model.enums.LotStatus.DRAFT;

import com.bytes.and.dragons.fantasyauction.mapper.ItemMapper;
import com.bytes.and.dragons.fantasyauction.mapper.LotMapper;
import com.bytes.and.dragons.fantasyauction.model.entity.Item;
import com.bytes.and.dragons.fantasyauction.model.entity.Lot;
import com.bytes.and.dragons.fantasyauction.model.entity.User;
import com.bytes.and.dragons.fantasyauction.model.request.CreateLotRequest;
import com.bytes.and.dragons.fantasyauction.model.response.LotResponse;
import com.bytes.and.dragons.fantasyauction.repository.LotRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LotService {

    private final LotRepository lotRepository;
    private final LotMapper lotMapper;
    private final ItemMapper itemMapper;
    private final UserService userService;

    @Transactional
    public List<LotResponse> getLots() {
        return lotRepository.findAll().stream()
                .map(lotMapper::toLotResponse)
                .toList();
    }

    @Transactional
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
