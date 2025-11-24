package com.f1.customer_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerAddressDTO {
    @JsonProperty(defaultValue = "other")
    private String type;

    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotBlank
    private String country;

    @JsonProperty("postal_code")
    private String postalCode;

    @JsonProperty("customer_id")
    private Integer customerId;
}
