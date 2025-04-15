package com.bytes.and.dragons.fantasyauction.mapper;

import com.bytes.and.dragons.fantasyauction.model.dto.LotDto;
import com.bytes.and.dragons.fantasyauction.model.entity.Lot;
import com.bytes.and.dragons.fantasyauction.model.request.CreateLotRequest;
import java.time.ZonedDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface LotMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "item.name", target = "itemName")
    @Mapping(source = "item.type", target = "itemType")
    LotDto toLotDto(Lot lot);

    @Mapping(source = "price", target = "detail.initialPrice")
    @Mapping(source = "durationDays", target = "detail.expiresAt", qualifiedByName = "convertDaysToDateTime")
    Lot toLotEntity(CreateLotRequest request);

    @Named("convertDaysToDateTime")
    default ZonedDateTime convertDaysToDateTime(Integer durationDays) {
        return ZonedDateTime.now().plusDays(durationDays);
    }

}
