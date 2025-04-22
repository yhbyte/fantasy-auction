package com.bytes.and.dragons.fantasyauction.model.request;

import com.bytes.and.dragons.fantasyauction.model.enums.ItemType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateItemRequest {

    @NotBlank
    private String name;
    @NotNull
    private ItemType type;

}
