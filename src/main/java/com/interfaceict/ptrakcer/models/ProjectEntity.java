//
// Created by htrap19 on 1/8/22
//

package com.interfaceict.ptrakcer.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ProjectEntity
{
    static public String toStringTemplate = "%s [ Description: %s, Status: %b, Duration(mths): %d, Start Date: %t, Completion Date: %t, Delivery Date: %t ]";

    @Id @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long m_ID;

    @Column(name = "name", nullable = false)
    private String m_Name;

    @Column(name = "description")
    private String m_Description;

    @Column(name = "status")
    private Boolean m_Status;

    @Column(name = "duration")
    private Long m_Duration; // duration in months

    @Column(name = "start_date")
    private Date m_StartDate;

    @Column(name = "completion_date")
    private Date m_CompletionDate;

    @Column(name = "delivery_date")
    private Date m_DeliveryDate;

    ProjectEntity() {}

    ProjectEntity(String name,
                  String description,
                  Boolean status,
                  Long duration,
                  Date startDate,
                  Date completionDate,
                  Date deliveryDate)
    {
        m_Name = name;
        m_Description = description;
        m_Status = status;
        m_Duration = duration;
        m_StartDate = startDate;
        m_CompletionDate = completionDate;
        m_DeliveryDate = deliveryDate;
    }

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
