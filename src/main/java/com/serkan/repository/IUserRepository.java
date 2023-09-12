package com.serkan.repository;

import com.serkan.dto.request.RegisterRequestDto;
import com.serkan.enums.EUserType;
import com.serkan.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    // "select u from User as u where u.email=?1 and u.password=?2";
   
    Optional<User> findByEmailAndPassword(String email, String password);

    Boolean existsByPasswordAndEmail(String password, String email);

    List<User> findAllByUserType(EUserType userType);


    List<User> findAllByOrderByName();
    List<User> findAllByOrderByNameDesc();
    List<User> findAllByNameContainingIgnoreCase(String value);
    List<User> findAllByEmailContainingIgnoreCase(String value);
    List<User> findAllByEmailEndingWithIgnoreCase(String value);

    @Query("select u from User u where length(u.password) > ?1") // ? aşağıdaki int value değerini almamızı sağlıyor
    List<User> passwordlongerThan(int value);

    @Query("select u from User u where length(u.password) > :x ")
    List<User> passwordlongerThan2(@Param("x") int value);

    @Query(value = "select * from tbl_user u where length(u.password) > ?1 ", nativeQuery = true)
    List<User> passwordlongerThan3(int value);



}
