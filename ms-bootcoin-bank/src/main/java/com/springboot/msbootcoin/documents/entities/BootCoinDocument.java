package com.springboot.msbootcoin.documents.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "bootCoin_cp")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BootCoinDocument {

    @Id
    private String id;

    @Field(name = "typeOfAccount")
    private String typeOfAccount;

    @Field(name = "customerIdentityType")
    private String customerIdentityType;

    @Field(name = "customerIdentityNumber")
    @Indexed(unique = true)
    private String customerIdentityNumber;

    @Indexed(unique = true)
    @Field(name = "ownerYankie")
    private String ownerBootCoin;

    @Indexed(unique = true)
    @Field(name = "email")
    private String email;

    @Indexed(unique = true)
    @Field(name = "nroPhone")
    private String nroPhone;

    private Double amountBitCoin;

    @Field(name = "accountPayment")
    private String accountPayment;

    private Date createBootCoin;


}
