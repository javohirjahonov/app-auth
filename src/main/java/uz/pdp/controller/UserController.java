package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.model.User;
import uz.pdp.service.UserService;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    //basic auth

    @GetMapping("/api/user")
    public List<User> getUsers() {
        return userService.list();
    }

    @PostAuthorize(value = "hasAnyAuthority('admin')")
    @GetMapping("/api/tesha")
    public String a() {
        return "welcome to the tesha controller";
    }

    @PreAuthorize(value = "hasAnyAuthority('admin','moder')")
    @GetMapping("/api/ketmon")
    public String b() {
        return "welcome to the ketmon controller";
    }

    @PreAuthorize(value = "hasAnyAuthority('admin','moder','user')")
    @GetMapping("/api/uroq")
    public String c() {
        return "welcome to the uroq controller";
    }


}
