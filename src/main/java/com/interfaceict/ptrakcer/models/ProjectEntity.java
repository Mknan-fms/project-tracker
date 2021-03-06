//
// Created by htrap19 on 1/8/22
//

package com.interfaceict.ptrakcer.models;

import javax.persistence.*;

import com.interfaceict.ptrakcer.enums.ProjectStatus;

import java.time.LocalDate;
import java.util.List;

@Entity
public class ProjectEntity
{
    static public String toStringTemplate = "%s [ Description: %s, Status: %b, Duration(mths): %d, Start Date: %t, Completion Date: %t, Delivery Date: %t ]";

    @Id @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long m_ID;

    @Column(name = "name", nullable = false, unique = true)
    private String m_Name;

    @Column(name = "description")
    private String m_Description;

    /**
     * Value of this class field should have a dedicated API for changing its value 
     */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProjectStatus m_Status;

    @Column(name = "duration")
    private Long m_Duration; // duration in months

    @Column(name = "start_date")
    private LocalDate m_StartDate;

    @Column(name = "completion_date")
    private LocalDate m_CompletionDate;

    @Column(name = "delivery_date")
    private LocalDate m_DeliveryDate;

    @OneToOne
    private ProjectSoftwareDetails m_SoftwareDetails;

    @OneToMany
    private List<ProjectAsset> m_Assets;

    @OneToMany
    private List<ProjectDependency> m_Dependencies;

    public ProjectEntity() {}

    public Long getId() { return m_ID; }

    public void setName(String name) { m_Name = name; }

    public String getName() { return m_Name; }

    public void setDescription(String description) { m_Description = description; }

    public String getDescription() { return m_Description; }

    public void setStatus(ProjectStatus status) { m_Status = status; }

    public ProjectStatus getStatus() { return m_Status; }

    public void setDuration(Long duration) { m_Duration = duration; }

    public Long getDuration() { return m_Duration; }

    public void setStartDate(LocalDate date) { m_StartDate = date; }

    public LocalDate getStartDate() { return m_StartDate; }

    public void setCompletionDate(LocalDate date) { m_CompletionDate = date; }

    public LocalDate getCompletionDate() { return m_CompletionDate; }

    public void setDeliveryDate(LocalDate date) { m_DeliveryDate = date; }

    public LocalDate getDeliveryDate() { return m_DeliveryDate; }

    public void setSoftwareDetails(ProjectSoftwareDetails details) { m_SoftwareDetails = details; }

    public ProjectSoftwareDetails getSoftwareDetails() { return m_SoftwareDetails; }

    public void setAssets(List<ProjectAsset> assets) { m_Assets = assets; }

    public List<ProjectAsset> getAssets() { return m_Assets; }

    public void setDependencies(List<ProjectDependency> dependencies) { m_Dependencies = dependencies; }

    public List<ProjectDependency> getDependencies() { return m_Dependencies; }

    @Override
    public String toString()
    {
        return String.format(toStringTemplate,
                m_Name,
                m_Description,
                m_Status,
                m_Duration,
                m_StartDate.toString(),
                m_CompletionDate.toString(),
                m_DeliveryDate.toString());
    }
}
