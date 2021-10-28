package com.springboot.transaction.msbootcoin.documents.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class YankiDtoDocument {

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
    private String ownerYankie;

    @Indexed(unique = true)
    @Field(name = "email")
    private String email;

    @Indexed(unique = true)
    @Field(name = "nroPhone")
    private String nroPhone;

    @Indexed(unique = true)
    @Field(name = "imeiPhone")
    private String imeiPhone;

    private double amountYanki;

    private Date createYanki;

    @Indexed(unique = true)
    private String debitCard;
}
