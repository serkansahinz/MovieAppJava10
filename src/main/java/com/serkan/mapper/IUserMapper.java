package com.serkan.mapper;

import com.serkan.dto.request.RegisterRequestDto;
import com.serkan.dto.response.UserResponseDto;
import com.serkan.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {

    IUserMapper INSTANCE= Mappers.getMapper(IUserMapper.class);

    UserResponseDto toUserResponseDto(User user);

    User toUser(RegisterRequestDto dto);
    List<UserResponseDto> toUserResponseDtos(List<User> userList);


}
