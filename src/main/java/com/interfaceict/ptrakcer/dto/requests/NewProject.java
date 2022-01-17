package com.interfaceict.ptrakcer.dto.requests;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Simple POJO class to hold new project required parameters fields
 * 
 * @author Mustafa Ibrahim
 *
 */
public class NewProject {

	@NotNull @NotEmpty
    private String m_Name;
	
	private String m_Description;
	
	@NotNull
    private Long m_Duration;
	
	private Date m_StartDate;

	public NewProject() {
		// TODO Auto-generated constructor stub
	}
	
	public String getM_Name() {
		return m_Name;
	}

	public void setM_Name(String m_Name) {
		this.m_Name = m_Name;
	}

	public String getM_Description() {
		return m_Description;
	}

	public void setM_Description(String m_Description) {
		this.m_Description = m_Description;
	}

	public Long getM_Duration() {
		return m_Duration;
	}

	public void setM_Duration(Long m_Duration) {
		this.m_Duration = m_Duration;
	}

	public Date getM_StartDate() {
		return m_StartDate;
	}

	public void setM_StartDate(Date m_StartDate) {
		this.m_StartDate = m_StartDate;
	}
	
	
}
