package com.bytes.and.dragons.fantasyauction.mapper;

import com.bytes.and.dragons.fantasyauction.model.dto.ItemDto;
import com.bytes.and.dragons.fantasyauction.model.entity.Item;
import com.bytes.and.dragons.fantasyauction.model.request.CreateItemRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item toItemEntity(CreateItemRequest request);

    ItemDto toItemDto(Item item);

}
