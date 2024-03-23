package com.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class GitHubService {

    private static final String GITHUB_API_URL = "https://api.github.com/users/{username}/repos";


    private RestTemplate restTemplate;

    public String[] getRepositories(String username) {
        return restTemplate.getForObject(GITHUB_API_URL, String[].class, username);
    }
}

