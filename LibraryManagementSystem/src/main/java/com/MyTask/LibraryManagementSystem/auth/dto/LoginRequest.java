package com.MyTask.LibraryManagementSystem.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "Username is a required field")
    private String username;

    @NotBlank(message = "Password is a required field")
    @Size(message = "Password must have at least 6 characters", min = 6)
    private String password;
}

