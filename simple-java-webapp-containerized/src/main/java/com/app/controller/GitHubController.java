package com.app.controller;

import com.app.service.GitHubService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/github")
@AllArgsConstructor
public class GitHubController {


    private GitHubService gitHubService;

    @GetMapping("/repos/{username}")
    public String[] getUserRepositories(@PathVariable String username) {
        return gitHubService.getRepositories(username);
    }
}
