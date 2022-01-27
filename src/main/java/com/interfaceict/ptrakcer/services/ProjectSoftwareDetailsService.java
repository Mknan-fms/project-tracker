//
// Created by htrap19 on 1/20/22
//

package com.interfaceict.ptrakcer.services;

import com.interfaceict.ptrakcer.dto.requests.NewProjectSoftwareDetail;
import com.interfaceict.ptrakcer.models.ProjectEntity;
import com.interfaceict.ptrakcer.models.ProjectSoftwareDetails;
import com.interfaceict.ptrakcer.repositories.ProjectSoftwareDetailsRepo;
import com.interfaceict.ptrakcer.services.helpers.GeneralServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class ProjectSoftwareDetailsService extends GeneralServiceHelper<ProjectSoftwareDetails, ProjectSoftwareDetailsRepo>
{
    @Autowired
    private ProjectEntityService m_ProjectEntityService;

    public ProjectSoftwareDetails save(@Valid NewProjectSoftwareDetail detail)
    {
        ProjectEntity projectEntity = m_ProjectEntityService.getById(detail.getProjectID());

        ProjectSoftwareDetails softwareDetails = new ProjectSoftwareDetails();
        softwareDetails.setName(detail.getName());
        softwareDetails.setDescription(detail.getDescription());
        softwareDetails.setApplicationType(detail.getType());
        softwareDetails.setProgrammingLanguage(detail.getLang());
        softwareDetails.setFrameworkUsed(detail.getFrameworkUsed());

        projectEntity.setSoftwareDetails(softwareDetails);
        return repo.save(softwareDetails);
    }

    public ProjectSoftwareDetails update(@Valid NewProjectSoftwareDetail details, Long projectSoftDetailsID)
    {
        ProjectEntity projectEntity = m_ProjectEntityService.getById(details.getProjectID());

        ProjectSoftwareDetails entityDB = getById(projectSoftDetailsID);
        entityDB.setName(details.getName());
        entityDB.setDescription(details.getDescription());
        entityDB.setApplicationType(details.getType());
        entityDB.setProgrammingLanguage(details.getLang());
        entityDB.setFrameworkUsed(details.getFrameworkUsed());

        projectEntity.setSoftwareDetails(entityDB);
        return repo.save(entityDB);
    }

    public void delete(Long projectID, Long projectSoftDetailsID)
    {
        ProjectEntity projectEntity = m_ProjectEntityService.getById(projectID);
        ProjectSoftwareDetails entityDB = getById(projectSoftDetailsID);
        projectEntity.setSoftwareDetails(null);
        repo.delete(entityDB);
    }
}
