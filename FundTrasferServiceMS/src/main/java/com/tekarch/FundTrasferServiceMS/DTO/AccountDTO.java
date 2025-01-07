package com.tekarch.FundTrasferServiceMS.DTO;

import lombok.Data;

@Data
public class AccountDTO {
    private Long accountId;
    private String accountNumber;
    private String accountType;
    private Double balance;
    private String currency;
}
