package com.example.youcontribute.repositories;

import java.util.List;
import java.util.Optional;

import com.example.youcontribute.models.Repository;
import com.example.youcontribute.models.Issue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IssueRepository extends PagingAndSortingRepository<Issue, Integer> {

    List<Issue> findAll();

    List<Issue> findByRepository(Repository repository);

    @Query(value = "select * from issue where id not in (select issue_id from issue_challenge) order by rand() limit 1", nativeQuery = true)
    Optional<Issue> findRandomIssue();

    Optional<Issue> findByGithubIssueId(Long githubIssueId);
}