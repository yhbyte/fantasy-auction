package com.bytes.and.dragons.fantasyauction.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {

    @Size(min = 5, max = 50)
    @NotBlank
    private String username;

    @Size(min = 8, max = 255)
    @NotBlank
    private String password;
}
