package com.example.walletApi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionUpdateDTO {
    private UUID walletId;
    private String operationType; // DEPOSIT or WITHDRAW
    private BigDecimal amount;
    public enum OperationType {
        DEPOSIT,
        WITHDRAW
    }
}
