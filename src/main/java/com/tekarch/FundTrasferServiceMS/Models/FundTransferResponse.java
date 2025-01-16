package com.tekarch.FundTrasferServiceMS.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "fundservice")
@Data
public class FundTransferResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transferId;
    @NotNull
    private Long fromaccountId;
    @NotNull
    private  Long toaccountId;
    @NotNull
    private Double amount;
    private String status;
    @CreationTimestamp
    private LocalDateTime initiated_at;
    @UpdateTimestamp
    private LocalDateTime completed_at;

    private String scheduleId;
    private String frequency;
    private String description;
    @CreationTimestamp
    private LocalDate startDate;
    @UpdateTimestamp
    private LocalDate endDate;


    // private String message;


   /* @OneToMany(mappedBy = "fundTransferResponse")
    private List<FundTransferRequest> fundTransferRequest;

     private double dailyLimitRemaining;
    private double weeklyLimitRemaining;
    private double monthlyLimitRemaining;*/

}
