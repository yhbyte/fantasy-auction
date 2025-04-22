package com.bytes.and.dragons.fantasyauction.mapper;

import com.bytes.and.dragons.fantasyauction.model.dto.LotDto;
import com.bytes.and.dragons.fantasyauction.model.entity.Lot;
import com.bytes.and.dragons.fantasyauction.model.request.CreateLotRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LotMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "item.name", target = "itemName")
    @Mapping(source = "item.type", target = "itemType")
    LotDto toLotDto(Lot lot);

    @Mapping(source = "price", target = "initialPrice")
    @Mapping(source = "endTime", target = "expiresAt")
    Lot toLotEntity(CreateLotRequest request);

}
