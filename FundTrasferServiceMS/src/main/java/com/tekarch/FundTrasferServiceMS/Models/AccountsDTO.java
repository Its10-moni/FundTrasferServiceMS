package com.tekarch.FundTrasferServiceMS.Models;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AccountsDTO {

    private Integer accountId;
    private String username;
    private String accountType;
    private Double balance;
    private String currency;
    private Long userid;
    private String Email;
    private Long MobileNumber;
    private String Address;
}
