package com.example.Lab5.daos;

import com.example.Lab5.entities.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MyUser, String> {
    //MyUser findByUsername(String username);
}
