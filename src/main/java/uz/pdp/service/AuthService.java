package uz.pdp.service;

import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.dto.LoginDTO;
import uz.pdp.dto.UserDTO;
import uz.pdp.exceptions.MyException;
import uz.pdp.model.ApiKetmonResponse;
import uz.pdp.model.User;
import uz.pdp.repo.UserRepo;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Service
public record AuthService (UserRepo userRepo,
                          PasswordEncoder passwordEncoder) implements UserDetailsService {


    //encode -> decode
    //hash
    //spring security ready hash


    public ApiKetmonResponse register(UserDTO userDTO) throws MyException {

        if (!Objects.equals(userDTO.getPassword(), userDTO.getPrePassword()))
            throw new MyException("Wrong password");

        int years = Period.between(userDTO.getBirthDate(), LocalDate.now()).getYears();
        if (years < 16)
            throw new MyException("You are not eligible");

        if (userRepo.existsByEmail(userDTO.getEmail()))
            throw new MyException("Email already exists");

        User user = mapToUser(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        userRepo.save(user);

        return ApiKetmonResponse.success("ok bro");
    }

    public String login(LoginDTO loginDTO) {
        User user = userRepo.findByEmail(loginDTO.getEmail()).orElseThrow(() -> new MyException("Email or password wrong"));
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword()))
            throw new MyException("Email or password wrong");
        return user.getName();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        return org.springframework.security.core.userdetails.User.builder()
                .username(username)
                .password(user.getPassword())
                .build();
    }

    private User mapToUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setBirthDate(userDTO.getBirthDate());
        return user;
    }
}

/*
username -> password
 */
