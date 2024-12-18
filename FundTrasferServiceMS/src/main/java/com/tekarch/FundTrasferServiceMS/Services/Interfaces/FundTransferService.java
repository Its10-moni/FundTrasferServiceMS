package com.tekarch.FundTrasferServiceMS.Services.Interfaces;

import com.tekarch.FundTrasferServiceMS.Models.FundTransferRequest;
import com.tekarch.FundTrasferServiceMS.Models.FundTransferResponse;

import java.util.Map;
import java.util.Optional;

public interface FundTransferService {
    public FundTransferResponse initiateTransfer(FundTransferRequest transferRequest);
    Iterable<FundTransferResponse> getAllTransfers();

    Optional<FundTransferResponse> getTransferStatus(Long transferId);
}
