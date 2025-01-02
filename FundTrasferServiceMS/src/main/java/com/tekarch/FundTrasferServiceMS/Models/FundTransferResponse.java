package com.tekarch.FundTrasferServiceMS.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "info")
@Data
public class FundTransferResponse {

    public FundTransferResponse() {
        this.transferId = transferId;
        this.status = status;
        this.initiatedAt = initiatedAt;
    }
    @Id
    @NotNull
    private String fromAccountId;
    private Long transferId;
    private String status;
    private String toAccountId;

    @NotNull
    private Long amount;
    private LocalDateTime initiatedAt;
    private boolean valid;
    private String scheduleId;
    private String frequency;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;

    private String message;


    @OneToMany(mappedBy = "fundTransferResponse")
    private List<FundTransferRequest> fundTransferRequest;

}
