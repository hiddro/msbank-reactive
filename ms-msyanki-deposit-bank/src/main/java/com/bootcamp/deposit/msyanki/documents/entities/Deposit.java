package com.bootcamp.deposit.msyanki.documents.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "deposit_yanki")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Deposit {
    @Id
    private String id;

    @Field(name = "amount")
    private Double amount;

    private String typeOfAccount;

    private String accountNumber;

    @Field(name = "customerIdentityNumber")
    private String customerIdentityNumber;

    @Field(name = "ownerYankie")
    private String ownerYankie;

    @Field(name = "nroPhone")
    private String nroPhone;

    private Date depositDate = new Date();
}
