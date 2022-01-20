//
// Created by htrap19 on 1/17/22
//

package com.interfaceict.ptrakcer.dto.requests;

import com.interfaceict.ptrakcer.enums.AssetType;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Base64;

@Data
public class NewAsset
{
    @NotNull @NotEmpty
    private String name;

    @Min(1)
    private Long projectID;

    private String description;

    @NotNull
    private AssetType type;

    @NotNull
    private String encodedAssetFile; // Base64 String

    // Alternative way for file string
    public void encodeAssetFile(String data) { encodedAssetFile = Base64.getEncoder().encodeToString(data.getBytes()); }

    public String decodeAssetFile()
    {
        if (encodedAssetFile.isEmpty()) return "";

        byte[] decodedFile = Base64.getDecoder().decode(encodedAssetFile);
        return new String(decodedFile);
    }
}
