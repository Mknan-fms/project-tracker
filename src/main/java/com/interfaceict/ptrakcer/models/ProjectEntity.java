//
// Created by htrap19 on 1/8/22
//

package com.interfaceict.ptrakcer.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class ProjectEntity
{
    static public String toStringTemplate = "%s [ Description: %s, Status: %b, Duration(mths): %d, Start Date: %t, Completion Date: %t, Delivery Date: %t ]";

    @Id @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long m_ID;

    @Column(name = "name", nullable = false, unique = true)
    @NotNull @NotEmpty
    private String m_Name;

    @Column(name = "description")
    private String m_Description;

    /**
     * TODO: This class field should have initial value
     * and it should an ENUM type, since the project can have multiple status:
     * <pre>{PENDING , STARTED , STOPPED , CANCELED , COMPLETED}</pre>
     */
    @Column(name = "status")
    @NotNull
    private Boolean m_Status;

    @Column(name = "duration")
    @NotNull
    private Long m_Duration; // duration in months

    @Column(name = "start_date")
    private Date m_StartDate;

    @Column(name = "completion_date")
    private Date m_CompletionDate;

    @Column(name = "delivery_date")
    private Date m_DeliveryDate;

    @OneToOne
    @JoinColumn(name = "id")
    private ProjectSoftwareDetails m_SoftwareDetails;

    @OneToMany
    @JoinColumn(name = "id")
    private List<ProjectAsset> m_Assets;

    @OneToMany
    @JoinColumn(name = "id")
    private List<ProjectDependency> m_Dependencies;

    ProjectEntity() {}

    public Long getId() { return m_ID; }

    public void setName(String name) { m_Name = name; }

    public String getName() { return m_Name; }

    public void setDescription(String description) { m_Description = description; }

    public String getDescription() { return m_Description; }

    public void setStatus(Boolean status) { m_Status = status; }

    public Boolean getStatus() { return m_Status; }

    public void setDuration(Long duration) { m_Duration = duration; }

    public Long getDuration() { return m_Duration; }

    public void setStartDate(Date date) { m_StartDate = date; }

    public Date getStartDate() { return m_StartDate; }

    public void setCompletionDate(Date date) { m_CompletionDate = date; }

    public Date getCompletionDate() { return m_CompletionDate; }

    public void setDeliveryDate(Date date) { m_DeliveryDate = date; }

    public Date getDeliveryDate() { return m_DeliveryDate; }

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
