package com.serkan.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
//kullanıcın bize gönderdiği istek
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequestDto {
    private String name;
    private String surName;
    private String password;
    private String email;
    private String phone;
}
