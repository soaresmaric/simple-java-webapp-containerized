package com.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@AllArgsConstructor
public class GitHubService {

    private static final String GITHUB_API_URL = "https://api.github.com/users/{username}/repos";

    private RestTemplate restTemplate;

    public List<String> getRepositories(String username) {

        Map<String, Object>[] response = restTemplate.getForObject(GITHUB_API_URL, Map[].class, username);
        List<String> repositories = new ArrayList<>();

        if (response != null) {
            for (Map<String, Object> repo : response) {
                String repoName = repo.get("name").toString();
                repositories.add(repoName);
            }
        }

        return repositories;
    }
}

