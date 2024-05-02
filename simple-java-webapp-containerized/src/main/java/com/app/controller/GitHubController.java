package com.app.controller;

import com.app.service.GitHubService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/github")
@AllArgsConstructor
public class GitHubController {

    private final GitHubService gitHubService;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @GetMapping("/repos/{username}")
    public ResponseEntity<List<String>> getUserRepositories(@PathVariable String username) {
        List<String> repositories = gitHubService.getRepositories(username);
        if (repositories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(repositories);
    }

    public void init() {
        scheduler.scheduleAtFixedRate(this::callGitHub, 0, 2, TimeUnit.MINUTES);
    }

    private void callGitHub() {
        try {
            ResponseEntity<List<String>> response = getUserRepositories("soaresmaric");
            List<String> repositories = response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        scheduler.shutdown();
    }
}
