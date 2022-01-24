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

import java.time.LocalDate;
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

    public ProjectEntity save(@Valid NewProject newProjectRequest)
    {
    	ProjectEntity projectEntity = new ProjectEntity();
    	
    	projectEntity.setDuration(newProjectRequest.getDuration()); // duration is in months
    	projectEntity.setStartDate(newProjectRequest.getStartDate());
    	projectEntity.setName(newProjectRequest.getName());
    	projectEntity.setDescription(newProjectRequest.getDescription());
    	projectEntity.setStatus(ProjectStatus.PENDING);	// Default value for project status when it's first-initialized

        // Here we calculated expected date of completion based on start date
        LocalDate completionDate = projectEntity.getStartDate().plusMonths(projectEntity.getDuration());
    	projectEntity.setCompletionDate(completionDate);

    	return m_Repo.save(projectEntity);
    }

    public ProjectEntity update(NewProject entity, Long id)
    {
        Optional<ProjectEntity> entityDBOpt = m_Repo.findById(id);
        if (entityDBOpt.isEmpty()) return null;

        ProjectEntity entityDB = entityDBOpt.get();

        entityDB.setName(entity.getName());
        entityDB.setDescription(entity.getDescription());
//        entityDB.setStatus(entity.getStatus()); // Status have it's own API
        entityDB.setDuration(entity.getDuration());
        entityDB.setStartDate(entity.getStartDate());
        entityDB.setCompletionDate(entity.getCompletionDate());
        entityDB.setDeliveryDate(entity.getDeliveryDate());

        return m_Repo.save(entityDB);
    }

    public Boolean updateStatus(ProjectStatus status, Long id)
    {
        if (status == ProjectStatus.COMPLETED)
            return false;

        Optional<ProjectEntity> entityDBOpt = m_Repo.findById(id);
        if (entityDBOpt.isEmpty())
            return false;

        ProjectEntity entityDB = entityDBOpt.get();
        entityDB.setStatus(status);

        m_Repo.save(entityDB);
        return true;
    }

    public void delete(Long id) { m_Repo.deleteById(id); }
    
    /**
     * TODO: instead of defining and calling `findProjectById` repository method every time
     *       we can write `getProjectbyId` method here (For the sake of code re usability)
     *       This is also valid for other entities's services methods  
     */
    
}

