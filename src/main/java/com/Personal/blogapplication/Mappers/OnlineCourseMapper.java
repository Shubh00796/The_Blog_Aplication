package com.Personal.blogapplication.Mappers;

import com.Personal.blogapplication.Dtos.OnlineCourseDTO;
import com.Personal.blogapplication.Entity.OnlineCourse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OnlineCourseMapper {
    OnlineCourse toEntity(OnlineCourseDTO onlineCourseDTO);
    OnlineCourseDTO toDTO(OnlineCourse onlineCourse);
}
