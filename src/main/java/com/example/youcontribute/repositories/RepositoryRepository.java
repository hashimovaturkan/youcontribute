package com.example.youcontribute.repositories;

import com.example.youcontribute.models.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;

@org.springframework.stereotype.Repository
public interface RepositoryRepository extends PagingAndSortingRepository<Repository, Integer> {
    List<Repository> findAll();
    void save(Repository r);
    <S extends Repository> List<S> saveAll(Iterable<S> entities);
}
