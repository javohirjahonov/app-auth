package uz.pdp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.model.User;
import uz.pdp.service.UserService;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@RestController
public record UserController(UserService userService) {


    //basic auth

    @GetMapping("/api/user")
    public List<User> getUsers() {
        return userService.list();
    }

    public static void main(String[] args) {
        String s = "ketmonsada121@gmail.com:Ahmoq_1234";
        byte[] encode = Base64.getEncoder().encode(s.getBytes(StandardCharsets.UTF_8));
        String encoded = new String(encode, StandardCharsets.UTF_8);
        System.out.println(encoded);
        byte[] decode = Base64.getDecoder().decode(encoded);
        String decoded = new String(decode, StandardCharsets.UTF_8);
        System.out.println(decoded);
    }
}
