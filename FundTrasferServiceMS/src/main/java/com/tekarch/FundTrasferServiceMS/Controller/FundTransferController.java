package com.tekarch.FundTrasferServiceMS.Controller;

import com.tekarch.FundTrasferServiceMS.Models.FundTransferRequest;
import com.tekarch.FundTrasferServiceMS.Models.FundTransferResponse;
import com.tekarch.FundTrasferServiceMS.Services.FundTransferServicesImpl;
import com.tekarch.FundTrasferServiceMS.Services.Interfaces.FundTransferService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/transfer")
public class FundTransferController {
    private final FundTransferService fundTransferServices;

    public FundTransferController(FundTransferService fundTransferServices) {
        this.fundTransferServices = fundTransferServices;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FundTransferResponse initiateTransfer(@Valid @RequestBody FundTransferRequest transferRequest) {
        return fundTransferServices.initiateTransfer(transferRequest);
    }
    @GetMapping("/{transferId}")
    public Optional<FundTransferResponse> getAccount(@PathVariable Long transferId) {
            return fundTransferServices.getTransferStatus(transferId);
    }

    @GetMapping
    public Iterable<FundTransferResponse> getAllTransfers() {
        return fundTransferServices.getAllTransfers();
    }
}

