package com.recipeshare.enterprise.repository;

import com.recipeshare.enterprise.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {}
