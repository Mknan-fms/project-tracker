//
// Created by htrap19 on 1/8/22
//

package com.interfaceict.ptrakcer.services;

import com.interfaceict.ptrakcer.models.ProjectEntity;
import com.interfaceict.ptrakcer.repositories.ProjectEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectEntityService
{
    @Autowired
    private ProjectEntityRepo m_Repo;

    public List<ProjectEntity> getAll() { return new ArrayList<>(m_Repo.findAll()); }

    public void save(ProjectEntity entity) { m_Repo.save(entity); }

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

