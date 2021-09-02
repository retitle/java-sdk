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
public class Contact extends RequestModel {
    private Agent agent;
    private String cellPhone;
    private String email;
    private String entityName;
    private String entityType;
    private String firstName;
    private String lastName;
    private String title;
}
