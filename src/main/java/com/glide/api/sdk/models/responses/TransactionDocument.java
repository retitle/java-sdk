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
public class TransactionDocument extends ResponseModel {
    private String id;
    private Folder folder;
    private String folderKind;
    private int order;
    private String title;
    private Transaction transaction;
    private String url;

    public String getId() {
        return id != null ? id : "";
    }

}
