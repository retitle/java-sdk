package com.glide.api.sdk.models.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class TransactionsDetailRequest extends DetailResourceModel {
    public TransactionsDetailRequest (@NonNull String id) {
        this.setId(id);
    }
}