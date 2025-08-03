package com.ttn.dto;

public record UserRegisterDto(
        String name,
        String email,
        String password
) {
}
