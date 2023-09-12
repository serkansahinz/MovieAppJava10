package com.serkan.service;

import com.serkan.dto.request.LoginRequestDto;
import com.serkan.dto.request.RegisterRequestDto;
import com.serkan.dto.response.UserResponseDto;
import com.serkan.mapper.IUserMapper;
import com.serkan.repository.IUserRepository;
import com.serkan.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User register(RegisterRequestDto dto) {
//        User user = User.builder().phone(dto.getPhone()).name(dto.getName()).surName(dto.getSurName()).
//                email(dto.getEmail()).password(dto.getPassword()).build();
//
        User user=IUserMapper.INSTANCE.toUser(dto);
        return userRepository.save(user);

    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new RuntimeException("Kullanıcı Bulunamadı");
        }

        return user.get();
    }

    public UserResponseDto login(LoginRequestDto dto) {
        Optional<User> user=userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if(user.isEmpty()){
            throw new RuntimeException("Kullanıcı Bulunamadı");
        }
//        return UserResponseDto.builder()
//                .id(user.get().getId())
//                .userType(user.get().getUserType())   Buna gerek yok Mapping, Instance ile halettik
//                .email(user.get().getEmail())
//                .name(user.get().getName())
//                .surName(user.get().getSurName())
//                .phone(user.get().getPhone())
//                .build();
        return IUserMapper.INSTANCE.toUserResponseDto(user.get());

    }
    public List<User> findAllByOrderByName(){
        return userRepository.findAllByOrderByName();
    }

    public List<User> findAllByOrderByNameDesc(){
        return userRepository.findAllByOrderByNameDesc();
    }

    public List<UserResponseDto> findAllByNameContainingIgnoreCase(String value){
        List<User> userList= userRepository.findAllByNameContainingIgnoreCase(value);

        if(userList.isEmpty()){
            throw new RuntimeException("no such data found");
        }
        //uzun yöntem tek tek mapleme
//        List<UserResponseDto> list=userList.stream().map(x -> IUserMapper.INSTANCE.toUserResponseDtos(x)).collection(Collectors.toList());
        return IUserMapper.INSTANCE.toUserResponseDtos(userList);
    }

    public List<User> findAllByEmailContainingIgnoreCase(String value){
        return userRepository.findAllByEmailContainingIgnoreCase(value);
    }

    public List<User> findAllByEmailEndingWithIgnoreCase(String value){
        return userRepository.findAllByEmailEndingWithIgnoreCase(value);
    }

   public List<User> passwordlongerThan(int value){
       return userRepository.passwordlongerThan(value);
   }

    public List<User> passwordlongerThan2(@Param("x") int value){
        return userRepository.passwordlongerThan2(value);
    }

   public List<User> passwordlongerThan3(int value){
       return userRepository.passwordlongerThan3(value);
   }


}
