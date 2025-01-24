package com.Personal.blogapplication.Controller;

import com.Personal.blogapplication.Dtos.ActivityDTO;
import com.Personal.blogapplication.Service.GitHubActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GitHubActivityController {

    @Autowired
    private GitHubActivityService gitHubActivityService;

    @GetMapping("/github/activity")
    public List<ActivityDTO> getUserActivity(@RequestParam String username) {
        return gitHubActivityService.getUserActivity(username);
    }
}
