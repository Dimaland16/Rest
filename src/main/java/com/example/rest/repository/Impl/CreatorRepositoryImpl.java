package com.example.rest.repository.Impl;

import com.example.rest.entity.Creator;
import com.example.rest.repository.CreatorRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CreatorRepositoryImpl implements CreatorRepository {

    private final Map<Long, Creator> creators = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public Creator create(Creator creator) {
        creator.setId(nextId++);
        creators.put(creator.getId(), creator);
        return creator;
    }

    @Override
    public Creator update(Creator updatedCreator) {

        if (!creators.containsKey(updatedCreator.getId())) {
            throw new IllegalArgumentException("Creator with ID " + updatedCreator.getId() + " not found");
        }
        creators.put(updatedCreator.getId(), updatedCreator);
        return updatedCreator;
    }

    @Override
    public void deleteById(Long id) {
        creators.remove(id);
    }

    @Override
    public List<Creator> findAll() {
        return creators.values().stream().toList();
    }

    @Override
    public Optional<Creator> findById(Long id) {
        return Optional.ofNullable(creators.get(id));
    }
}
