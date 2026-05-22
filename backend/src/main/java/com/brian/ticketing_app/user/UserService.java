package com.brian.ticketing_app.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.brian.ticketing_app.common.exception.ExceptionClasses.TicketNotFoundException;
import com.brian.ticketing_app.common.exception.ExceptionClasses.UserNotFoundException;
import com.brian.ticketing_app.ticket.Ticket;
import com.brian.ticketing_app.ticket.TicketRepository;
import com.brian.ticketing_app.user.dto.CreateUserRequestDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
@Service
public class UserService {
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    public void createUser(CreateUserRequestDTO createUserRequestDTO) {
        User user = User.builder()
            .username(createUserRequestDTO.getUsername())
            .email(createUserRequestDTO.getEmail())
            .password(createUserRequestDTO.getPassword())
            .userRole(createUserRequestDTO.getUserRole())
            .isActive(true)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
        userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public List<Ticket> getTickets(Long id) {
        Optional<List<Ticket>> tickets = ticketRepository.findByUserId(id);
        if (tickets.isEmpty()) {
            throw new TicketNotFoundException("Ticket not found");
        }
        return tickets.get();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


}
