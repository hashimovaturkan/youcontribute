package com.example.youcontribute.service;

import com.example.youcontribute.controllers.models.Repository;
import com.example.youcontribute.controllers.response.RepositoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RepositoryService {

    private final RepositoryRepository repositoryRepository;

    public RepositoryService(RepositoryRepository repositoryRepository) {
        this.repositoryRepository = repositoryRepository;
    }

    @Transactional
    public void create(String organization, String repository) {
        Repository r = Repository.builder().organization(organization).repository(repository).build();
        repositoryRepository.save(r);

    }

    public List<Repository> list() {
        return repositoryRepository.findAll();
    }
}
