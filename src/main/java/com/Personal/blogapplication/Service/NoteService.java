package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.NoteRequestDTO;
import com.Personal.blogapplication.Dtos.NoteResponseDTO;
import com.Personal.blogapplication.Entity.Note;
import com.Personal.blogapplication.Repo.NoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.SimpleTokenizer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteResponseDTO saveNote(NoteRequestDTO noteRequestDTO) {
        log.info("Creating note with title: {}", noteRequestDTO.getTitle()); // Fixed logging syntax

        Note note = Note.builder()
                .title(noteRequestDTO.getTitle())
                .content(noteRequestDTO.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Note savedNote = noteRepository.save(note);

        return mapToNoteResponseDTO(savedNote);
    }

    private NoteResponseDTO mapToNoteResponseDTO(Note note) {
        return NoteResponseDTO.builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .createdAt(note.getCreatedAt()) // Assuming createdAt in NoteResponseDTO is LocalDateTime
                .updatedAt(note.getUpdatedAt()) // Add updatedAt if needed
                .build();
    }

    public List<NoteResponseDTO> listNotes() {
       return  noteRepository.findAll().stream()

                .map(note -> NoteResponseDTO.builder().id(note.getId())
                        .title(note.getTitle())
                        .content(note.getContent())
                        .createdAt(note.getCreatedAt())
                        .updatedAt(note.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    public NoteResponseDTO uploadMarkdownFile(MultipartFile multipartFile){
        if(multipartFile == null || multipartFile.isEmpty()){
            throw new IllegalArgumentException("File cannot be null or empty");

        }
        String content;
        try (InputStream inputStream = multipartFile.getInputStream()) {
            content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file content: " + e.getMessage(), e);
        }

        Note note = Note.builder()
                .title(multipartFile.getOriginalFilename())
                .content(content)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Note savedNoteForFile = noteRepository.save(note);

        return mapToNoteResponseDTO(savedNoteForFile);
    }
    public class GrammarChecker {
        public String checkGrammar(String content) {
            if (content == null || content.isEmpty()) {
                return "Content is empty. No grammar issues founsssssssssssssssdssssssss.";
            }

            try (InputStream modelIn = getClass().getResourceAsStream("/en-pos-maxent.bin")) {
                POSModel model = new POSModel(modelIn);
                POSTaggerME tagger = new POSTaggerME(model);

                // Tokenize the sentence
                String[] tokens = SimpleTokenizer.INSTANCE.tokenize(content);

                // Get part-of-speech tags
                String[] tags = tagger.tag(tokens);

                // Analyze POS tags for grammar issues (basic example)
                StringBuilder issues = new StringBuilder();
                for (int i = 0; i < tokens.length; i++) {
                    if (tags[i].equals(".")) { // Example rule: punctuation should not follow certain tags
                        issues.append("Potential issue with token: ").append(tokens[i]).append("\n");
                    }
                }

                return issues.length() == 0 ? "Grammar is correct for the given content." : issues.toString();
            } catch (Exception e) {
                throw new RuntimeException("Failed to check grammar: " + e.getMessage(), e);
            }
        }
    }
}