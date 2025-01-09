package com.tekarch.FundTrasferServiceMS.Services;

import com.tekarch.FundTrasferServiceMS.DTO.AccountDTO;
//import com.tekarch.FundTrasferServiceMS.Models.FundTransferRequest;
import com.tekarch.FundTrasferServiceMS.Models.FundTransferResponse;
import com.tekarch.FundTrasferServiceMS.Repository.FundTransferRepository;
import com.tekarch.FundTrasferServiceMS.Services.Interfaces.FundTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
@Service
public class FundTransferServicesImpl implements FundTransferService {
    @Autowired
    private FundTransferRepository fundTransferRepository;
    private final Map<String, FundTransferResponse> transfers = new HashMap<>();
    @Autowired
    private RestTemplate restTemplate;

    String Account_URL="http://localhost:8081/account";

    @Override
    public FundTransferResponse initiateTransfer(FundTransferResponse transferRequest) {
    AccountDTO receiverAccount = restTemplate.getForObject(Account_URL + "/" + transferRequest.getToaccountId(),
            AccountDTO.class);

    AccountDTO senderAccount = restTemplate.getForObject(Account_URL + "/" + transferRequest.getFromaccountId(),
            AccountDTO.class);
    if(receiverAccount.getAccountNumber() == null || senderAccount.getAccountNumber() == null){
                throw new RuntimeException("Invalid Account");
            }

            receiverAccount.setBalance(receiverAccount.getBalance() + transferRequest.getAmount());
            senderAccount.setBalance(senderAccount.getBalance() - transferRequest.getAmount());

            restTemplate.put(Account_URL, senderAccount);
            restTemplate.put(Account_URL, receiverAccount);

            return fundTransferRepository.save(transferRequest);
        }

@Override
    public Optional<FundTransferResponse> getTransferStatus(Long transferId) {
     return fundTransferRepository.findById(transferId);

    }

    // Get all transfers (for listing purposes)
    @Override
    public   Iterable<FundTransferResponse> getAllTransfers() {
        return fundTransferRepository.findAll();
    }

    @Override
    public FundTransferResponse createScheduledTransfer(FundTransferResponse requestDTO) {
        String scheduleId = UUID.randomUUID().toString();
        FundTransferResponse responseDTO = new FundTransferResponse();
        responseDTO.setScheduleId(scheduleId);
        responseDTO.setStatus("Scheduled");

        responseDTO.setFromaccountId(requestDTO.getFromaccountId());
        responseDTO.setToaccountId(requestDTO.getToaccountId());
        responseDTO.setAmount(requestDTO.getAmount());
        responseDTO.setFrequency(requestDTO.getFrequency());
        responseDTO.setDescription(requestDTO.getDescription());

        // Save the scheduled transfer
        transfers.put(scheduleId, responseDTO);
        return responseDTO;
    }
   public FundTransferResponse updateScheduledTransfer(String scheduleId, FundTransferResponse request) {
        FundTransferResponse existingTransfer = transfers.get(scheduleId);
        if (existingTransfer != null) {
            if (request.getAmount() != 0) existingTransfer.setAmount(request.getAmount());
            if (request.getEndDate() != null) existingTransfer.setCompleted_at(request.getEndDate().atStartOfDay());
            if (request.getDescription() != null) existingTransfer.setDescription(request.getDescription());
            return existingTransfer;
        }
        return null; // Scheduled transfer not found
    }

    // Get details of a scheduled transfer
    @Override
    public FundTransferResponse getScheduledTransfer(String scheduleId) {
        return transfers.get(scheduleId);
    }

    // Cancel a scheduled transfer
    @Override
    public FundTransferResponse cancelScheduledTransfer(String scheduleId) {
        FundTransferResponse transfer = transfers.remove(scheduleId);
        if (transfer != null) {
            transfer.setStatus("Cancelled");
            return transfer;
        }
        return null; // Scheduled transfer not found
    }
}
 /*@Override
    public FundTransferResponse validateTransactionLimit(String accountId, FundTransferRequest requestDTO) {
        String url = String.format("%s/accounts/%s/limits/validate-transaction?amount=%f", Account_URL, accountId, requestDTO.getAmount());
        return restTemplate.getForObject(url, FundTransferResponse.class);
    }
    @Override
    public RemainingResponseDTO getRemainingLimits(String accountId) {
        String url = String.format("%s/accounts/%s/transaction-limits", Account_URL, accountId);
        return restTemplate.getForObject(url, RemainingResponseDTO.class);
    }*/