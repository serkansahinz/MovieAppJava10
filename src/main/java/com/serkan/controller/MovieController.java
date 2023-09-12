package com.serkan.controller;

import static com.serkan.constant.RestApiUrl.*;

import com.serkan.repository.entity.Movie;
import com.serkan.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(MOVIE)
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/before_premired")
    public ResponseEntity<List<Movie>> findAllByPremiredBefore(String date){
        return ResponseEntity.ok(movieService.findAllByPremiredBefore(date));
    }
    @GetMapping("/rating_greater_than")
    public ResponseEntity<List<Movie>> findAllByRatingGreaterThanEqual(@RequestParam(required = false, defaultValue = "1") Double rating){
        return ResponseEntity.ok(movieService.findAllByRatingGreaterThanEqual(rating));
    }

    @GetMapping("/fav_movies")
    public  ResponseEntity<List<Movie>> findAllByFavUsers(Long id){
        return ResponseEntity.ok(movieService.findAllByFavUsers(id));
    }
}
