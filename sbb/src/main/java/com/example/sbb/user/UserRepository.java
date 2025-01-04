package com.example.sbb.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
    // id로 유저 불러오기
    Optional<SiteUser> findByusername(String username);
}
