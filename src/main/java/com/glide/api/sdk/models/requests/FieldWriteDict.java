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
public class FieldWriteDict extends RequestModel {
    private HashMap<String, TransactionFieldWrite> fields;
}
