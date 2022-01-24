//
// Created by htrap19 on 1/20/22
//

package com.interfaceict.ptrakcer.controllers;

import com.interfaceict.ptrakcer.dto.requests.NewProjectSoftwareDetail;
import com.interfaceict.ptrakcer.models.ProjectSoftwareDetails;
import com.interfaceict.ptrakcer.services.ProjectSoftwareDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projectSoftDetails")
public class ProjectSoftwareDetailsController
{
    @Autowired
    private ProjectSoftwareDetailsService m_Service;

    @GetMapping("/")
    public List<ProjectSoftwareDetails> getAllProjectSoftDetails() { return m_Service.getAll(); }

    @PostMapping("/")
    public ProjectSoftwareDetails newProjectSoftDetails(@Valid @RequestBody NewProjectSoftwareDetail detail) { return m_Service.save(detail); }

    @PutMapping("/{projectSoftDetailsID}")
    public ProjectSoftwareDetails updateProjectSoftDetails(@Valid @RequestBody NewProjectSoftwareDetail detail, @PathVariable("projectSoftDetailsID") Long projectSoftDetailsID) { return m_Service.update(detail, projectSoftDetailsID); }

    @DeleteMapping("/{projectSoftDetailsID}")
    public String deleteProjectSoftDetails(@PathVariable("projectSoftDetailsID") Long projectSoftDetailsID)
    {
        if (m_Service.delete(projectSoftDetailsID))
            return "Deleted successfully!";

        return "Deletion failed!";
    }
}