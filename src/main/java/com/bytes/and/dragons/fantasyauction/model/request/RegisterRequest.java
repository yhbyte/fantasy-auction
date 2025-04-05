package com.bytes.and.dragons.fantasyauction.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @Size(min = 5, max = 50)
    @NotBlank
    private String username;

    @Size(min = 5, max = 255)
    @NotBlank
    private String email;

    @Size(max = 255)
    private String password;
}
