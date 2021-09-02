package com.glide.api.sdk.models.requests;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class Address extends RequestModel {
    @NonNull private String city;
    @NonNull private String state;
    @NonNull private String street;
    private String unit;
    @NonNull private String zipCode;
}
