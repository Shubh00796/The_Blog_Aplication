package com.Personal.blogapplication.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OnlineCourseDTO {
    private String id;
    private String title;
    private String description;
    private String instructorName;
}
