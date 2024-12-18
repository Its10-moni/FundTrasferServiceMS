package com.tekarch.FundTrasferServiceMS.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "info")
@Data
public class FundTransferResponse {

    public FundTransferResponse(Long transferId, String status, LocalDateTime initiatedAt) {
        this.transferId = transferId;
        this.status = status;
        this.initiatedAt = initiatedAt;
    }
    @Id
    @NotNull
    private String fromAccountId;
    private Long transferId;
    private String status;
    private LocalDateTime initiatedAt;


    @OneToMany(mappedBy = "fundTransferResponse")
    private List<FundTransferRequest> fundTransferRequest;

}
