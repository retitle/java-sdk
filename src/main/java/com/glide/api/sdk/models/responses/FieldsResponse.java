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
public class FieldsResponse extends ResponseModel {
    private FieldsResponseResult result;
    private String transactionId;

}
