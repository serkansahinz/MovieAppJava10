package com.serkan.repository;

import com.serkan.repository.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface IMovieRepository extends JpaRepository<Movie, Long> {


    List<Movie> findAllByPremiredBefore(LocalDate date);
    List<Movie> findAllByRatingGreaterThanEqual(Double rating);
//    Belirli bir Kullanıcının ilgi alanlarını kapsayan filmleri geitiriniz
    List<Movie> findAllByFavUsers(Long id);

    @Query("select u from User")
    List<Movie> favMovieByUserId(@Param("x") Long value);
//    private Set<Long> favMovies;
}
