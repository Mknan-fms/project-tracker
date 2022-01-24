//
// Created by htrap19 on 1/20/22
//

package com.interfaceict.ptrakcer.controllers;

import com.interfaceict.ptrakcer.dto.requests.NewDatabase;
import com.interfaceict.ptrakcer.models.ProjectDatabaseUsed;
import com.interfaceict.ptrakcer.services.ProjectDatabaseUsedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/database")
public class ProjectDatabaseUsedController {
	@Autowired
	private ProjectDatabaseUsedService m_Service;

	@GetMapping("/{projectSoftDetailsID}")
	public List<ProjectDatabaseUsed> getAllDatabase(@PathVariable("projectSoftDetailsID") Long projectSoftDetailsID) {
		return m_Service.getAllByProjectSoftID(projectSoftDetailsID);
	}

	@PostMapping("/")
	public ProjectDatabaseUsed newDatabase(@Valid @RequestBody NewDatabase database) {
		return m_Service.save(database);
	}

	@PutMapping("/{dbUsedID}")
	public ProjectDatabaseUsed updateDatabase(@Valid @RequestBody NewDatabase database,
			@PathVariable("dbUsedID") Long dbUsedID) {
		return m_Service.update(database, dbUsedID);
	}

	@DeleteMapping("/{projectSoftDetailsID}/{dbUsedID}")
	public String deleteDatabase(@PathVariable("projectSoftDetailsID") Long projectSoftDetailsID,
			@PathVariable("dbUsedID") Long dbUsedID) {
		if (m_Service.delete(projectSoftDetailsID, dbUsedID))
			return "Deleted successfully!";

		return "Deletion failed!";
	}
}
