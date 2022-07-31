package com.edgsel.tuumtestassignment.controller.dto.response;

import com.edgsel.tuumtestassignment.controller.dto.enums.CurrencyDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@Schema(description = "Balance object")
public class BalanceDTO {

    @Schema(
        description = "Balance currency",
        example = "EUR",
        type = "enum"
    )
    private CurrencyDTO currency;

    @Schema(
        description = "Balance amount",
        example = "1.56",
        type = "double"
    )
    private BigDecimal amount;
}
