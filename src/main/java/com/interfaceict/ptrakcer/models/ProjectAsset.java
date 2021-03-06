//
// Created by htrap19 on 1/8/22
//

package com.interfaceict.ptrakcer.models;

import com.interfaceict.ptrakcer.enums.AssetType;

import javax.persistence.*;
import java.util.Base64;

@Entity
public class ProjectAsset
{
    static public String toStringTemplate = "%s { Description: %s, Type: %s }";

    @Id @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long m_ID;

    @Column(name = "name", nullable = false)
    private String m_Name;

    @Column(name = "description")
    private String m_Description;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AssetType m_Type;

    @Column(name = "asset_file")
    private String m_EncodedAssetFile; // Base64 string

    public ProjectAsset() {}

    /*
    *   Getters
    */

    public Long getID() { return m_ID; }

    public String getName() { return m_Name; }

    public String getDescription() { return m_Description; }

    public AssetType getAssetType() { return m_Type; }

    /*
    *   Setters
    */

    public void setName(String name) { m_Name = name; }

    public void setDescription(String description) { m_Description = description; }

    public void setAssetType(AssetType type) { m_Type = type; }

    /*
    *   Utilities
    */

    // Setter for asset file
    public void encodeAssetFile(String fileData) { m_EncodedAssetFile = Base64.getEncoder().encodeToString(fileData.getBytes()); }

    // Getter for asset file
    public String decodeAssetFile()
    {
        byte[] decodedFile = Base64.getDecoder().decode(m_EncodedAssetFile);
        return new String(decodedFile);
    }

    @Override
    public String toString()
    {
        return String.format(toStringTemplate,
                m_Name,
                m_Description,
                m_Type.toString());
    }
}
