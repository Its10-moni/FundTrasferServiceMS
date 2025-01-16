package com.tekarch.FundTrasferServiceMS.Services.Interfaces;

//import com.tekarch.FundTrasferServiceMS.Models.FundTransferRequest;
import com.tekarch.FundTrasferServiceMS.Models.FundTransferResponse;

import java.util.Optional;

public interface FundTransferService {
    public FundTransferResponse initiateTransfer(FundTransferResponse transferRequest);
    Iterable<FundTransferResponse> getAllTransfers();

    Optional<FundTransferResponse> getTransferStatus(Long transferId);
   // public FundTransferResponse validateTransactionLimit(String accountId, FundTransferRequest requestDTO);
  //  public RemainingResponseDTO getRemainingLimits(String accountId);
    public FundTransferResponse createScheduledTransfer(FundTransferResponse requestDTO);
    public FundTransferResponse updateScheduledTransfer(String scheduleId, FundTransferResponse requestDTO);
    public FundTransferResponse getScheduledTransfer(String scheduleId);
    public FundTransferResponse cancelScheduledTransfer(String scheduleId);
}
