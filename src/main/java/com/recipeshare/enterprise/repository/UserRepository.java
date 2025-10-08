package com.recipeshare.enterprise.repository;

import com.recipeshare.enterprise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {}
