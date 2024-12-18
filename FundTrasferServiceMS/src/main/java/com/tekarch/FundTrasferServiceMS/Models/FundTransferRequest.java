package com.tekarch.FundTrasferServiceMS.Models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name = "info")
@Data
public class FundTransferRequest {

    @Id
    @NotNull
    private String fromAccountId;
    @NotNull
    private String toAccountId;

    @NotNull
    private BigDecimal amount;

    private String description;

    @ManyToOne
    @JoinColumn(name = "fund_transfer_response_id")
    private FundTransferResponse fundTransferResponse;
    //private String transferId;
   //private String status;
    //private LocalDateTime initiatedAt;

}

