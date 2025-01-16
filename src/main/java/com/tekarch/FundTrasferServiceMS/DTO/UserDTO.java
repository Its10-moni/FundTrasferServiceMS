package com.tekarch.FundTrasferServiceMS.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private Long userid;
    private String username;
    private String Email;
    private Long MobileNumber;
    private String Address;
}
