package com.projectbuddy2.repositories;


import com.projectbuddy2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllById(User id);
    Optional<User> findById(User id);
    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);
    Boolean existsUserByEmail(String email);
    Boolean existsUserByName(String name);

    Optional<User> findByNameOrEmail(String userNameOrEmail, String userNameorEmail);
}
