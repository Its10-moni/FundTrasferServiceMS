package com.tekarch.FundTrasferServiceMS.Services;

import com.tekarch.FundTrasferServiceMS.Models.FundTransferRequest;
import com.tekarch.FundTrasferServiceMS.Models.FundTransferResponse;
import com.tekarch.FundTrasferServiceMS.Models.RemainingResponseDTO;
import com.tekarch.FundTrasferServiceMS.Repository.FundTransferRepository;
import com.tekarch.FundTrasferServiceMS.Services.Interfaces.FundTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
@Service
public class FundTransferServicesImpl implements FundTransferService {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${account.service.url}")
    private static String Account_URL;

    @Autowired
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
        return new FundTransferResponse();
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
    public FundTransferResponse validateTransactionLimit(String accountId, FundTransferRequest requestDTO) {
        String url = String.format("%s/accounts/%s/limits/validate-transaction?amount=%f", Account_URL, accountId, requestDTO.getAmount());
        return restTemplate.getForObject(url, FundTransferResponse.class);
    }

    public RemainingResponseDTO getRemainingLimits(String accountId) {
        String url = String.format("%s/accounts/%s/transaction-limits", Account_URL, accountId);
        return restTemplate.getForObject(url, RemainingResponseDTO.class);
    }
    public FundTransferResponse createScheduledTransfer(FundTransferResponse requestDTO) {
        String scheduleId = UUID.randomUUID().toString();
        FundTransferResponse responseDTO = new FundTransferResponse();
        responseDTO.setScheduleId(scheduleId);
        responseDTO.setStatus("Scheduled");

        responseDTO.setFromAccountId(requestDTO.getFromAccountId());
        responseDTO.setToAccountId(requestDTO.getToAccountId());
        responseDTO.setAmount(requestDTO.getAmount());
        responseDTO.setFrequency(requestDTO.getFrequency());
        responseDTO.setStartDate(requestDTO.getStartDate());
        responseDTO.setEndDate(requestDTO.getEndDate());
        responseDTO.setDescription(requestDTO.getDescription());

        // Save the scheduled transfer
        transfers.put(scheduleId, responseDTO);
        return responseDTO;
    }
    public FundTransferResponse updateScheduledTransfer(String scheduleId, FundTransferRequest requestDTO) {
        FundTransferResponse existingTransfer = transfers.get(scheduleId);
        if (existingTransfer != null) {
            if (requestDTO.getAmount() != 0) existingTransfer.setAmount(requestDTO.getAmount());
            if (requestDTO.getEndDate() != null) existingTransfer.setEndDate(requestDTO.getEndDate());
            if (requestDTO.getDescription() != null) existingTransfer.setDescription(requestDTO.getDescription());
            return existingTransfer;
        }
        return null; // Scheduled transfer not found
    }

    // Get details of a scheduled transfer
    public FundTransferResponse getScheduledTransfer(String scheduleId) {
        return transfers.get(scheduleId);
    }

    // Cancel a scheduled transfer
    public FundTransferResponse cancelScheduledTransfer(String scheduleId) {
        FundTransferResponse transfer = transfers.remove(scheduleId);
        if (transfer != null) {
            transfer.setStatus("Cancelled");
            return transfer;
        }
        return null; // Scheduled transfer not found
    }
}
