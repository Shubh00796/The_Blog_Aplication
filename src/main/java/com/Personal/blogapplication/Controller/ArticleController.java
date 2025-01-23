package com.Personal.blogapplication.Controller;

import com.Personal.blogapplication.Dtos.ArticleDTO;
import com.Personal.blogapplication.Exceptions.ArticleNotFoundException;
import com.Personal.blogapplication.Service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;


    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getAllArticles(){
        log.info("Fetching all articles.");
        List<ArticleDTO> allArticles = articleService.getAllArticles();
        return ResponseEntity.ok(allArticles);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable Long id) throws ArticleNotFoundException {
        log.info("Fetching article with ID: {}", id);
        ArticleDTO articleById = articleService.getArticleById(id);
        return ResponseEntity.ok(articleById);


    }

    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@RequestBody ArticleDTO articleDTO){
        log.info("Creating a new article with title: {}", articleDTO.getTitle());
        ArticleDTO articleDTO1 = articleService.saveArticle(articleDTO);
        return ResponseEntity.status(201).body(articleDTO1);
    }

    @PutMapping("{id}")
  public  ResponseEntity<ArticleDTO> updateArticle(@PathVariable Long id, @RequestBody ArticleDTO articleDTO) throws ArticleNotFoundException {
        log.info("Updating article with ID: {}", id);
        ArticleDTO updatedArticle = articleService.updateArticle(id, articleDTO);
        return ResponseEntity.status(200).body(updatedArticle);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) throws ArticleNotFoundException {
        log.info("Deleting article with ID: {}", id);
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
}
