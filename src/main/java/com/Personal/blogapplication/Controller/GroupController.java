package com.Personal.blogapplication.Controller;

import com.Personal.blogapplication.Service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;



    @PostMapping("/{groupName}/add/{username}")
    public ResponseEntity<String> addUserToGroup(@PathVariable String groupName, @PathVariable String username) {
        groupService.addUserToGroup(groupName, username);
        return ResponseEntity.ok("User added to group.");
    }

    @PostMapping("/{groupName}/remove/{username}")
    public ResponseEntity<String> removeUserFromGroup(@PathVariable String groupName, @PathVariable String username) {
        groupService.removeUserFromGroup(groupName, username);
        return ResponseEntity.ok("User removed from group.");
    }

    @GetMapping("/{groupName}/members")
    public ResponseEntity<Set<String>> getGroupMembers(@PathVariable String groupName) {
        Set<String> members = groupService.getGroupMembers(groupName);
        return ResponseEntity.ok(members);
    }
}