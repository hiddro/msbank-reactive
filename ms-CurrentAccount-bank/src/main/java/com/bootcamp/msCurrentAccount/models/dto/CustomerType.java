package com.bootcamp.msCurrentAccount.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;

/**
 * The type Customer type.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerType {

    private String code;
    private String name;
}
