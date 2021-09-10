package com.leizhou.repository;

import com.leizhou.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT id,name,password,status,created_date FROM account WHERE name = ?1", nativeQuery = true)
    User selectUser(String username);
}
