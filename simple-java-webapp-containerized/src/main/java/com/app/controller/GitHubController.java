package com.app.controller;

import com.app.service.GitHubService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/github")
@AllArgsConstructor
public class GitHubController {


    private GitHubService gitHubService;

    @GetMapping("/repos/{username}")
    public ResponseEntity<List<String>> getUserRepositories(@PathVariable String username) {
        List<String> repositories = gitHubService.getRepositories(username);
        if (repositories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(repositories);

    }
}
