package com.recipeshare.enterprise.repository;

import com.recipeshare.enterprise.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUserNameAndUserPassword(String userName, String userPassword);
    Optional<Users> findByUserName(String userName);
}
