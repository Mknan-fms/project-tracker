//
// Created by htrap19 on 1/20/22
//

package com.interfaceict.ptrakcer.controllers;

import com.interfaceict.ptrakcer.dto.requests.NewDependency;
import com.interfaceict.ptrakcer.models.ProjectDependency;
import com.interfaceict.ptrakcer.services.ProjectDependencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dependencies")
public class ProjectDependencyController
{
    @Autowired
    private ProjectDependencyService m_Service;

    @GetMapping("/{project_id}")
    public List<ProjectDependency> getAllDeps(@PathVariable("project_id") Long projectID) { return new ArrayList<>(m_Service.getAllByProjectID(projectID)); }

    @PostMapping("/")
    public ProjectDependency newDep(@Valid @RequestBody NewDependency dependency) { return m_Service.save(dependency); }

    @PutMapping("/{dep_id}")
    public ProjectDependency updateDep(@RequestBody NewDependency dependency, @PathVariable("dep_id") Long depID) { return m_Service.update(dependency, depID); }

    @DeleteMapping("/{project_id}/{dep_id}")
    public String deleteDep(@PathVariable("project_id") Long projectID, @PathVariable("dep_id") Long depID)
    {
        if (m_Service.delete(projectID, depID))
            return "Deleted successfully!";

        return "Deletion failed!";
    }
}
