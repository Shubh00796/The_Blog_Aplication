package com.Personal.blogapplication.Controller;

import com.Personal.blogapplication.Dtos.NoteRequestDTO;
import com.Personal.blogapplication.Dtos.NoteResponseDTO;
import com.Personal.blogapplication.Service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    // Save a note
    @PostMapping
    public ResponseEntity<NoteResponseDTO> saveNote(@RequestBody NoteRequestDTO noteRequestDTO) {
        NoteResponseDTO savedNote = noteService.saveNote(noteRequestDTO);
        return ResponseEntity.ok(savedNote);
    }

    // List all notes
    @GetMapping
    public ResponseEntity<List<NoteResponseDTO>> listNotes() {
        List<NoteResponseDTO> notes = noteService.listNotes();
        return ResponseEntity.ok(notes);
    }

    // Upload a markdown file
    @PostMapping("/upload")
    public ResponseEntity<NoteResponseDTO> uploadMarkdownFile(@RequestParam("file") MultipartFile file) {
        NoteResponseDTO uploadedNote = noteService.uploadMarkdownFile(file);
        return ResponseEntity.ok(uploadedNote);
    }

    // Check grammar
    @PostMapping("/check-grammar")
    public ResponseEntity<String> checkGrammar(@RequestBody String content) {
        NoteService.GrammarChecker grammarChecker = noteService.new GrammarChecker();
        String grammarResult = grammarChecker.checkGrammar(content);
        return ResponseEntity.ok(grammarResult);
    }
}
