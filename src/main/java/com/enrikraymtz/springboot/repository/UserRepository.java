package com.enrikraymtz.springboot.repository;

import java.io.Serializable;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.enrikraymtz.springboot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Serializable> {
    @Query("select u from User u left join fetch u.roles r where u.username=:username")
    public Optional<User> findByUsername(@Param("username") String username);
}
