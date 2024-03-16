package com.example.youcontribute.service;

import com.example.youcontribute.config.GithubProperties;
import com.example.youcontribute.service.models.GithubIssueResponse;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class GithubClient {

    private final RestTemplate restTemplate;

    private final GithubProperties githubProperties;

    public GithubClient(RestTemplate restTemplate, GithubProperties githubProperties) {
        this.restTemplate = restTemplate;
        this.githubProperties = githubProperties;
    }


    public GithubIssueResponse[] listIssues(String owner, String repository, LocalDate date){
        String issuesUrl = String.format("%s/repos/%s/%s/issues", githubProperties.getApiUrl(),owner, repository);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",  "token " + this.githubProperties.getToken());
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<GithubIssueResponse[]> response = restTemplate.exchange(issuesUrl, HttpMethod.GET, request, GithubIssueResponse[].class);
        return response.getBody();

    }


}
