//
// Created by htrap19 on 1/8/22
//

package com.interfaceict.ptrakcer.controllers;

import com.interfaceict.ptrakcer.dto.requests.NewProject;
import com.interfaceict.ptrakcer.dto.requests.UpdateProjectStatus;
import com.interfaceict.ptrakcer.models.ProjectEntity;
import com.interfaceict.ptrakcer.services.ProjectEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectEntityController {
	@Autowired
	private ProjectEntityService m_Service;

	@GetMapping("/")
	public List<ProjectEntity> getAllProjects() {
		return m_Service.getAll();
	}

	@PostMapping("/")
	public ProjectEntity newProject(@Valid @RequestBody NewProject newProject) {
		return m_Service.save(newProject);
	}

	@PutMapping("/{id}")
	public ProjectEntity updateProject(@RequestBody NewProject project, @PathVariable("id") Long id) {
		return m_Service.update(project, id);
	}

	@DeleteMapping("/{id}")
	public String deleteProject(@PathVariable("id") Long id) {
		m_Service.delete(id);
		return "Deleted Successfully";
	}

	/*
	 * I used json body to update the project status, because it is better to keep
	 * hidden and not visible in query.
	 */
	@PutMapping("/update_project_status/{id}")
	public boolean updateProjectStatus(@PathVariable("id") Long id, @Valid @RequestBody UpdateProjectStatus status) {
		return m_Service.updateStatus(status.getStatus(), id);
	}
}
