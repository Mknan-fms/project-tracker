//
// Created by htrap19 on 1/8/22
//

package com.interfaceict.ptrakcer.models;

import com.interfaceict.ptrakcer.enums.ApplicationType;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class ProjectSoftwareDetails
{
    static public String toStringTemplate = "%s ( Description: %s, Type: %s, Lang: %s, Framework: %s, Database: { Name: %s, Type: %s, Version; %s } )";

    @Id @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long m_ID;

    @Column(name = "name")
    @NotNull @NotEmpty
    private String m_Name;

    @Column(name = "description")
    private String m_Description;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private ApplicationType m_Type;

    @Column(name = "lang")
    @NotNull @NotEmpty
    private String m_Lang;

    @Column(name = "framework")
    @NotNull @NotEmpty
    private String m_FrameworkUsed;

    @OneToMany
    @JoinColumn(name = "id")
    @NotNull
    private List<DatabaseEntity> m_Databases;

    ProjectSoftwareDetails() {}

    /*
    *   Getters
    */

    public Long getID() { return m_ID; }

    public String getName() { return m_Name; }

    public String getDescription() { return m_Description; }

    public ApplicationType getApplicationType() { return m_Type; }

    public String getProgrammingLang() { return m_Lang; }

    public String getFramework() { return m_FrameworkUsed; }

    public List<DatabaseEntity> getDatabases() { return m_Databases; }

    /*
    *   Setters
    */

    public void setName(String name) { m_Name = name; }

    public void setDescription(String description) { m_Description = description; }

    public void setApplicationType(ApplicationType type) { m_Type = type; }

    public void setProgrammingLanguage(String language) { m_Lang = language; }

    public void setFrameworkUsed(String framework) { m_FrameworkUsed = framework; }

    public void setDatabases(List<DatabaseEntity> databases) { m_Databases = databases; }

    @Override
    public String toString()
    {
        return String.format(toStringTemplate,
                m_Name,
                m_Description,
                m_Type.toString(),
                m_Lang,
                m_FrameworkUsed,
                m_Databases.toString());
    }
}
