package com.bootcamp.deposit.msyanki.documents.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionYankiDto {

    @Id
    private String id;

    private String typeoftransaction;

    private String identityNumber;

    private String ownerYanki;

    private double transactionAmount;

    private String typeOfAccount;

    private String customerIdentityNumber;

    private String transactionDescription;

    private Date dateOperation = new Date();
}
