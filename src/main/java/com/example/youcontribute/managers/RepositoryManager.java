package com.example.youcontribute.managers;

import com.example.youcontribute.models.Repository;
import com.example.youcontribute.service.GithubClient;
import com.example.youcontribute.service.IssueService;
import com.example.youcontribute.service.RepositoryService;
import com.example.youcontribute.service.models.GithubIssueResponse;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.example.youcontribute.models.Issue;

@Service
@AllArgsConstructor
public class RepositoryManager {

    private final RepositoryService repositoryService;

    private final IssueService issueService;

    private final GithubClient githubClient;
    public void importRepository(String organization, String repository) {
        this.repositoryService.create(organization, repository);
    }

    @Async
    public void importIssues(Repository repository) {
        var since = LocalDate.now();
        GithubIssueResponse[] githubIssueResponses = this.githubClient.listIssues(
                repository.getOrganization(), repository.getRepository(), since);
        List<Issue> issues = Arrays.stream(githubIssueResponses).map(githubIssueResponse -> new Issue()).collect(
                Collectors.toList());
        this.issueService.saveAll(issues);
    }
}