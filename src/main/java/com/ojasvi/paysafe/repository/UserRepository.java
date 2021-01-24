package com.ojasvi.paysafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ojasvi.paysafe.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
