package com.serkan.service;

import com.serkan.repository.IMovieRepository;
import com.serkan.repository.entity.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final IMovieRepository iMovieRepository;

   public List<Movie> findAllByPremiredBefore(String date){
       LocalDate mydate=null;

       try {
           mydate=LocalDate.parse(date);
       } catch (Exception e) {
           throw new RuntimeException(e.toString());
       }
       return iMovieRepository.findAllByPremiredBefore(mydate);
   }
   public List<Movie> findAllByRatingGreaterThanEqual(Double rating){
       return iMovieRepository.findAllByRatingGreaterThanEqual(rating);
   }
   public  List<Movie> findAllByFavUsers(Long id){
       return iMovieRepository.findAllByFavUsers(id);
   }
}
