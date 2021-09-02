package com.glide.api.sdk.models.responses;

import java.util.HashMap;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@NoArgsConstructor
public class Address extends ResponseModel {
    private String city;
    private String state;
    private String street;
    private String unit;
    private String zipCode;

}
