package com.serkan.service;

import com.serkan.dto.request.RegisterRequestDto;
import com.serkan.repository.IUserRepository;
import com.serkan.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User register(RegisterRequestDto dto) {
        User user = User.builder().phone(dto.getPhone()).name(dto.getName()).surName(dto.getSurName()).
                email(dto.getEmail()).password(dto.getPassword()).build();
        return userRepository.save(user);
    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new RuntimeException("Kullanıcı Bulunamadı");
        }

        return user.get();
    }
}
