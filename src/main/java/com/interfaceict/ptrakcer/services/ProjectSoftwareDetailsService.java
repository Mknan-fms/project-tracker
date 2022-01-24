//
// Created by htrap19 on 1/20/22
//

package com.interfaceict.ptrakcer.services;

import com.interfaceict.ptrakcer.dto.requests.NewProjectSoftwareDetail;
import com.interfaceict.ptrakcer.models.ProjectSoftwareDetails;
import com.interfaceict.ptrakcer.repositories.ProjectSoftwareDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectSoftwareDetailsService
{
    @Autowired
    private ProjectSoftwareDetailsRepo m_ProjectSoftDetailsRepo;

    public List<ProjectSoftwareDetails> getAll() { return new ArrayList<>(m_ProjectSoftDetailsRepo.findAll()); }

    public ProjectSoftwareDetails save(@Valid NewProjectSoftwareDetail detail)
    {
        ProjectSoftwareDetails softwareDetails = new ProjectSoftwareDetails();
        softwareDetails.setName(detail.getName());
        softwareDetails.setDescription(detail.getDescription());
        softwareDetails.setApplicationType(detail.getType());
        softwareDetails.setProgrammingLanguage(detail.getLang());
        softwareDetails.setFrameworkUsed(detail.getFrameworkUsed());

        return m_ProjectSoftDetailsRepo.save(softwareDetails);
    }

    public ProjectSoftwareDetails update(@Valid NewProjectSoftwareDetail details, Long projectSoftDetailsID)
    {
        Optional<ProjectSoftwareDetails> entityDBOpt = m_ProjectSoftDetailsRepo.findById(projectSoftDetailsID);
        if (entityDBOpt.isEmpty()) return null;

        ProjectSoftwareDetails entityDB = entityDBOpt.get();
        entityDB.setName(details.getName());
        entityDB.setDescription(details.getDescription());
        entityDB.setApplicationType(details.getType());
        entityDB.setProgrammingLanguage(details.getLang());
        entityDB.setFrameworkUsed(details.getFrameworkUsed());

        return m_ProjectSoftDetailsRepo.save(entityDB);
    }

    public Boolean delete(Long projectSoftDetailsID)
    {
        Optional<ProjectSoftwareDetails> entityDBOpt = m_ProjectSoftDetailsRepo.findById(projectSoftDetailsID);
        if (entityDBOpt.isEmpty()) return false;

        m_ProjectSoftDetailsRepo.delete(entityDBOpt.get());
        return true;
    }
}
