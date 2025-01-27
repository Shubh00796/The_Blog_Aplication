package com.Personal.blogapplication.Exceptions;

public class ArticleNotFoundException extends Exception {
//    public ArticleNotFoundException() {
//        super("Article not found");
//    }

    public ArticleNotFoundException(String message) {
        super(message);
    }
}