//
// Created by htrap19 on 1/20/22
//

package com.interfaceict.ptrakcer.services;

import com.interfaceict.ptrakcer.dto.requests.NewDependency;
import com.interfaceict.ptrakcer.models.ProjectDependency;
import com.interfaceict.ptrakcer.models.ProjectEntity;
import com.interfaceict.ptrakcer.repositories.ProjectDependencyRepo;
import com.interfaceict.ptrakcer.repositories.ProjectEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectDependencyService
{
    @Autowired
    private ProjectDependencyRepo m_ProjectDepRepo;

    @Autowired
    private ProjectEntityRepo m_ProjectRepo;

    public List<ProjectDependency> getAll() { return new ArrayList<>(m_ProjectDepRepo.findAll()); }

    public List<ProjectDependency> getAllByProjectID(Long projectID)
    {
        Optional<ProjectEntity> entityDBOpt = m_ProjectRepo.findById(projectID);
        if (entityDBOpt.isEmpty()) return null;

        return entityDBOpt.get().getDependencies();
    }

    public ProjectDependency save(@Valid NewDependency dependency)
    {
        Optional<ProjectEntity> entityDBOpt = m_ProjectRepo.findById(dependency.getProjectID());
        if (entityDBOpt.isEmpty()) return null;

        ProjectDependency dep = new ProjectDependency();
        dep.setName(dependency.getName());
        dep.setDescription(dependency.getDescription());
        dep.setStatus(dependency.getStatus());

        entityDBOpt.get().getDependencies().add(dep);
        m_ProjectDepRepo.save(dep);
        return dep;
    }

    public ProjectDependency update(@Valid NewDependency dependency, Long depID)
    {
        Optional<ProjectDependency> entityDBOpt = m_ProjectDepRepo.findById(depID);
        if (entityDBOpt.isEmpty()) return null;

        ProjectDependency entityDB = entityDBOpt.get();
        entityDB.setName(dependency.getName());
        entityDB.setDescription(dependency.getDescription());
        entityDB.setStatus(dependency.getStatus());

        return m_ProjectDepRepo.save(entityDB);
    }

    public Boolean delete(Long projectID, Long depID)
    {
        Optional<ProjectDependency> depDBOpt = m_ProjectDepRepo.findById(depID);
        Optional<ProjectEntity> projectDBOpt = m_ProjectRepo.findById(projectID);
        if (depDBOpt.isEmpty() || projectDBOpt.isEmpty()) return false;

        projectDBOpt.get().getDependencies().removeIf(dep -> depID.equals(dep.getID()));
        m_ProjectDepRepo.delete(depDBOpt.get());
        return true;
    }
}
