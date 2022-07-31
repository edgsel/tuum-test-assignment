package com.edgsel.tuumtestassignment.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
public class AccountResponseDTO {

    private long id;

    private String customerId;

    private List<BalanceDTO> balances;
}
