//
// Created by htrap19 on 1/17/22
//

package com.interfaceict.ptrakcer.controllers;

import com.interfaceict.ptrakcer.dto.requests.NewAsset;
import com.interfaceict.ptrakcer.models.ProjectAsset;
import com.interfaceict.ptrakcer.services.ProjectAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/assets")
public class ProjectAssetController {
	@Autowired
	private ProjectAssetService m_AssetService;

	@GetMapping("/{project_id}")
	public List<ProjectAsset> getAllProjectAssets(@PathVariable("project_id") Long projectID) {
		return m_AssetService.getAllByProjectID(projectID);
	}
	
	@GetMapping
	public List<ProjectAsset> getAllAssets() {
		return m_AssetService.getAll();
	}

	@PostMapping("/")
	public ProjectAsset newAsset(@Valid @RequestBody NewAsset newAsset) {
		return m_AssetService.save(newAsset);
	}

	@PutMapping("/{id}")
	public ProjectAsset updateAsset(@RequestBody NewAsset asset, @PathVariable("id") Long id) {
		return m_AssetService.update(asset, id);
	}

	@DeleteMapping("/{project_id}/{asset_id}")
	public String deleteAsset(@PathVariable("project_id") Long projectID, @PathVariable("asset_id") Long assetID) {
		if (m_AssetService.delete(projectID, assetID))
			return "Deleted successfully!";

		return "Deletion failed!";
	}
}
