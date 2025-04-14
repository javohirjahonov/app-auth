package uz.pdp.service;

import org.springframework.stereotype.Service;
import uz.pdp.model.User;
import uz.pdp.repo.UserRepo;

import java.util.List;

@Service
public record UserService(UserRepo userRepo) {


    public List<User> list() {
        return userRepo.findAll();
    }
}
