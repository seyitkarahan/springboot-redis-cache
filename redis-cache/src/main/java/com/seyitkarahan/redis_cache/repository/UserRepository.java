package com.seyitkarahan.redis_cache.repository;

import com.seyitkarahan.redis_cache.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
