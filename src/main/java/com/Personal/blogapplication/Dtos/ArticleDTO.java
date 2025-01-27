package com.Personal.blogapplication.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {

    private Long id;
    private String title;
    private String content;
    private LocalDate publicationDate;

    @Override
    public String toString() {
        return "ArticleDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", publicationDate=" + publicationDate +
                '}';
    }
}
