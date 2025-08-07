package com.home.login.repository;

import com.home.login.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUsername(String username);


    Optional<UserEntity> findFirstByUsernameAndPassword(String username, String password);

}
