package com.example.youcontribute.controllers.response;

import com.example.youcontribute.controllers.models.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;

public interface RepositoryRepository extends PagingAndSortingRepository<Repository, Integer> {
    List<Repository> findAll();
    void save(Repository r);

    <T> void saveAll(List<T> list);
}
