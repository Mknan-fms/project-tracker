//
// Created by htrap19 on 1/8/22
//

package com.interfaceict.ptrakcer.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class ProjectDependency
{
    static public String toStringTemplate = "Dep [%s]: { Description: %s, Status: %b }";

    @Id @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long m_ID;

    @Column(name = "name", nullable = false)
    @NotNull @NotEmpty
    private String m_Name;

    @Column(name = "description")
    private String m_Description;

    @Column(name = "status")
    @NotNull
    private Boolean m_Status;

    ProjectDependency() {}

    ProjectDependency(String name,
                      String description,
                      Boolean status)
    {
        m_Name = name;
        m_Description = description;
        m_Status = status;
    }

    /*
    *   Getters
    */

    public Long getID() { return m_ID; }

    public String getName() { return m_Name; }

    public String getDescription() { return m_Description; }

    public Boolean getStatus() { return m_Status; }

    /*
    *   Setters
    */

    public void setName(String name) { m_Name = name; }

    public void setDescription(String description) { m_Description = description; }

    public void setStatus(Boolean status) { m_Status = status; }

    /*
    *   Utilities
    */

    @Override
    public String toString()
    {
        return String.format(toStringTemplate,
                m_Name,
                m_Description,
                m_Status);
    }
}
