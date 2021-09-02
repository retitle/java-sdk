package com.glide.api.sdk.models.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@NoArgsConstructor
public class TransactionFieldWrite {
    private long controlTimestamp;
    private Object value;

    public TransactionFieldWrite(Object value) {
        this.value = value;
        this.controlTimestamp = 0;
    }

    public TransactionFieldWrite(Object value, long controlTimestamp) {
        this.value = value;
        this.controlTimestamp = controlTimestamp;
    }
}
