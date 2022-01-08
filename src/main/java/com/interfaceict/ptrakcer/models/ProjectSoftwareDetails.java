//
// Created by htrap19 on 1/8/22
//

package com.interfaceict.ptrakcer.models;

import com.interfaceict.ptrakcer.enums.ApplicationType;

import javax.persistence.*;

@Entity
public class ProjectSoftwareDetails
{
    static public String toStringTemplate = "%s ( Description: %s, Type: %s, Lang: %s, Framework: %s, Database: { Name: %s, Type: %s, Version; %s } )";

    @Id @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long m_ID;

    @Column(name = "name")
    private String m_Name;

    @Column(name = "description")
    private String m_Description;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ApplicationType m_Type;

    @Column(name = "lang")
    private String m_Lang;

    @Column(name = "framework")
    private String m_FrameworkUsed;

    // == Database details == - in future it might have a separate model
    @Column(name = "database_name")
    private String m_DatabaseName;

    @Column(name = "database_type")
    private String m_DatabaseType;

    @Column(name = "database_version")
    private String m_DatabaseVersion;
    // == ================ ==

    ProjectSoftwareDetails() {}

    ProjectSoftwareDetails(String name,
                           ApplicationType type,
                           String lang,
                           String frameworkUsed,
                           String databaseName,
                           String databaseType,
                           String databaseVersion)
    {
        m_Name = name;
        m_Type = type;
        m_Lang = lang;
        m_FrameworkUsed = frameworkUsed;
        m_DatabaseName = databaseName;
        m_DatabaseType = databaseType;
        m_DatabaseVersion = databaseVersion;
    }

    public String getName() { return m_Name; }

    public ApplicationType getApplicationType() { return m_Type; }

    public String getProgrammingLang() { return m_Lang; }

    public String getFramework() { return m_FrameworkUsed; }

    public String getDatabaseName() { return m_DatabaseName; }

    public String getDatabaseType() { return m_DatabaseType; }

    public String getDatabaseVersion() { return m_DatabaseVersion; }

    @Override
    public String toString()
    {
        return String.format(toStringTemplate,
                m_Name,
                m_Description,
                m_Type.toString(),
                m_Lang,
                m_FrameworkUsed,
                m_DatabaseName,
                m_DatabaseType,
                m_DatabaseVersion);
    }
}
