package uz.pdp.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.dto.LoginDTO;
import uz.pdp.dto.UserDTO;
import uz.pdp.dto.ApiKetmonResponse;
import uz.pdp.service.AuthService;

@RestController
public record AuthController(AuthService authService) {


    //localhost:8080/api/auth/register
    @PostMapping(value = "/api/auth/register")
    public ApiKetmonResponse signUp(@Valid @RequestBody UserDTO userDTO) {
        return authService.register(userDTO);
    }


    @PostMapping("/api/auth/login")
    public ApiKetmonResponse<String> login(@Valid @RequestBody LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }



    /*
    post - send data to server
    put - full update
    patch - partial update
    delete - remove
    get - fetch
     */
}
