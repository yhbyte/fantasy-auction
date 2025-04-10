package com.bytes.and.dragons.fantasyauction.model.response;

import com.bytes.and.dragons.fantasyauction.model.enums.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LotResponse {

    private Long id;
    private String itemName;
    private ItemType itemType;

}
