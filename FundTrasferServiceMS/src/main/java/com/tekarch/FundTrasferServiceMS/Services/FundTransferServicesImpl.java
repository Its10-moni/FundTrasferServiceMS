package com.tekarch.FundTrasferServiceMS.Services;

import com.tekarch.FundTrasferServiceMS.Models.FundTransferRequest;
import com.tekarch.FundTrasferServiceMS.Models.FundTransferResponse;
import com.tekarch.FundTrasferServiceMS.Repository.FundTransferRepository;
import com.tekarch.FundTrasferServiceMS.Services.Interfaces.FundTransferService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
@Service
public class FundTransferServicesImpl implements FundTransferService {
    private final FundTransferRepository fundTransferRepository;
    private final Map<String, FundTransferResponse> transfers = new HashMap<>();

    public FundTransferServicesImpl(FundTransferRepository fundTransferRepository) {
        this.fundTransferRepository = fundTransferRepository;
    }


    public FundTransferResponse initiateTransfer(FundTransferRequest transferRequest) {


        UUID uuid = UUID.randomUUID();
        Long transferId = uuid.getMostSignificantBits();
        String status = "initiated";
        LocalDateTime initiatedAt = LocalDateTime.now();

        System.out.println("Initiating transfer from " + transferRequest.getFromAccountId() +
                " to " + transferRequest.getToAccountId() + " amount: " + transferRequest.getAmount());
        return new FundTransferResponse(transferId, status, initiatedAt);
       /* FundTransferResponse transferResponse = new FundTransferResponse(transferId, status, initiatedAt);
        transfers.put(transferId, transferResponse);

        // Return the transfer response
        return transferResponse;*/
    }
    public Optional<FundTransferResponse> getTransferStatus(Long transferId) {
return fundTransferRepository.findById(transferId);

    }

    // Get all transfers (for listing purposes)
    public   Iterable<FundTransferResponse> getAllTransfers() {
        return fundTransferRepository.findAll();
    }
}
