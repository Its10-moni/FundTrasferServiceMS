package com.tekarch.FundTrasferServiceMS.Repository;

import com.tekarch.FundTrasferServiceMS.Models.FundTransferRequest;
import com.tekarch.FundTrasferServiceMS.Models.FundTransferResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FundTransferRepository extends JpaRepository<FundTransferResponse,Long> {

}