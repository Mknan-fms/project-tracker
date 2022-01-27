//
// Created by htrap19 on 1/20/22
//

package com.interfaceict.ptrakcer.services;

import com.interfaceict.ptrakcer.dto.requests.NewDependency;
import com.interfaceict.ptrakcer.models.ProjectDependency;
import com.interfaceict.ptrakcer.models.ProjectEntity;
import com.interfaceict.ptrakcer.repositories.ProjectDependencyRepo;
import com.interfaceict.ptrakcer.services.helpers.GeneralServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class ProjectDependencyService extends GeneralServiceHelper<ProjectDependency, ProjectDependencyRepo>
{
    @Autowired
    private ProjectEntityService m_ProjectService;

    public List<ProjectDependency> getAllByProjectID(Long projectID)
    {
        return m_ProjectService.getById(projectID).getDependencies();
    }

    public ProjectDependency save(@Valid NewDependency dependency)
    {
        ProjectEntity entityDB = m_ProjectService.getById(dependency.getProjectID());

        ProjectDependency dep = new ProjectDependency();
        dep.setName(dependency.getName());
        dep.setDescription(dependency.getDescription());
        dep.setStatus(dependency.getStatus());

        entityDB.getDependencies().add(dep);
        return repo.save(dep);
    }

    public ProjectDependency update(@Valid NewDependency dependency, Long depID)
    {
        ProjectDependency entityDB = getById(depID);
        entityDB.setName(dependency.getName());
        entityDB.setDescription(dependency.getDescription());
        entityDB.setStatus(dependency.getStatus());

        return repo.save(entityDB);
    }

    public void delete(Long projectID, Long depID)
    {
        ProjectDependency depDB = getById(depID);
        ProjectEntity projectDB = m_ProjectService.getById(projectID);

        projectDB.getDependencies().removeIf(dep -> depID.equals(dep.getID()));
        repo.delete(depDB);
    }
}
