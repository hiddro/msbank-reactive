package com.bootcamp.transaction.msyanki.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "transaction_yanki")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionYanki {

    @Id
    String id;

    private String typeoftransaction;

    private String identityNumber;

    private String ownerYanki;

    private String typeOfAccount;

    private String customerIdentityNumber;

    private double transactionAmount;

    private String destination;

    private String destinationYanki;

    private String transactionDescription;

    private Date dateOperation = new Date();
}
