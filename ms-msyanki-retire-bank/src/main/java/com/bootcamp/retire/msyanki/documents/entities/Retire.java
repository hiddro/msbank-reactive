package com.bootcamp.retire.msyanki.documents.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "retire_yanki")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Retire {

    @Id
    private String id;

    @Field(name = "amount")
    private Double amount;

    @Field(name = "typeOfAccount")
    private String typeOfAccount;

    @Field(name = "customerIdentityNumber")
    private String customerIdentityNumber;

    @Field(name = "ownerYankie")
    private String ownerYankie;

    @Field(name = "nroPhone")
    private String nroPhone;

    private String description;

    private Date createRetire = new Date();
}
