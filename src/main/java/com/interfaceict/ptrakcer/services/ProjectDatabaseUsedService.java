//
// Created by htrap19 on 1/20/22
//

package com.interfaceict.ptrakcer.services;

import com.interfaceict.ptrakcer.dto.requests.NewDatabase;
import com.interfaceict.ptrakcer.models.ProjectDatabaseUsed;
import com.interfaceict.ptrakcer.models.ProjectSoftwareDetails;
import com.interfaceict.ptrakcer.repositories.ProjectDatabaseUsedRepo;
import com.interfaceict.ptrakcer.repositories.ProjectSoftwareDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectDatabaseUsedService
{
    @Autowired
    private ProjectDatabaseUsedRepo m_ProjectDBUsedRepo;

    @Autowired
    private ProjectSoftwareDetailsRepo m_ProjectSoftDetailsRepo;

    public List<ProjectDatabaseUsed> getAll() { return new ArrayList<>(m_ProjectDBUsedRepo.findAll()); }

    public List<ProjectDatabaseUsed> getAllByProjectSoftID(Long projectSoftID)
    {
        Optional<ProjectSoftwareDetails> entityDBOpt = m_ProjectSoftDetailsRepo.findById(projectSoftID);
        if (entityDBOpt.isEmpty()) return null;

        return entityDBOpt.get().getDatabases();
    }

    public ProjectDatabaseUsed save(@Valid NewDatabase database)
    {
        Optional<ProjectSoftwareDetails> entityDBOpt = m_ProjectSoftDetailsRepo.findById(database.getProjectSoftDetailID());
        if (entityDBOpt.isEmpty()) return null;

        ProjectDatabaseUsed dpUsed = new ProjectDatabaseUsed();
        dpUsed.setName(database.getName());
        dpUsed.setDatabaseType(database.getType());
        dpUsed.setVersion(database.getVersion());

        entityDBOpt.get().getDatabases().add(dpUsed);
        m_ProjectDBUsedRepo.save(dpUsed);
        return dpUsed;
    }

    public ProjectDatabaseUsed update(@Valid NewDatabase database, Long dbUsedID)
    {
        Optional<ProjectDatabaseUsed> entityDBOpt = m_ProjectDBUsedRepo.findById(dbUsedID);
        if (entityDBOpt.isEmpty()) return null;

        ProjectDatabaseUsed entityDB = entityDBOpt.get();
        entityDB.setName(database.getName());
        entityDB.setDatabaseType(database.getType());
        entityDB.setVersion(database.getVersion());

        return m_ProjectDBUsedRepo.save(entityDB);
    }

    public boolean delete(Long projectSoftDetailsID, Long dbUsedID)
    {
        Optional<ProjectSoftwareDetails> projectSoftDetailsOpt = m_ProjectSoftDetailsRepo.findById(projectSoftDetailsID);
        Optional<ProjectDatabaseUsed> projectDBUsedOpt = m_ProjectDBUsedRepo.findById(dbUsedID);
        if (projectSoftDetailsOpt.isEmpty() || projectDBUsedOpt.isEmpty()) return false;

        projectSoftDetailsOpt.get().getDatabases().removeIf(dbUsed -> dbUsedID.equals(dbUsed.getID()));
        m_ProjectDBUsedRepo.delete(projectDBUsedOpt.get());
        return true;
    }
}
