package com.example.youcontribute.controllers.response;

import com.example.youcontribute.controllers.models.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;

@org.springframework.stereotype.Repository
public interface RepositoryRepository extends PagingAndSortingRepository<Repository, Integer> {
    List<Repository> findAll();
    void save(Repository r);
    <S extends Repository> List<S> saveAll(Iterable<S> entities);
}
