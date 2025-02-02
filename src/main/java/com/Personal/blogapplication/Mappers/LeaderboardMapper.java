package com.Personal.blogapplication.Mappers;

import com.Personal.blogapplication.Dtos.LeaderboardEntryDTO;
import com.Personal.blogapplication.Entity.LeaderboardEntry;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LeaderboardMapper {
    LeaderboardEntryDTO toDto(LeaderboardEntry entry);
    LeaderboardEntry toEntity(LeaderboardEntryDTO dto);
}