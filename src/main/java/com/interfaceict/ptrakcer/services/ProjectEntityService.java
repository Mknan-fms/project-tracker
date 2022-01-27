//
// Created by htrap19 on 1/8/22
//

package com.interfaceict.ptrakcer.services;

import com.interfaceict.ptrakcer.dto.requests.NewProject;
import com.interfaceict.ptrakcer.enums.ProjectStatus;
import com.interfaceict.ptrakcer.models.ProjectEntity;
import com.interfaceict.ptrakcer.repositories.ProjectEntityRepo;
import com.interfaceict.ptrakcer.services.helpers.GeneralServiceHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import javax.validation.Valid;

@Service
public class ProjectEntityService extends GeneralServiceHelper<ProjectEntity, ProjectEntityRepo>
{
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

    	return repo.save(projectEntity);
    }

    public ProjectEntity update(NewProject entity, Long id)
    {
        ProjectEntity entityDB = getById(id);

        entityDB.setName(entity.getName());
        entityDB.setDescription(entity.getDescription());
//        entityDB.setStatus(entity.getStatus()); // Status have it's own API
        entityDB.setDuration(entity.getDuration());
        entityDB.setStartDate(entity.getStartDate());
        entityDB.setCompletionDate(entity.getCompletionDate());
        entityDB.setDeliveryDate(entity.getDeliveryDate());

        return repo.save(entityDB);
    }

    public Boolean updateStatus(ProjectStatus status, Long id)
    {
        if (status == ProjectStatus.COMPLETED)
            return false;

        ProjectEntity entityDB = getById(id);
        entityDB.setStatus(status);

        repo.save(entityDB);
        return true;
    }

    public void delete(Long id)
    {
        ProjectEntity entityDB = getById(id);
        repo.delete(entityDB);
    }
    
}

