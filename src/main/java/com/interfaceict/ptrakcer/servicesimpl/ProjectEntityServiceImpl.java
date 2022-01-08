//
// Created by htrap19 on 1/8/22
//

package com.interfaceict.ptrakcer.servicesimpl;

import com.interfaceict.ptrakcer.models.ProjectEntity;
import com.interfaceict.ptrakcer.repositories.ProjectEntityRepo;
import com.interfaceict.ptrakcer.services.ProjectService;
import com.interfaceict.ptrakcer.services.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectEntityServiceImpl extends ProjectService<ProjectEntity, ProjectEntityRepo>
{
    public ProjectEntity update(ProjectEntity entity, Long id)
    {
        Optional<ProjectEntity> entityDBOpt = m_Repo.findById(id);
        if (entityDBOpt.isEmpty()) return null;

        ProjectEntity entityDB = entityDBOpt.get();

        // This can be refactored more
        if (Utils.CheckIfItsNullOrEmpty(entity.getName()))
            entityDB.setName(entity.getName());

        if (Utils.CheckIfItsNullOrEmpty(entity.getDescription()))
            entityDB.setDescription(entity.getDescription());

        if (Utils.CheckIfItsNullOrEmpty(entity.getStatus()))
            entityDB.setStatus(entity.getStatus());

        if (Utils.CheckIfItsNullOrEmpty(entity.getDuration()))
            entityDB.setDuration(entity.getDuration());

        if (Utils.CheckIfItsNullOrEmpty(entity.getStartDate()))
            entityDB.setStartDate(entity.getStartDate());

        if (Utils.CheckIfItsNullOrEmpty(entity.getCompletionDate()))
            entityDB.setCompletionDate(entity.getCompletionDate());

        if (Utils.CheckIfItsNullOrEmpty(entity.getDeliveryDate()))
            entityDB.setDeliveryDate(entity.getDeliveryDate());
        // ====

        return m_Repo.save(entityDB);
    }
}
