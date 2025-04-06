package com.bytes.and.dragons.fantasyauction.model.response;

import lombok.Data;

@Data
public class ErrorResponse {

    private Integer code;
    private String message;
    private String description;

}
