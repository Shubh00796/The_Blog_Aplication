package com.Personal.blogapplication.Dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class GeminiRequest {
    @JsonProperty("candidates")
    private List<Candidate> candidates;

    public static class Candidate{
        private Content content;

    }
    public static class Content {
        private List<Part> parts;
    }

    public static class Part {
        private String text;
    }
}
