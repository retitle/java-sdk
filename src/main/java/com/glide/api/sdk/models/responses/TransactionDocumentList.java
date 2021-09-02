package com.glide.api.sdk.models.responses;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@NoArgsConstructor
public class TransactionDocumentList extends ResponseList<TransactionDocument> {

}
