package com.brian.ticketing_app.user.dto;

import lombok.Getter;
import com.brian.ticketing_app.user.User.UserRole;
import java.time.LocalDateTime;

@Getter
public class CreateUserRequestDTO {
    private String username;
    private String email;
    private String password;
    private UserRole userRole;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
