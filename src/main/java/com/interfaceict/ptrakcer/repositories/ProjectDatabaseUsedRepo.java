//
// Created by htrap19 on 1/20/22
//

package com.interfaceict.ptrakcer.repositories;

import com.interfaceict.ptrakcer.models.ProjectDatabaseUsed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDatabaseUsedRepo extends JpaRepository<ProjectDatabaseUsed, Long>
{
}
