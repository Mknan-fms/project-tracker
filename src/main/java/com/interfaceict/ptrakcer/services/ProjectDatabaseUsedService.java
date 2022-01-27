//
// Created by htrap19 on 1/20/22
//

package com.interfaceict.ptrakcer.services;

import com.interfaceict.ptrakcer.dto.requests.NewDatabase;
import com.interfaceict.ptrakcer.models.ProjectDatabaseUsed;
import com.interfaceict.ptrakcer.models.ProjectSoftwareDetails;
import com.interfaceict.ptrakcer.repositories.ProjectDatabaseUsedRepo;
import com.interfaceict.ptrakcer.services.helpers.GeneralServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class ProjectDatabaseUsedService extends GeneralServiceHelper<ProjectDatabaseUsed, ProjectDatabaseUsedRepo>
{
    @Autowired
    private ProjectSoftwareDetailsService m_ProjectSoftDetailsService;

    public List<ProjectDatabaseUsed> getAllByProjectSoftID(Long projectSoftID)
    {
        return m_ProjectSoftDetailsService.getById(projectSoftID).getDatabases();
    }

    public ProjectDatabaseUsed save(@Valid NewDatabase database)
    {
        ProjectSoftwareDetails entityDB = m_ProjectSoftDetailsService.getById(database.getProjectSoftDetailID());

        ProjectDatabaseUsed dpUsed = new ProjectDatabaseUsed();
        dpUsed.setName(database.getName());
        dpUsed.setDatabaseType(database.getType());
        dpUsed.setVersion(database.getVersion());

        entityDB.getDatabases().add(dpUsed);
        return repo.save(dpUsed);
    }

    public ProjectDatabaseUsed update(@Valid NewDatabase database, Long dbUsedID)
    {
        ProjectDatabaseUsed entityDB = getById(dbUsedID);
        entityDB.setName(database.getName());
        entityDB.setDatabaseType(database.getType());
        entityDB.setVersion(database.getVersion());

        return repo.save(entityDB);
    }

    public void delete(Long projectSoftDetailsID, Long dbUsedID)
    {
        ProjectSoftwareDetails projectSoftwareDetails = m_ProjectSoftDetailsService.getById(projectSoftDetailsID);
        ProjectDatabaseUsed projectDatabaseUsed = getById(dbUsedID);

        projectSoftwareDetails.getDatabases().removeIf(dbUsed -> dbUsedID.equals(dbUsed.getID()));
        repo.delete(projectDatabaseUsed);
    }
}
