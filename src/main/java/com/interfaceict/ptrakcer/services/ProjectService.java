//
// Created by htrap19 on 1/8/22
//

package com.interfaceict.ptrakcer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public class ProjectService<T, Repo extends JpaRepository<T, Long>>
{
    @Autowired
    protected Repo m_Repo;

    public List<T> getAll() { return new ArrayList<>(m_Repo.findAll()); }

    public void save(T entity) { m_Repo.save(entity); }

    public void delete(Long id) { m_Repo.deleteById(id); }
}
