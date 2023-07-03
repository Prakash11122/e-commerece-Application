package com.Spring.SpringBoot.Dao;

import com.Spring.SpringBoot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public interface UserDao extends JpaRepository<User, Long> {
   // User findByUsername(String username);
    User findByEmailIgnoreCase(String email);
    public User findByResetPasswordToken(String token);
}
