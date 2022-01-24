//
// Created by htrap19 on 1/12/22
//

package com.interfaceict.ptrakcer.models;

import com.interfaceict.ptrakcer.enums.DatabaseType;

import javax.persistence.*;

@Entity
public class ProjectDatabaseUsed
{
    @Id @GeneratedValue
    @Column(name = "id")
    private Long m_ID;

    @Column(name = "name")
    private String m_Name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private DatabaseType m_Type;

    @Column(name = "version")
    private Long m_Version;

    /*
    *   Getters
    */

    public Long getID() { return m_ID; }

    public String getName() { return m_Name; }

    public DatabaseType getDatabaseType() { return m_Type; }

    public Long getVersion() { return m_Version; }

    /*
    *   Setters
    */

    public void setName(String name) { m_Name = name; }

    public void setDatabaseType(DatabaseType type) { m_Type = type; }

    public void setVersion(Long version) { m_Version = version; }
}
