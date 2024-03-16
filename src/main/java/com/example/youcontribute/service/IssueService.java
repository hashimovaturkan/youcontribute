package com.example.youcontribute.service;


import com.example.youcontribute.models.Issue;
import com.example.youcontribute.repositories.IssueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;

    private final RepositoryService repositoryService;

    @Transactional
    public void saveAll(List<Issue> issues) {

    }


}
