//
// Created by htrap19 on 1/17/22
//

package com.interfaceict.ptrakcer.services;

import com.interfaceict.ptrakcer.dto.requests.NewAsset;
import com.interfaceict.ptrakcer.models.ProjectAsset;
import com.interfaceict.ptrakcer.models.ProjectEntity;
import com.interfaceict.ptrakcer.repositories.ProjectAssetRepo;
import com.interfaceict.ptrakcer.services.helpers.GeneralServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class ProjectAssetService extends GeneralServiceHelper<ProjectAsset, ProjectAssetRepo>
{
    @Autowired
    private ProjectEntityService m_ProjectService;

    public List<ProjectAsset> getAllByProjectID(Long id)
    {
        return m_ProjectService.getById(id).getAssets();
    }

    public ProjectAsset save(@Valid NewAsset newAsset)
    {
        ProjectEntity projectDB = m_ProjectService.getById(newAsset.getProjectID());

        ProjectAsset asset = new ProjectAsset();
        asset.setName(newAsset.getName());
        asset.setDescription(newAsset.getDescription());
        asset.setAssetType(newAsset.getType());

        String decodedString = newAsset.decodeAssetFile();
        if (!decodedString.isEmpty())
            asset.encodeAssetFile(decodedString);

        projectDB.getAssets().add(asset);
        return repo.save(asset);
    }

    public ProjectAsset update(@Valid NewAsset entity, Long assetID)
    {
        ProjectAsset entityDB = getById(assetID);

        entityDB.setName(entity.getName());
        entityDB.setDescription(entity.getDescription());
        entityDB.setAssetType(entity.getType());

        String decodedString = entity.decodeAssetFile();
        if (!decodedString.isEmpty())
            entityDB.encodeAssetFile(decodedString);

        return repo.save(entityDB);
    }

    public void delete(Long projectID, Long assetID)
    {
        ProjectEntity projectDB = m_ProjectService.getById(projectID);
        ProjectAsset projectAssetDB = getById(assetID);

        projectDB.getAssets().removeIf(asset -> assetID.equals(asset.getID()));

        repo.delete(projectAssetDB);
    }
}
