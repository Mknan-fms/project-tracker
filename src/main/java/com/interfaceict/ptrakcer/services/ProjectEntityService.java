//
// Created by htrap19 on 1/8/22
//

package com.interfaceict.ptrakcer.services;

import com.interfaceict.ptrakcer.dto.requests.NewProject;
import com.interfaceict.ptrakcer.enums.ProjectStatus;
import com.interfaceict.ptrakcer.models.ProjectEntity;
import com.interfaceict.ptrakcer.repositories.ProjectEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@Service
public class ProjectEntityService
{
    @Autowired
    private ProjectEntityRepo m_Repo;

    public List<ProjectEntity> getAll() { return new ArrayList<>(m_Repo.findAll()); }

    
    /**
     * TODO:<br>
     * 	Here we need to create a new project entity and set some fields' values
     * 
     * @param newProjectRequest
     */
    public ProjectEntity save(@Valid NewProject newProjectRequest) { 
    	
    	ProjectEntity projectEntity = new ProjectEntity();
    	
    	projectEntity.setDuration(newProjectRequest.getM_Duration());
    	projectEntity.setStartDate(newProjectRequest.getM_StartDate());
    	projectEntity.setName(newProjectRequest.getM_Name());
    	projectEntity.setDescription(newProjectRequest.getM_Description());
    	projectEntity.setStatus(ProjectStatus.PENDING);		//Default value for project status when it's first-initialized
    	projectEntity.setCompletionDate(null); // Here we can calculate expected finish date for the project based on start date
    	
    	/**
    	 * Also, there are some dependent-fields we can set like {status, completion_date, ...etc} 
    	 */
    	
    	
    	return m_Repo.save(projectEntity); 
    }

    public ProjectEntity update(ProjectEntity entity, Long id)
    {
        Optional<ProjectEntity> entityDBOpt = m_Repo.findById(id);
        if (entityDBOpt.isEmpty()) return null;

        ProjectEntity entityDB = entityDBOpt.get();

        entityDB.setName(entity.getName());
        entityDB.setDescription(entity.getDescription());
        entityDB.setStatus(entity.getStatus());
        entityDB.setDuration(entity.getDuration());
        entityDB.setStartDate(entity.getStartDate());
        entityDB.setCompletionDate(entity.getCompletionDate());
        entityDB.setDeliveryDate(entity.getDeliveryDate());

        return m_Repo.save(entityDB);
    }

    public void delete(Long id) { m_Repo.deleteById(id); }
}

