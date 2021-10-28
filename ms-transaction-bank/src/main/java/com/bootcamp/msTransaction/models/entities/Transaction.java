package com.bootcamp.msTransaction.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.inject.spi.PrivateElements;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * The type Transaction.
 */
@Document(collection = "transaction")
@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Builder
public class Transaction {

    /**
     * The Id.
     */
    @Id
    String id;

    private String typeoftransaction;

    private String identityNumber;

    private String typeOfAccount;

    private String accountUsed;

    private String customerIdentityNumber;

    private double transactionAmount;

    private String destination;

    private String transactionDescription;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private  LocalDateTime dateOperation;
}
