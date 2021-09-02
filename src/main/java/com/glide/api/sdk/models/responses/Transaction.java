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
public class Transaction extends ResponseModel {
    private String id;
    private Address address;
    private HashMap<String, TransactionField> fields;
    private FolderList folders;
    private PartyList parties;
    private String stage;
    private String title;
    private TransactionDocumentList transactionDocuments;

    public String getId() {
        return id != null ? id : "";
    }

}
