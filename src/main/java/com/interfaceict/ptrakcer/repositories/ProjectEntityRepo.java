//
// Created by htrap19 on 1/8/22
//

package com.interfaceict.ptrakcer.repositories;

import com.interfaceict.ptrakcer.models.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectEntityRepo extends JpaRepository<ProjectEntity, Long>
{
}
