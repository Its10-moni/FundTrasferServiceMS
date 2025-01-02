package com.tekarch.FundTrasferServiceMS.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RemainingResponseDTO {

    private String fromAccountId;
    private double dailyLimitRemaining;
    private double weeklyLimitRemaining;
    private double monthlyLimitRemaining;

}
