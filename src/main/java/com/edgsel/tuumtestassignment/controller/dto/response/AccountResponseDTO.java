package com.edgsel.tuumtestassignment.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Response object for creating banking account")
public class AccountResponseDTO {

    @Schema(
        description = "Unique ID representing a account",
        example = "1",
        type = "long"
    )
    private long id;

    @Schema(
        description = "Unique ID representing a customer",
        example = "1234-uuid-1234",
        type = "string"
    )
    private String customerId;

    @Schema(
        description = "A list of currencies of the account and balances",
        type = "array"
    )
    private List<BalanceDTO> balances;
}
