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
public class Contact extends ResponseModel {
    private Agent agent;
    private String cellPhone;
    private String email;
    private String entityName;
    private String entityType;
    private String firstName;
    private String lastName;
    private String title;

}
