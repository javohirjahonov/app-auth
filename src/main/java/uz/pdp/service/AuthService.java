package uz.pdp.service;

import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.dto.UserDTO;
import uz.pdp.exceptions.MyException;
import uz.pdp.model.ApiKetmonResponse;
import uz.pdp.model.User;
import uz.pdp.repo.UserRepo;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Service
public record AuthService(UserRepo userRepo, PasswordEncoder passwordEncoder) {


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

    private User mapToUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setBirthDate(userDTO.getBirthDate());
        return user;
    }
}
