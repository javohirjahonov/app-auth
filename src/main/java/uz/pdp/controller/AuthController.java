package uz.pdp.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.dto.UserDTO;
import uz.pdp.model.ApiKetmonResponse;
import uz.pdp.service.AuthService;

@RestController
public record AuthController(AuthService authService) {


    //localhost:8080/api/auth/register
    @PostMapping(value = "/api/auth/register")
    public ApiKetmonResponse signUp(@Valid @RequestBody UserDTO userDTO) {
        return authService.register(userDTO);
    }

}
