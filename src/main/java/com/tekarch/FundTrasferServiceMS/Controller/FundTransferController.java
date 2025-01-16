package com.tekarch.FundTrasferServiceMS.Controller;

//import com.tekarch.FundTrasferServiceMS.Models.FundTransferRequest;
import com.tekarch.FundTrasferServiceMS.Models.FundTransferResponse;
import com.tekarch.FundTrasferServiceMS.Services.FundTransferServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/transfer")
public class FundTransferController {

    @Autowired
    private FundTransferServicesImpl fundTransferServices;

    @PostMapping
    public ResponseEntity<FundTransferResponse> initiateTransfer(@RequestBody FundTransferResponse transferRequest) {
        FundTransferResponse initiatedTransfer = fundTransferServices.initiateTransfer(transferRequest);
        return new ResponseEntity<>(initiatedTransfer, HttpStatus.CREATED);
    }
    @GetMapping("/{transferId}")
    public Optional<FundTransferResponse> getAccount(@PathVariable Long transferId) {
            return fundTransferServices.getTransferStatus(transferId);
    }

    @GetMapping
    public Iterable<FundTransferResponse> getAllTransfers() {
        return fundTransferServices.getAllTransfers();
    }

    @PostMapping("/transfer?scheduled={date}")
    public FundTransferResponse setUpScheduledTransfer(@RequestBody FundTransferResponse requestDTO) {
        return fundTransferServices.createScheduledTransfer(requestDTO);
    }

     //Endpoint to update an existing scheduled transfer
    @PutMapping("/{scheduleId}")
    public FundTransferResponse updateScheduledTransfer(@PathVariable String scheduleId,
                                                                @RequestBody FundTransferResponse request) {
        FundTransferResponse request1 = new FundTransferResponse();
        return fundTransferServices.updateScheduledTransfer(scheduleId, request1);
    }

    // Endpoint to get details of a scheduled transfer
    @GetMapping("/{scheduleId}")
    public FundTransferResponse getScheduledTransfer(@PathVariable String scheduleId) {
        return fundTransferServices.getScheduledTransfer(scheduleId);
    }
    @DeleteMapping("/{scheduleId}")
    public FundTransferResponse cancelScheduledTransfer(@PathVariable String scheduleId) {
        return fundTransferServices.cancelScheduledTransfer(scheduleId);
    }

}
 /*  @GetMapping("/accounts/{accountId}/limits/validate-transaction")
    public FundTransferResponse validateTransaction(@PathVariable String accountId,
                                                           @RequestParam Long amount) {
        FundTransferRequest requestDTO = new FundTransferRequest();
        requestDTO.setAmount(amount);
        return fundTransferServices.validateTransactionLimit(accountId, requestDTO);
    }
    @GetMapping("/accounts/{accountId}/transaction-limits")
    public RemainingResponseDTO getRemainingLimits(@PathVariable String accountId) {
        return fundTransferServices.getRemainingLimits(accountId);
    }*/

