package com.edgsel.tuumtestassignment.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseDTO {

    @Schema(
        description = "Message describing errors related to the payload",
        example = "Lorum Ipsum",
        type = "string"
    )
    private final String message;

    public ErrorResponseDTO(String message) {
        this.message = message;
    }
}
