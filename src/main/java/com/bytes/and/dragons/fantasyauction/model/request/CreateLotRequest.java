package com.bytes.and.dragons.fantasyauction.model.request;

import com.bytes.and.dragons.fantasyauction.model.dto.ItemDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLotRequest {

    @NotNull
    @Valid
    private ItemDto item;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Integer durationDays;

}
