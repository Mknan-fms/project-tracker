package com.interfaceict.ptrakcer.services.helpers;

import com.interfaceict.ptrakcer.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class GeneralServiceHelper<T, R extends JpaRepository<T, Long>>
{
    private static final String notFoundString = "Entity with id: %s not found!";

    @Autowired
    protected R repo;

    public List<T> getAll() { return repo.findAll(); }

    public T getById(Long id)
    {
        Optional<T> entityDBOpt = repo.findById(id);
        if (entityDBOpt.isEmpty()) throw new NotFoundException(String.format(notFoundString, id));

        return entityDBOpt.get();
    }
}
