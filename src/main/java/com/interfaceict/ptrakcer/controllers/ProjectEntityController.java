//
// Created by htrap19 on 1/8/22
//

package com.interfaceict.ptrakcer.controllers;

import com.interfaceict.ptrakcer.dto.requests.NewProject;
import com.interfaceict.ptrakcer.models.ProjectEntity;
import com.interfaceict.ptrakcer.services.ProjectEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProjectEntityController
{
    @Autowired
    private ProjectEntityService m_Service;

    @GetMapping("/projects")
    public List<ProjectEntity> getAllProjects() { return m_Service.getAll(); }

    @PostMapping("/projects")
    public ProjectEntity newProject(@Valid @RequestBody NewProject newProject) { return m_Service.save(newProject); }
    
    @PutMapping("/projects/{id}")
    public ProjectEntity updateProject(@RequestBody ProjectEntity project, @PathVariable("id") Long id) { return m_Service.update(project, id); }

    @DeleteMapping("/projects/{id}")
    public String deleteProject(@PathVariable("id") Long id) { m_Service.delete(id); return "Deleted Successfully"; }
}
