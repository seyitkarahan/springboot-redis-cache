package com.seyitkarahan.redis_cache.service;

import com.seyitkarahan.redis_cache.dto.CreateUserDto;
import com.seyitkarahan.redis_cache.dto.UpdateUserDto;
import com.seyitkarahan.redis_cache.model.User;
import com.seyitkarahan.redis_cache.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Cacheable(value = "users", key = "#root.methodName", unless = "#result == null")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Cacheable(cacheNames = "user_id", key = "#root.methodName + #id", unless = "#result == null")
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @CacheEvict(value = {"users", "user_id"}, allEntries = true)
    public User createUser(CreateUserDto dto) {
        var user = userRepository.save(dto.toEntity(dto));
        return user;
    }

    @CacheEvict(value = {"users", "user_id"}, allEntries = true)
    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User deleted";
    }

    @CachePut(cacheNames = "user_id", key = "'getUserById' + #dto.id", unless = "#result == null")
    public User updateUser(UpdateUserDto dto) {
        Optional<User> user = userRepository.findById(dto.getId());
        if (user.isPresent()) {
            User user1 = user.get();
            user1.setPassword(dto.getPassword());
            return userRepository.save(user1);
        } else {
            return null;
        }
    }
}
















