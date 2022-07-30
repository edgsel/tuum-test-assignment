package com.edgsel.tuumtestassignment.controller.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class AccountResponseDTO {

    private long id;

    private String customerId;

    private List<BalanceDTO> balances;
}
