package uz.pdp.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data
public class UserDTO {

    private Integer id;

    @NotBlank(message = "Name field should not be empty or null")
    private String name;

    @NotNull(message = "Birthdate should not be null")
    private LocalDate birthDate;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-]).{8,}$")
    private String password;

    @NotNull
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-]).{8,}$")
    private String prePassword;
}
