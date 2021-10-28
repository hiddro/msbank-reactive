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
public class YankiDocumentDto {

    @Id
    private String id;

    private String typeOfAccount;

    private String customerIdentityType;

    private String customerIdentityNumber;

    private String ownerYankie;

    private String email;

    private String nroPhone;

    private String imeiPhone;

    private double amountYanki;

    private Date createYanki;

    private String debitCard;
}
