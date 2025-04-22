package com.bytes.and.dragons.fantasyauction.model.response;

import com.bytes.and.dragons.fantasyauction.model.dto.ItemDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {

    private List<ItemDto> items;

}
