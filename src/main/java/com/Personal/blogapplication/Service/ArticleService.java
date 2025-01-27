package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.ArticleDTO;
import com.Personal.blogapplication.Entity.Article;
import com.Personal.blogapplication.Exceptions.ArticleNotFoundException;
import com.Personal.blogapplication.Repo.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<ArticleDTO> getAllArticles() {
        log.info("Fetching all articles sorted by publication date.");
        return articleRepository.findAllByOrderByPublicationDateDesc()
                .stream()
                .map(article -> new ArticleDTO(
                        article.getId(),
                        article.getTitle(),
                        article.getContent(),
                        article.getPublicationDate()
                ))
                .collect(Collectors.toList());
    }

    public ArticleDTO getArticleById(Long id) throws ArticleNotFoundException {
        log.info("Fetching article with ID: {}", id);

        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article with ID " + id + " not found."));
        return new ArticleDTO(article.getId(), article.getTitle(), article.getContent(), article.getPublicationDate());
    }

    public ArticleDTO saveArticle(ArticleDTO articleDTO) {
        log.info("Saving a new article with title: {}", articleDTO.getTitle());
        Article article = new Article();
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        article.setPublicationDate(articleDTO.getPublicationDate());

        Article savedArticle = articleRepository.save(article);
        return new ArticleDTO(savedArticle.getId(), savedArticle.getTitle(), savedArticle.getContent(), savedArticle.getPublicationDate());
    }

    public ArticleDTO updateArticle(Long id, ArticleDTO articleDTO) throws ArticleNotFoundException {
        log.info("Updating article with ID: {}", id);
        Article existingArticle = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article with ID " + id + " not found."));
        existingArticle.setTitle(articleDTO.getTitle());
        existingArticle.setContent(articleDTO.getContent());
        existingArticle.setPublicationDate(articleDTO.getPublicationDate());

        Article updatedArticle = articleRepository.save(existingArticle);
        return new ArticleDTO(updatedArticle.getId(), updatedArticle.getTitle(), updatedArticle.getContent(), updatedArticle.getPublicationDate());
    }

    public void deleteArticle(Long id) throws ArticleNotFoundException {
        log.info("Deleting article with ID: {}", id);
        if (!articleRepository.existsById(id)) {
            throw new ArticleNotFoundException("Article with ID " + id + " not found.");
        }
        articleRepository.deleteById(id);
        log.info("Article with ID: {} successfully deleted.", id);
    }
}
