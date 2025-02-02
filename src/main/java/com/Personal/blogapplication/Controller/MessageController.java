package com.Personal.blogapplication.Controller;

import com.Personal.blogapplication.Dtos.MessageDTO;
import com.Personal.blogapplication.Service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;


    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody MessageDTO messageDTO) {
        messageService.SendMesage(messageDTO);
        return ResponseEntity.ok("Message sent successfully.");
    }

    @GetMapping("/history/{groupName}")
    public ResponseEntity<List<String>> getMessageHistory(@PathVariable String groupName) {
        List<String> history = messageService.getMessageHistory(groupName);
        return ResponseEntity.ok(history);
    }
}
