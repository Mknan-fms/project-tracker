//
// Created by htrap19 on 1/17/22
//

package com.interfaceict.ptrakcer.services;

import com.interfaceict.ptrakcer.dto.requests.NewAsset;
import com.interfaceict.ptrakcer.models.ProjectAsset;
import com.interfaceict.ptrakcer.models.ProjectEntity;
import com.interfaceict.ptrakcer.repositories.ProjectAssetRepo;
import com.interfaceict.ptrakcer.repositories.ProjectEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectAssetService
{
    @Autowired
    private ProjectAssetRepo m_AssetRepo;

    @Autowired
    private ProjectEntityRepo m_ProjectRepo;

    public List<ProjectAsset> getAll() { 
    	//TODO: no need for new ArrayList constructor
    	// `findAll` already returns a List
    	//return new ArrayList<>(m_AssetRepo.findAll());
    	
    	return m_AssetRepo.findAll();
    }

    public List<ProjectAsset> getAllByProjectID(Long id)
    {
        Optional<ProjectEntity> entityDBOpt = m_ProjectRepo.findById(id);
        if (entityDBOpt.isEmpty()) return null;

        // Found a valid project
        return entityDBOpt.get().getAssets();
    }

    public ProjectAsset save(@Valid NewAsset newAsset)
    {
        Optional<ProjectEntity> entityDBOpt = m_ProjectRepo.findById(newAsset.getProjectID());
        if (entityDBOpt.isEmpty()) return null;

        ProjectAsset asset = new ProjectAsset();
        asset.setName(newAsset.getName());
        asset.setDescription(newAsset.getDescription());
        asset.setAssetType(newAsset.getType());

        String decodedString = newAsset.decodeAssetFile();
        if (!decodedString.isEmpty())
            asset.encodeAssetFile(decodedString);

        entityDBOpt.get().getAssets().add(asset);
        m_AssetRepo.save(asset);
        return asset;
    }

    public ProjectAsset update(@Valid NewAsset entity, Long assetID)
    {
        Optional<ProjectAsset> entityDBOpt = m_AssetRepo.findById(assetID);
        if (entityDBOpt.isEmpty()) return null;

        ProjectAsset entityDB = entityDBOpt.get();

        entityDB.setName(entity.getName());
        entityDB.setDescription(entity.getDescription());
        entityDB.setAssetType(entity.getType());

        String decodedString = entity.decodeAssetFile();
        if (!decodedString.isEmpty())
            entityDB.encodeAssetFile(decodedString);

        return m_AssetRepo.save(entityDB);
    }

    public boolean delete(Long projectID, Long assetID)
    {
        Optional<ProjectEntity> projectDBOpt = m_ProjectRepo.findById(projectID);
        Optional<ProjectAsset> assetDBOpt = m_AssetRepo.findById(assetID);
        if (assetDBOpt.isEmpty() || projectDBOpt.isEmpty()) return false;

        ProjectEntity projectDB = projectDBOpt.get();

        projectDB.getAssets().removeIf(asset -> assetID.equals(asset.getID()));

        m_AssetRepo.deleteById(assetID);
        return true;
    }
}
