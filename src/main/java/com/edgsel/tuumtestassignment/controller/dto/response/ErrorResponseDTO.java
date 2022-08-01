package com.edgsel.tuumtestassignment.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseDTO {

    private final String message;

    public ErrorResponseDTO(String message) {
        this.message = message;
    }
}
