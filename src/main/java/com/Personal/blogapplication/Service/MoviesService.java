package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.MovieDTO;
import com.Personal.blogapplication.Entity.Movie;
import com.Personal.blogapplication.Mappers.MovieMapper;
import com.Personal.blogapplication.Repo.MovieRepository;
import com.Personal.blogapplication.Utils.UpdateUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MoviesService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public MovieDTO addMovies(MovieDTO movieDTO){
        Movie savedToEntity = movieMapper.toEntity(movieDTO);
        Movie savedToRepo = movieRepository.save(savedToEntity);
        return movieMapper.toDTO(savedToRepo);
    }

    public MovieDTO updateMovies(Long id, MovieDTO movieDTO){
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie id not found you have given"));

        if(movie.getTitle() != null){
            movie.setTitle(movieDTO.getTitle());
        }
        if(movie.getGenre() != null){
            movie.setGenre(movieDTO.getGenre());
        }
        if(movie.getDescription() != null){
            movie.setDescription(movieDTO.getDescription());
        }
        if(movie.getPosterImage() != null){
            movie.setPosterImage(movieDTO.getPosterImage());
        }

        Movie updatedMovies = movieRepository.save(movie);
        return movieMapper.toDTO(updatedMovies);
    }


//    public MovieDTO updateMovie(Long id, MovieDTO movieDTO) {
//        Movie movie = movieRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Movie with ID " + id + " not found"));
//
//        UpdateUtil.updateIfNotNull(movieDTO, movie);
//
//        return movieMapper.toDTO(movieRepository.save(movie));
//    }
    public List<MovieDTO> getAllMovies(){
        return movieRepository.findAll().stream()
                .map(movieMapper::toDTO)
                .collect(Collectors.toList());

    }
    public List<MovieDTO> getMoviesById(Long id){
        return movieRepository.findById(id).stream()
                .map(movieMapper::toDTO)
                .collect(Collectors.toList());

    }
    public List<MovieDTO> findByGenre(String genre){
        return movieRepository.findByGenre(genre).stream()
                .map(movieMapper::toDTO)
                .collect(Collectors.toList());

    }
}
